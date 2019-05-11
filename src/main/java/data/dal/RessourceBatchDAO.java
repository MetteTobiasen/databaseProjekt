package data.dal;

import data.dto.*;

import java.sql.*;

public class RessourceBatchDAO {
    private final String url = "jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185131?";
    private final String userName= "user=s185131";
    private final String pass = "password=f641omiIhm3Ly1oQR5khj";


   public void createRessourceBatchDTO (RessourceBatchDTO ressourceBatch, int ingredientId, String producerName)throws DALException{
       try(Connection connection = DriverManager.getConnection(url + userName +"&"+ pass)){

           IngredientDTO ingredient = new IngredientDTO(ingredientId);
           ProducerDTO producer = new ProducerDTO(producerName);

           PreparedStatement pStmt = connection.prepareStatement(
                   "INSERT INTO ressource_batches (ressource_batch_amount_in_mg, is_rest, producer_name, ingredient_id) VALUES(?,?,?,?)");

           pStmt.setInt(1, ressourceBatch.getRessourceAmount());
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

            ressourceBatch = new RessourceBatchDTO(ressourceBatchId,resultset.getInt(2),resultset.getInt(3),resultset.getString(4), resultset.getInt(5));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ressourceBatch;
    }



}
