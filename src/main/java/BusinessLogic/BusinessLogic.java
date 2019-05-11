package BusinessLogic;

import data.dal.RecipeDAO;
import data.dal.RoleDAO;
import data.dal.UserDAO;
import data.dal.UserRoleDAO;
import data.dto.*;

import java.util.ArrayList;
import java.util.List;

public class BusinessLogic {
    UserRoleDAO userRoleDAO = new UserRoleDAO();
    RoleDAO roleDAO = new RoleDAO();
    RecipeDAO recipeDAO = new RecipeDAO();
    UserDAO userDAO = new UserDAO();

//    UserRoleDTO userRoleDTO = new UserRoleDTO();

    /**
     * A user tries to create a recipe.
     * Only users with a "farmaceut" role is allowed to create recipes.
     *
     * If operation is successful, the recipe is returned.
     * In case of no success, null is returned.
     */
    public RecipeDTO createRecipe(int userId, String newRecipeName) throws DALException {
        List<UserRoleDTO> rolesId = userRoleDAO.getRolesList(userId);
        List<RoleDTO> roles = new ArrayList<>();
        for (int i = 0; i < rolesId.size(); i++) {
                    roles.add(roleDAO.getRole(rolesId.get(i).getRoleId()));
        }
        RecipeDTO newRecipe = null;
        for (int i = 0; i < roles.size(); i++) {
            if (roles.get(i).getRoleName().toLowerCase().equals("farmaceut")) {     //only "farmaceut allowed to do this
                recipeDAO.createRecipe(newRecipeName);              //end date and id automatically generated
                newRecipe = recipeDAO.getRecipe(newRecipeName);
            }
        }
        return newRecipe;
    }


//    public UserDTO createUserWithRoles(int roleCount, int userId) {
//        userDAO.createUser("");
//
//    }

}
