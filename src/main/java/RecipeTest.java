import data.dal.RecipeDAO;
import data.dto.DALException;

public class RecipeTest {




    public static void main(String[] args) throws DALException {
        RecipeDAO recipeDAO = new RecipeDAO();


        System.out.println(recipeDAO.getRecipeList());
    }
}
