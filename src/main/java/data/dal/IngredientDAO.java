package data.dal;

import data.dto.DALException;
import data.dto.IngredientDTO;
import data.dto.UserDTO;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientDAO implements IIngredientDAO {

    private final String url = "jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185131?";
    private final String userName= "user=s185131";
    private final String pass = "password=f641omiIhm3Ly1oQR5khj";

    @Override
    public void createIngredient(IngredientDTO ingredient) throws DALException {
        try(Connection connection = DriverManager.getConnection(url + userName +"&"+ pass)){   // med det syntax behøver man ikke lave final og connection.close()

            PreparedStatement pStmt = connection.prepareStatement("INSERT INTO ingredients (ingredient_name) VALUES(?)");

            pStmt.setString(1 ,ingredient.getIngredientName());

            pStmt.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public IngredientDTO getIngredient(int ingredientId) throws DALException {
        IngredientDTO ingredient = null;

        // closes itself if something fails
        try(Connection connection = DriverManager.getConnection(url + userName + "&" + pass)){

            PreparedStatement pStmt = connection.prepareStatement(
                    "SELECT * FROM ingredients WHERE ingredient_id = ?");

            pStmt.setInt(1, ingredientId);
            ResultSet resultSet = pStmt.executeQuery();
            resultSet.next();

            ingredient = new IngredientDTO(ingredientId,resultSet.getString(2), resultSet.getInt(3));

        } catch (SQLException e){
            e.printStackTrace();
        }
        return ingredient;
    }

    @Override
    public IngredientDTO getIngredientName(int ingredientId) throws DALException{
        IngredientDTO ingredientName = null;

        try(Connection connection = DriverManager.getConnection(url + userName + "&" + pass)){

            PreparedStatement pStmt = connection.prepareStatement(
                    "SELECT ingredient_name FROM ingredients WHERE ingredient_id = ?");

            pStmt.setInt(1, ingredientId);
            ResultSet resultSet = pStmt.executeQuery();
            resultSet.next();

            ingredientName = new IngredientDTO(resultSet.getString(2));

        } catch (SQLException e){
            e.printStackTrace();
        }
        return ingredientName;
    }

    @Override
    public List<IngredientDTO> getIngredientList() throws DALException {
        List<IngredientDTO> ingredients = new ArrayList<>();
        IngredientDTO ingredient = null;

        try(Connection connection = DriverManager.getConnection(url + userName + "&" + pass)){

            PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM ingredients");
            ResultSet resultSet = pStmt.executeQuery();

            while(resultSet.next()){
                ingredient = new IngredientDTO(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3));
                ingredients.add(ingredient);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } return ingredients;

    }

    @Override
    public void updateIngredientName(IngredientDTO ingredient) throws DALException {

        try (Connection connection = DriverManager.getConnection(url + userName + "&" + pass)) {

            PreparedStatement pStmt = connection.prepareStatement("UPDATE ingredients SET ingredient_name = ? WHERE ingredient_id = ?");

            pStmt.setString(1, ingredient.getIngredientName());
            pStmt.setInt(2, ingredient.getIngredientId());
            pStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateIngredientReorder(int ingredientId, boolean decrement) throws DALException {
        try (Connection connection = DriverManager.getConnection(url + userName + "&" + pass)) {

            int reorderCount = getIngredient(ingredientId).getReoder();

            PreparedStatement pStmt = connection.prepareStatement("UPDATE ingredients SET reorder = ? WHERE ingredient_id = ?");

            if (decrement) {
                pStmt.setInt(1,reorderCount - 1);
            } else {
                pStmt.setInt(1, reorderCount + 1);
            }
            pStmt.setInt(2, ingredientId);
            pStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteIngredient(int ingredientId) throws DALException {
        try(Connection connection = DriverManager.getConnection(url + userName +"&"+ pass)){

            PreparedStatement pStmt = connection.prepareStatement("DELETE FROM ingredients WHERE ingredient_id = ?");
            pStmt.setInt(1, ingredientId);
            pStmt.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<IngredientDTO> getOrderedRessources() {
        List<IngredientDTO> ingredients = new ArrayList<>();
        IngredientDTO ingredient = null;

        try(Connection connection = DriverManager.getConnection(url + userName + "&" + pass)){

            PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM ingredients WHERE reorder < 0");
            ResultSet resultSet = pStmt.executeQuery();

            while(resultSet.next()){
                ingredient = new IngredientDTO(resultSet.getInt(1),resultSet.getString(2), resultSet.getInt(3));
                ingredients.add(ingredient);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } return ingredients;
    }
}
