package data.dal;

import data.dto.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RessourceAmountsDAO {

    private final String url = "jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185131?";
    private final String userName= "user=s185131";
    private final String pass = "password=f641omiIhm3Ly1oQR5khj";


    public void createRessourceAmountDTO (RessourceAmountsDTO ressourceAmount, int ressourceBatchId, int productBatchId)throws DALException {
        try(Connection connection = DriverManager.getConnection(url + userName +"&"+ pass)){

            RessourceBatchDTO ressourceBatch = new RessourceBatchDTO(ressourceBatchId);
            ProductBatchDTO productBatch = new ProductBatchDTO(productBatchId);

            PreparedStatement pStmt = connection.prepareStatement(
                    "INSERT INTO ressource_amounts (ressource_batch_used_amount_in_mg, ressource_batch_id, product_batch_id) VALUES(?,?,?)");

            pStmt.setInt(1, ressourceAmount.getRessourceBatchUsedAmount());
            pStmt.setInt(2, ressourceBatch.getRessourceBatchId());
            pStmt.setInt(3, productBatch.getProductBatchId());

            pStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
