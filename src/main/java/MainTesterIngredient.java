import data.dal.IUserDAO;
import data.dal.IngredientDAO;
import data.dto.DALException;
import data.dto.IngredientDTO;

public class MainTesterIngredient {

    public static void main(String[] args) throws DALException, IUserDAO.DALException {

        IngredientDAO ingredientDAO = new IngredientDAO();

        IngredientDTO ingredient = new IngredientDTO("Salt");

        ingredientDAO.createIngredient(ingredient);

        //System.out.println(ingredientDAO.getIngredient(1));

        //IngredientDTO ingredient = new IngredientDTO(1,"Peber");

        //ingredientDAO.updateIngredientName(ingredient);

        //ingredientDAO.deleteIngredient(1);






    }
}
