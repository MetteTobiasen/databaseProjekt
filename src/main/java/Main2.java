import data.dal.IUserDAO;
import data.dal.ProductBatchDAO;
import data.dal.RoleDAO;
import data.dal.UserDAO;
import data.dto.RecipeDTO;
import data.dto.RoleDTO;
import sun.util.calendar.LocalGregorianCalendar;

import java.util.Date;

public class Main2 {

    public static void main(String[] args) throws IUserDAO.DALException {
        UserDAO userDAO = new UserDAO();
        //UserDTO user = new UserDTO(23,"Hejsa");

        //userDAO.createUser(user);

        //tester createProductBatch in productBatchDAO
        ProductBatchDAO productBatchDAO = new ProductBatchDAO();
        //ProductBatchDTO productBatch = new ProductBatchDTO(1,new java.sql.Date(2019-8-5),23,3);

        //productBatchDAO.createProductBatch(productBatch);
        //System.out.println(productBatchDAO.getProductBatch(2));

        RoleDAO roleDAO = new RoleDAO();
        RoleDTO role = new RoleDTO(5,"admin");

        roleDAO.createRole(role);

        RoleDTO role1 = new RoleDTO(3, "LabKing");

<<<<<<< Updated upstream:src/main/java/Main.java
        roleDAO.updateRole(role1);


        System.out.println(roleDAO.getRoleList());
=======
        RecipeDTO recipe2 = new RecipeDTO(5,"Soup", Date.valueOf("2019-05-08"));

//        recipeDAO.createRecipe(recipe);
//        List<RecipeDTO> recipes = recipeDAO.getRecipeList();
//        System.out.println(recipes.toString());                     //TODO toString skal overskrives til at skrifte linje

        recipeDAO.updateRecipe(recipe2);
//
//
        System.out.println(recipeDAO.getRecipe(5));
>>>>>>> Stashed changes:src/main/java/ Main.java










        //System.out.println(user.toString());
    }


}
