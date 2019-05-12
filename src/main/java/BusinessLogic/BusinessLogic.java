package BusinessLogic;

import data.dal.*;
import data.dto.*;

import javax.management.relation.Role;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    /** -laborant-
     * A user tries to change productBatchStatus.
     * Only users with a(n) "laborant" role is allowed to change status.
     *
     * If operation is successful, the updated productBatch is returned.
     * In case of no success, null is returned.
     *
     * If product batch is under production, the used ressources are saved in the database.
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

                    double ressUsedAmount = (double) prodStk * ingrAmount * ingredients.get(i).getTolerance();

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
//        ProductBatchDTO productBatch = null;

        boolean access = isUserAllowedAccess(roles, "produktionsleder");
        if(access) {
            ProductBatchDTO productBatchDTO = new ProductBatchDTO(expirationDate, amountInStk, recipeId);
            productBatchDAO.createProductBatch(productBatchDTO);
//            productBatch = productBatchDAO.getProductBatch(pBId);     //nok lige meget

        }
        return access;
    }



//    public void updateUsedRessourceAmount(int recipeId, int productBatchId) {
//
//            List<ingredientAmountDTO> ingredients = ingredientAmountDAO.getIngredientList(recipeId);
//
//            for (int i = 0; i < ingredients.size(); i++) {
//                int ingrAmount = ingredients.get(i).getIngredientAmountIn_mg(recipeId);          //mængden uden den procentvise afvigelse
//
//                double ressUsedAmount = (double) amountInStk * ingrAmount * ingredients.get(i).getTolerance();        //kunne også droppe int for double i stedet for at caste
//
//
////ressBatches har samme amount, men used amount kan trækkes fra for at se hvor meget er tilbage
//                List<Integer> ressBatchIdList = ressourceBatchAmount.getIdListForBatchesBiggerThan(ressUsedAmount);
//
////                List<RessourceBatchDTO> ressBatches = ressourceBatchDAO.getRessBatchListForMinBiggerThan(ressUsedAmount);
//
//
//
//                for (int j = 0; j < ressBatchIdList.size(); j++) {
//
//                    RessourceBatchDTO ressourceBatch = ressourceBatchDAO.getRessourceBatch(ressBatchIdList.get(j));
//                    double recievedBatchAmount = ressourceBatch.getRessourceAmount();       //er sat som int i DTO
//
//                    RessourceBatchAmountDTO ressourceBatchAmount = ressourcebatchAmountDAO.getRessourceBatchAmount(productBatchId, ressBatchIdList.get(j))
//                    int totalUsedAmount = ressourceBatchAmount.getUsedAmount;
//
//
//                    if (ressUsedAmount > recievedBatchAmount - totalUsedAmount) {
//
//                    }
//                }
//
//
//                ressourceBatchDAO.getRessIdListForIngredient(, ressUsedAmount); //returnerer id
//
//
//                ressourceAmountDAO.updateUsedAmount(ressBatches, productBatchId, ressUsedAmount);
//
//        }
//    }


}
