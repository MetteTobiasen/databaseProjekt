package data.dal;

import data.dto.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientAmountsDAO {

    private final String url = "jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185131?";
    private final String userName= "user=s185131";
    private final String pass = "password=f641omiIhm3Ly1oQR5khj";


    public void createIngredientAmounts (IngredientAmountsDTO ingredientAmount)throws DALException {
        try(Connection connection = DriverManager.getConnection(url + userName +"&"+ pass)){

            PreparedStatement pStmt = connection.prepareStatement(
                    "INSERT INTO ingredient_amount_in_mg (ingredient_amount_in_mg, tolerance_in_percent, ingredient_id, recipe_id) VALUES(?,?,?,?)");

            pStmt.setInt(1, ingredientAmount.getIngredientAmount());
            pStmt.setInt(2, ingredientAmount.getTolerance());
            pStmt.setInt(3, ingredientAmount.getIngredientId());
            pStmt.setInt(4, ingredientAmount.getRecipeId());

            pStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public IngredientAmountsDTO getIngredientAmount(int ingredientId, int recipeId) throws DALException {

        IngredientAmountsDTO ingredientAmount = null;

        try(Connection connection = DriverManager.getConnection(url + userName +"&"+ pass)){

            PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM ingredient_amounts WHERE ingredient_id = ? & recipe_id = ? ");

            pStmt.setInt(1, ingredientId);
            pStmt.setInt(2, recipeId);

            ResultSet resultset = pStmt.executeQuery();

            resultset.next();

            ingredientAmount = new IngredientAmountsDTO(resultset.getInt(1), resultset.getInt(2),ingredientId, recipeId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredientAmount;
    }

    public List<IngredientAmountsDTO> getIngredientAmounts() throws DALException {
        List<IngredientAmountsDTO> ingredientAmounts = new ArrayList<>();
        IngredientAmountsDTO ingredientAmount = null;

        try(Connection connection = DriverManager.getConnection(url + userName + "&" + pass)){

            PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM ingredient_amounts");
            ResultSet resultSet = pStmt.executeQuery();

            while(resultSet.next()){
                ingredientAmount = new IngredientAmountsDTO(resultSet.getInt(1),resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4));
                ingredientAmounts.add(ingredientAmount);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } return ingredientAmounts;

    }

    public List<IngredientAmountsDTO> getIngredientAmountsListFromRecipe(int recipeId) throws DALException {
        List<IngredientAmountsDTO> ingredientAmounts = new ArrayList<>();
        IngredientAmountsDTO ingredientAmount = null;

        try(Connection connection = DriverManager.getConnection(url + userName + "&" + pass)){

            PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM ingredient_amouns WHERE recipe_id = ?");
            ResultSet resultSet = pStmt.executeQuery();

            while(resultSet.next()){
                ingredientAmount = new IngredientAmountsDTO(resultSet.getInt(1),resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(recipeId));
                ingredientAmounts.add(ingredientAmount);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } return ingredientAmounts;

    }

    public void updateIngredientAmount(IngredientAmountsDTO ingredientAmount) throws DALException {

        try (Connection connection = DriverManager.getConnection(url + userName + "&" + pass)) {

            PreparedStatement pStmt = connection.prepareStatement("UPDATE ingredient_amounts SET ingredient_amount_in_mg = ?, tolerance = ?, ingredient_id = ?, recipeId = ? WHERE ingredientId AND recipeId = ?");

            pStmt.setInt(1, ingredientAmount.getIngredientAmount());
            pStmt.setInt(2, ingredientAmount.getTolerance());
            pStmt.setInt(3, ingredientAmount.getIngredientId());
            pStmt.setInt(4, ingredientAmount.getRecipeId());
            pStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteIngredientAmount(int ingredientId, int recipeId) throws DALException {
        try (Connection connection = DriverManager.getConnection(url + userName + "&" + pass)) {

            PreparedStatement pStmt = connection.prepareStatement("DELETE FROM ingredient_amounts WHERE ingredient_id = AND & recipe = ?");
            pStmt.setInt(1, ingredientId);
            pStmt.setInt(2, recipeId);
            pStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

}

