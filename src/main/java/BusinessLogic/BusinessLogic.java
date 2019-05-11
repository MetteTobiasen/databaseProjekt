package BusinessLogic;

import data.dal.*;
import data.dto.*;

import javax.management.relation.Role;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class BusinessLogic {
    UserRoleDAO userRoleDAO = new UserRoleDAO();
    RoleDAO roleDAO = new RoleDAO();
    RecipeDAO recipeDAO = new RecipeDAO();
    UserDAO userDAO = new UserDAO();
    ProductBatchDAO productBatchDAO = new ProductBatchDAO();


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
            newRecipe = recipeDAO.getRecipe(recipeName);
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
     * If operation is successful, the created user is returned.
     * In case of no success, null is returned.
     */
    public UserDTO createUser(int userId, String newUserName) throws IUserDAO.DALException, DALException {
        List<RoleDTO> roles = getRolesByUserId(userId);
        UserDTO newUser = null;

        if(isUserAllowedAccess(roles, "administrator")){
            UserDTO userDTO = new UserDTO(newUserName);
            userDAO.createUser(userDTO);
            newUser = userDAO.getUser(userDTO.getUserId());
        }
        return newUser;
    }

    /** -laborant-
     * A user tries to change productBatchStatus.
     * Only users with a(n) "laborant" role is allowed to change status.
     *
     * If operation is successful, the updated productBatch is returned.
     * In case of no success, null is returned.
     */
    public ProductBatchDTO changeProductionStatus(int userId, int productBatchId, String newStatus) throws DALException {
        List<RoleDTO> roles = getRolesByUserId(userId);
        ProductBatchDTO productBatch = null;

        if (isUserAllowedAccess(roles, "laborant")) {
            productBatchDAO.getProductBatch(productBatchId);
            ProductBatchDTO productBatchDTO = productBatchDAO.getProductBatch(productBatchId);
            productBatch.setStatus(newStatus);

            productBatchDAO.updateProductBatchStatus(productBatch);

            productBatch = productBatchDAO.getProductBatch(productBatchId);
        }

        return productBatch;
    }




    /** -produktionsleder-
     *
     */


}
