package data.dal;

import data.dto.DALException;
import data.dto.RecipeDTO;
import data.dto.UserDTO;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeDAO implements Serializable {
    private final String url = "jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185131?";
    private final String userName= "user=s185131";
    private final String pass = "password=f641omiIhm3Ly1oQR5khj";

    public void createRecipe(RecipeDTO recipe) throws DALException {
        try(Connection connection = DriverManager.getConnection(url + userName +"&"+ pass)){   // med det syntax behøver man ikke lave final og connection.close()
            // try with resources
            PreparedStatement pStmt = connection.prepareStatement("INSERT INTO recipes (recipe_name) VALUES(?)");

            pStmt.setString(1 ,recipe.getRecipeName());

            pStmt.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public RecipeDTO getRecipe(int recipeId) throws DALException {
        RecipeDTO recipe = null;

        // closes itself if something fails
        try(Connection connection = DriverManager.getConnection(url + userName + "&" + pass)){

            PreparedStatement pStmt = connection.prepareStatement(
                    "SELECT * FROM recipes WHERE recipe_id = ?");

            pStmt.setInt(1, recipeId);
            ResultSet resultSet = pStmt.executeQuery();
            resultSet.next();

            recipe = new RecipeDTO(recipeId, resultSet.getString(2), resultSet.getDate(3));
        } catch (SQLException e){
            e.printStackTrace();
        }
        return recipe;
    }

    public List<RecipeDTO> getRecipeList() throws DALException {

        List<RecipeDTO> recipes = new ArrayList<>();
        RecipeDTO recipe = null;

        try(Connection connection = DriverManager.getConnection(url + userName + "&" + pass)){

            PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM recipes");
            ResultSet resultSet = pStmt.executeQuery();

            while(resultSet.next()){
//                recipe = new RecipeDTO(resultSet.getInt(1),resultSet.getString(2),resultSet.getDate(3));      //kan man ikke bare gøre det her?
                recipe = new RecipeDTO();
                recipe.setRecipeId(resultSet.getInt(1));
                recipe.setRecipeName(resultSet.getString(2));
                recipe.setEndDate(resultSet.getDate(3));

                recipes.add(recipe);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } return recipes;
    }

    public void updateRecipe(RecipeDTO recipe) throws DALException {

        try (Connection connection = DriverManager.getConnection(url + userName + "&" + pass)) {

            PreparedStatement pStmt = connection.prepareStatement("UPDATE recipes SET recipe_name = ?, end_date = ? WHERE recipe_id = ?");

            pStmt.setString(1, recipe.getRecipeName());
            pStmt.setDate(2, recipe.getEndDate());
            pStmt.setInt(3, recipe.getRecipeId());
            pStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRecipe(int recipeId) throws DALException {
        try(Connection connection = DriverManager.getConnection(url + userName +"&"+ pass)){

            PreparedStatement pStmt = connection.prepareStatement("DELETE FROM recipes WHERE recipe_id = ?");

            pStmt.setInt(1, recipeId);
            pStmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }




}
