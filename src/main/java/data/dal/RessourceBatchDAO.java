package data.dal;

import com.mysql.cj.protocol.Resultset;
import data.dto.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RessourceBatchDAO {
    private final String url = "jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185131?";
    private final String userName= "user=s185131";
    private final String pass = "password=f641omiIhm3Ly1oQR5khj";


   public void createRessourceBatch (RessourceBatchDTO ressourceBatch, int ingredientId, String producerName)throws DALException{
       try(Connection connection = DriverManager.getConnection(url + userName +"&"+ pass)){

           IngredientDTO ingredient = new IngredientDTO(ingredientId);
           ProducerDTO producer = new ProducerDTO(producerName);

           PreparedStatement pStmt = connection.prepareStatement(
                   "INSERT INTO ressource_batches (ressource_batch_amount_in_mg, is_rest, producer_name, ingredient_id) VALUES(?,?,?,?)");

           pStmt.setDouble(1, ressourceBatch.getRessourceBatchAmount());
           pStmt.setInt(2,ressourceBatch.getIsRest());
           pStmt.setString(3, producer.getProducerName());
           pStmt.setInt(4, ingredient.getIngredientId());

           pStmt.executeUpdate();

       } catch (SQLException e) {
           e.printStackTrace();
       }
   }


    public RessourceBatchDTO getRessourceBatch(int ressourceBatchId) throws DALException {

        RessourceBatchDTO ressourceBatch = null;

        try(Connection connection = DriverManager.getConnection(url + userName +"&"+ pass)){

            PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM ressource_batches WHERE ressource_batch_id = ? ");

            pStmt.setInt(1, ressourceBatchId);

            ResultSet resultset = pStmt.executeQuery();

            resultset.next();

            ressourceBatch = new RessourceBatchDTO(ressourceBatchId,resultset.getDouble(2),resultset.getInt(3),resultset.getString(4), resultset.getInt(5));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ressourceBatch;
    }

    public List<RessourceBatchDTO> getRessourceBatchList() throws DALException {
        List<RessourceBatchDTO> ressourceBatches = new ArrayList<>();
        RessourceBatchDTO ressourceBatch = null;

        try(Connection connection = DriverManager.getConnection(url + userName + "&" + pass)){

            PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM ressource_batches");
            ResultSet resultSet = pStmt.executeQuery();

            while(resultSet.next()){
                ressourceBatch = new RessourceBatchDTO(resultSet.getInt(1),resultSet.getDouble(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getInt(5));
                ressourceBatches.add(ressourceBatch);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ressourceBatches;
    }


    //TODO skal omskrives til at give en liste med sum af batchAmounts for hvert med forskelligt ingredientId
    public double getRessourceBatchSumForIngredient(int ingredientId) throws DALException {
        double ingredientAmount = 0;
        try(Connection connection = DriverManager.getConnection(url + userName + "&" + pass)){

            PreparedStatement pStmt = connection.prepareStatement("SELECT SUM(ressource_batch_amount_in_mg) FROM ressource_batches WHERE ingredient_id = ? AND is_rest = 0");

            pStmt.setInt(1, ingredientId);
            ResultSet resultSet = pStmt.executeQuery();

              ingredientAmount = resultSet.getDouble(1);

            return ingredientAmount;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredientAmount;
    }



    public void updateRessourceBatch(RessourceBatchDTO ressourceBatch, int ingredientId, String producerName) throws DALException {

        try (Connection connection = DriverManager.getConnection(url + userName + "&" + pass)) {

            IngredientDTO ingredient = new IngredientDTO(ingredientId);
            ProducerDTO producer = new ProducerDTO(producerName);

            PreparedStatement pStmt = connection.prepareStatement("UPDATE ressource_batches SET ressource_batch_amount_in_mg = ?, is_rest = ?, producer_name = ?, ingredient_id = ? WHERE ressource_batch_id = ?");

            pStmt.setDouble(1, ressourceBatch.getRessourceBatchAmount());
            pStmt.setInt(2, ressourceBatch.getIsRest());
            pStmt.setString(3, producer.getProducerName());
            pStmt.setInt(4, ingredient.getIngredientId());
            pStmt.setInt(5, ressourceBatch.getRessourceBatchId());
            pStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRessourceBatch(int ressourceBatchId) throws DALException {
        try (Connection connection = DriverManager.getConnection(url + userName + "&" + pass)) {

            PreparedStatement pStmt = connection.prepareStatement("DELETE FROM ressource_batches WHERE ressource_batch_id = ?");
            pStmt.setInt(1, ressourceBatchId);
            pStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public RessourceBatchDTO getMinUsableRessourceBatch(int ingredientId, double usedAmount) {
       RessourceBatchDTO ressourceBatch = null;
        try (Connection connection = DriverManager.getConnection(url + userName + "&" + pass)) {

            PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM ressource_batches WHERE is_rest = 0 AND ingredient_id = ? AND ressource_batch_amount_in_mg > ? ORDER BY ressource_batch_amount_in_mg LIMIT 1;");
            pStmt.setInt(1, ingredientId);
            pStmt.setDouble(2, usedAmount);
            ResultSet resultset = pStmt.executeQuery();

            ressourceBatch = new RessourceBatchDTO(resultset.getInt(1),resultset.getDouble(2),resultset.getInt(3),resultset.getString(4), resultset.getInt(5));

        } catch (SQLException e) {
            e.printStackTrace();
        }
       return ressourceBatch;
    }

    public void setIsRestStatusIfNeeded(RessourceBatchDTO ressourceBatchDTO, double minAmount) {
        try (Connection connection = DriverManager.getConnection(url + userName + "&" + pass)) {
            if (ressourceBatchDTO.getRessourceBatchAmount() < minAmount) {
                PreparedStatement pStmt = connection.prepareStatement("UPDATE ressource_batches SET is_rest = ? WHERE ressource_batch_id = ?");
                pStmt.setInt(1,1);
                pStmt.setInt(2,ressourceBatchDTO.getRessourceBatchId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean updateRessourceBatchAmount(RessourceBatchDTO ressourceBatch, double usedAmount) {

       boolean success = false;
       double restAmount = ressourceBatch.getRessourceBatchAmount();
       if(ressourceBatch.getRessourceBatchAmount() > usedAmount){
           restAmount = ressourceBatch.getRessourceBatchAmount() - usedAmount;
           success = true;
       }
        try (Connection connection = DriverManager.getConnection(url + userName + "&" + pass)) {

            PreparedStatement pStmt = connection.prepareStatement("UPDATE ressource_batches SET ressource_batch_amount_in_mg = ? WHERE ressource_batch_id = ?");

            pStmt.setDouble(1, restAmount);
            pStmt.setInt(2, ressourceBatch.getRessourceBatchId());
            pStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
       return success;
    }

    public List<RessourceBatchDTO> getNotRestRessourceBatchList() {
        List<RessourceBatchDTO> ressourceBatches = new ArrayList<>();
        RessourceBatchDTO ressourceBatch = null;

        try(Connection connection = DriverManager.getConnection(url + userName + "&" + pass)){

            PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM ressource_batches WHERE is_rest = 0");
            ResultSet resultSet = pStmt.executeQuery();

            while(resultSet.next()){
                ressourceBatch = new RessourceBatchDTO(resultSet.getInt(1),resultSet.getDouble(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getInt(5));
                ressourceBatches.add(ressourceBatch);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ressourceBatches;
    }
}
