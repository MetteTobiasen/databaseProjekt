package BusinessLogic;

import data.dal.*;
import data.dto.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class BusinessLogic {
    UserRoleDAO userRoleDAO = new UserRoleDAO();
    RoleDAO roleDAO = new RoleDAO();
    RecipeDAO recipeDAO = new RecipeDAO();
    UserDAO userDAO = new UserDAO();
    ProductBatchDAO productBatchDAO = new ProductBatchDAO();
    IngredientAmountsDAO ingredientAmountsDAO = new IngredientAmountsDAO();
    RessourceAmountsDAO ressourceAmountsDAO  = new RessourceAmountsDAO();
    IngredientDAO ingredientDAO = new IngredientDAO();
    RessourceBatchDAO ressourceBatchDAO = new RessourceBatchDAO();


    /** -farmaceut-
     * A user tries to create a recipe.
     * Only users with a(n) "farmaceut" role is allowed to create recipes.
     *
     * If operation is successful, the recipe is returned.
     * In case of no success, null is returned.
     */
    public RecipeDTO createRecipe(int userId, String recipeName) throws DALException {
        List<RoleDTO> roles = getRolesByUserId(userId);
        RecipeDTO newRecipe = null;

        if(isUserAllowedAccess(roles, "farmaceut")) {
            recipeDAO.createRecipe(recipeName);              //end date and id automatically generated
            newRecipe = recipeDAO.getRecipe(recipeName);        //name is a unique value in the table
        }
        return newRecipe;
    }


    /** -farmacuet-
     * A user tries to update a recipe.
     * Only users with a(n) "farmaceut" role is allowed to create recipes.
     *
     * If operation is successful, the recipe is returned.
     * In case of no success, null is returned.
     */
    public RecipeDTO updateRecipe(int userId, int recipeId, Date updateDate, String newRecipeName) throws DALException {
        List<RoleDTO> roles = getRolesByUserId(userId);
        RecipeDTO newRecipe = null;

        if(isUserAllowedAccess(roles, "farmaceut")) {
            RecipeDTO recipe = new RecipeDTO(recipeId,newRecipeName,updateDate);
            recipeDAO.updateRecipe(recipe);
            newRecipe = recipeDAO.getRecipe(recipeId);
        }
        return newRecipe;
    }


    /** -farmaceut-
     * Returns a list of the ressources that currently has 1 or more orders
     */
    public List<IngredientDTO> getOrderedRessources() {
        return ingredientDAO.getOrderedRessources();
    }


    /** -farmaceut-
     * Adds a ressourcebatch to the ressource_batches table,
     * and decrements the reorder status for that ingredient
     */
    public void createRessourceBatch(RessourceBatchDTO ressourceBatch, int ingredientId, String producerName) throws DALException {
        ressourceBatchDAO.createRessourceBatch(ressourceBatch,ingredientId,producerName);
        updateIngredientReorder(ingredientId, true);
    }


    /** -administrator-
     * A user tries to create another user.
     * Only users with a(n) "administrator" role is allowed to create users.
     *
     * If user is allowed the operation, true is returned.
     * In case of no access, false is returned.
     */
    public boolean createUser(int userId, String newUserName) throws IUserDAO.DALException, DALException {
        List<RoleDTO> roles = getRolesByUserId(userId);
//        UserDTO newUser = null;
        boolean access = isUserAllowedAccess(roles, "administrator");

        if(access){
            UserDTO userDTO = new UserDTO(newUserName);
            userDAO.createUser(userDTO);
//            newUser = userDAO.getUser(userDTO.getUserId());             //har nok ikke det rigtige id
        }
        return access;
    }


    /** -administrator-
     * A user tries to add a role to an existing user.
     * Only users with a(n) "administrator" role is allowed to add roles.
     *
     * If user is allowed the operation, true is returned.
     * In case of no access, false is returned.
     */
    public boolean addOneRole(int userId, int roleid) throws DALException {
        List<RoleDTO> roles = getRolesByUserId(userId);

        boolean access = isUserAllowedAccess(roles, "administrator");
        if (access) {
            userRoleDAO.createUserRoles(userId,roleid);
        }
        return access;
    }


    /** -laborant-
     * A user tries to change productBatchStatus.
     * Only users with a(n) "laborant" role is allowed to change status.
     *
     * If operation is successful, the updated productBatch is returned.
     * In case of no success, null is returned.
     *
     * If product batch is under production, the used ressource amounts are updated and saved in the database.
     */
    public ProductBatchDTO updateProductionStatus(int userId, int productBatchId, String newStatus) throws DALException {
        List<RoleDTO> roles = getRolesByUserId(userId);
        ProductBatchDTO productBatch = null;

        if (isUserAllowedAccess(roles, "laborant")) {
            productBatchDAO.getProductBatch(productBatchId);
            ProductBatchDTO productBatchDTO = productBatchDAO.getProductBatch(productBatchId);
            productBatch.setStatus(newStatus);

            productBatchDAO.updateProductBatchStatus(productBatch);

            productBatch = productBatchDAO.getProductBatch(productBatchId);

            if (newStatus.toLowerCase().equals("under produktion".toLowerCase())) {
                int prodStk = productBatch.getProductBatchAmount();
                List<IngredientAmountsDTO> ingredients = ingredientAmountsDAO.getIngredientAmounts(productBatch.getRecipeId());

                for (int i = 0; i < ingredients.size(); i++) {
                    int ingrAmount = ingredients.get(i).getIngredientAmount();

                    //simulated variation
                    double toleranceVariation =  Math.random()/100 * ingredients.get(i).getTolerance();
                    int plusMinusSim = (int) (Math.random() * 2);
                    if (plusMinusSim == 0) {
                        toleranceVariation = 1 - toleranceVariation;
                    } else {
                        toleranceVariation = 1 + toleranceVariation;
                    }

                    double ressUsedAmount = (double) prodStk * ingrAmount * toleranceVariation;


                    //updates the used amount in ressourceBatches
                    RessourceBatchDTO ressourceBatch = getMinUsableRessourceBatch(ingredients.get(i).getIngredientId(),ressUsedAmount);
                    updateRessourcebatchAmount(ressourceBatch,ressUsedAmount);
                    //sets is_rest status if necessary
                    ressourceBatchDAO.setIsRestStatusIfNeeded(ressourceBatch,1000 * getMinUsedIngredientAmountForRecipes(ingredients.get(i).getIngredientId()));

                    //sets the status for reordering that ingredient if needed
                    if (ressourceBatch.getRessourceBatchAmount() < 2 * 1000 * getMaxUsedIngredientAmountForRecipes(ingredients.get(i).getIngredientId())) {
                        updateIngredientReorder(ingredients.get(i).getIngredientId(), false);
                    }

                    //updates the used amount in ressource_used_amounts for that product
                    RessourceAmountsDTO ressourceAmounts = new RessourceAmountsDTO(ressUsedAmount, ingredients.get(i).getIngredientId(),productBatchId);
                    ressourceAmountsDAO.createRessourceAmount(ressourceAmounts);

                }
            }
        }
        return productBatch;
    }


    /** -produktionsleder-
     * A user tries to create a product batch.
     * Only users with a(n) "produktionsleder" role is allowed to create the batch.
     *
     * If user is allowed the operation, true is returned.
     * In case of no access, false is returned.
     */
    public boolean createProductBatch(int userId, int recipeId, int amountInStk, Date expirationDate) throws DALException {
        List<RoleDTO> roles = getRolesByUserId(userId);
        boolean access = isUserAllowedAccess(roles, "produktionsleder");
        if(access) {
            ProductBatchDTO productBatchDTO = new ProductBatchDTO(expirationDate, amountInStk, recipeId);
            productBatchDAO.createProductBatch(productBatchDTO);
        }
        return access;
    }


    /** -produktionsleder-
     * A user tries to create a product batch.
     * Only users with a(n) "produktionsleder" role is allowed to create the batch.
     *
     * If user is allowed the operation, true is returned.
     * In case of no access, false is returned.
     */
    public boolean updateProductBatchExpirationDate(int userId, int productBatchId, Date newExpirationDate) throws DALException {
        List<RoleDTO> roles = getRolesByUserId(userId);

        boolean access = isUserAllowedAccess(roles, "produktionsleder");
        if(access) {

            ProductBatchDTO productBatchDTO = new ProductBatchDTO(newExpirationDate);
            productBatchDAO.updateProductBatchExpirationDate(productBatchDTO);
        }
        return access;
    }


    /** -produktionsleder-
     *
     * Returns a list of the ressource batches
     * which have enough ressources left to be used in recipes.
     */
    public List<RessourceBatchDTO> getRelevantRessourceBatches() {
        return ressourceBatchDAO.getNotRestRessourceBatchList();
    }


    /** -all users-
     * Returns a list with all productbatches, with the status of the parameter.
     */
    public List<ProductBatchDTO> getProductBatchWithStatus(String status) throws DALException {
        return productBatchDAO.getProductBatchesWithStatus(status);
    }


    /** -system-
     * Update made for incrementing/decrementing the reoder attribute of a
     * certain ingredient by one, in case of a shortage of that ressources.
     */
    public void updateIngredientReorder(int ingredientId, boolean decrement) throws DALException {
        ingredientDAO.updateIngredientReorder(ingredientId, decrement);
    }

    /** -system-
     * Suitable for finding the relevant batch to substract amounts from.
     */
    public RessourceBatchDTO getMinUsableRessourceBatch(int ingredientId, double usedAmount) {
        return ressourceBatchDAO.getMinUsableRessourceBatch(ingredientId, usedAmount);
    }

    /** -system-
     * The ressourcebatchDTO in the parameter must be a legitimate object from the database,
     * and it would be best if it is directly derived from the database beforehand.
     */
    public boolean updateRessourcebatchAmount(RessourceBatchDTO ressourceBatchDTO, double usedAmount) {
        return ressourceBatchDAO.updateRessourceBatchAmount(ressourceBatchDTO, usedAmount);
    }

    public double getMinUsedIngredientAmountForRecipes(int ingredientId) {
        return ingredientAmountsDAO.getMinUsedIngredientAmountForRecipes(ingredientId);
    }

    public double getMaxUsedIngredientAmountForRecipes(int ingredientId) {
        return ingredientAmountsDAO.getMaxUsedIngredientAmountForRecipes(ingredientId);
    }

    /** -system-
     * For getting a users roles, used to figure out
     * the users allowed actions in the system.
     */
    public List<RoleDTO> getRolesByUserId(int userId) throws DALException {
        List<UserRoleDTO> rolesId = userRoleDAO.getRolesList(userId);
        List<RoleDTO> roles = new ArrayList<>();
        for (int i = 0; i < rolesId.size(); i++) {
            roles.add(roleDAO.getRole(rolesId.get(i).getRoleId()));
        }
        return roles;
    }


    /** -system-
     * Takes a list of roles, and checks if it corresponds
     * with the specified role allowed to take a certain action.
     */
    public boolean isUserAllowedAccess(List<RoleDTO> roles, String allowedRoleName) {
        boolean allowedAccess = false;
        for (int i = 0; i < roles.size(); i++) {
            if (roles.get(i).getRoleName().toLowerCase().equals(allowedRoleName.toLowerCase())) {
                allowedAccess = true;
            }
        }
        return allowedAccess;
    }


}
