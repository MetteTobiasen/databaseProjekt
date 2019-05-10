package data.dal;

import data.dto.DALException;
import data.dto.IngredientDTO;
import data.dto.UserDTO;

import java.io.Serializable;
import java.sql.*;

public class IngredientDAO implements Serializable {

    private final String url = "jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185131?";
    private final String userName= "user=s185131";
    private final String pass = "password=f641omiIhm3Ly1oQR5khj";

    public void createIngredient(IngredientDTO ingredient) throws DALException {
        try(Connection connection = DriverManager.getConnection(url + userName +"&"+ pass)){   // med det syntax behøver man ikke lave final og connection.close()

            PreparedStatement pStmt = connection.prepareStatement("INSERT INTO ingredients (ingredient_name) VALUES(?)");

            pStmt.setString(1 ,ingredient.getRessourceName());

            pStmt.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public IngredientDTO getIngredient(int ingredientId) throws DALException {
        IngredientDTO ingredient = null;

        // closes itself if something fails
        try(Connection connection = DriverManager.getConnection(url + userName + "&" + pass)){

            PreparedStatement pStmt = connection.prepareStatement(
                    "SELECT * FROM ingredients WHERE ingredient_id = ?");

            pStmt.setInt(1, ingredientId);
            ResultSet resultSet = pStmt.executeQuery();
            resultSet.next();

            ingredient = new IngredientDTO(ingredientId,resultSet.getString(2));

        } catch (SQLException e){
            e.printStackTrace();
        }
        return ingredient;
    }

}
