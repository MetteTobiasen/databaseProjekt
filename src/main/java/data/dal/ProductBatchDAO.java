package data.dal;

import data.dto.ProductBatchDTO;
import data.dto.ProductDTO;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductBatchDAO {

    private final String url = "jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185131?";
    private final String userName= "user=s185131";
    private final String pass = "password=f641omiIhm3Ly1oQR5khj";


    public ProductBatchDTO getProductBatch(int productBatchId) throws IUserDAO.DALException {

        ProductBatchDTO productBatch = null;

        try(Connection connection = DriverManager.getConnection(url + userName +"&"+ pass)){

            PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM product_batches WHERE product_batch_id = ? ");

            pStmt.setInt(1, productBatchId);

            ResultSet resultset = pStmt.executeQuery();

            resultset.next();

            productBatch.setProductBatchId(productBatchId);
            productBatch.setProductBatchAmount(resultset.getInt(2));
            productBatch.setExpirationDate(resultset.getDate(3));
            productBatch.setRecipeId(resultset.getInt(4));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productBatch;
    }

    // Lav en metode som henter productBatches ud fra productId


    public List<ProductBatchDTO> getProductBatchList() throws IUserDAO.DALException {
        List<ProductBatchDTO> productBatches = new ArrayList<>();
        ProductBatchDTO productBatch = null;

        try(Connection connection = DriverManager.getConnection(url + userName +"&"+ pass)){

            PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM product_batches");

            ResultSet resultset = pStmt.executeQuery();

            while(resultset.next()){
                productBatch = new ProductBatchDTO();
                productBatch.setProductBatchId(resultset.getInt(1));
                productBatch.setProductBatchAmount(resultset.getInt(2));
                productBatch.setExpirationDate(resultset.getDate(3));
                productBatch.setRecipeId(resultset.getInt(4));

                // adds productBatches to productBatchList
                productBatches.add(productBatch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productBatches;
    }

    public void createProductBatch(ProductBatchDTO productBatch) throws IUserDAO.DALException {
        try(Connection connection = DriverManager.getConnection(url + userName +"&"+ pass)){

            PreparedStatement pStmt = connection.prepareStatement("INSERT INTO product_batches VALUES(?,?,?,?)");

            pStmt.setInt(1, productBatch.getProductBatchId());
            pStmt.setInt(2 , productBatch.getProductBatchAmount());
            pStmt.setDate(3, (Date) productBatch.getExpirationDate());
            pStmt.setInt(4, productBatch.getRecipeId());

            pStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProductBatchAmount(ProductBatchDTO productBatch) throws IUserDAO.DALException {

        try(Connection connection = DriverManager.getConnection(url + userName +"&"+ pass)){

            PreparedStatement pStmt = connection.prepareStatement("UPDATE product_batches SET product_batch_amount = ?, expiration_date = ?, recipe_id = ? WHERE product_batch_id = " + productBatch.getProductBatchId());

            pStmt.setInt(1, productBatch.getProductBatchAmount());
            pStmt.setDate(2, (Date) productBatch.getExpirationDate());
            pStmt.setInt(3, productBatch.getRecipeId());

            pStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProductBatch(int productBatchId) throws IUserDAO.DALException {

        try(Connection connection = DriverManager.getConnection(url + userName +"&"+ pass)){

            PreparedStatement pStmt = connection.prepareStatement("DELETE product_batches WHERE product_batch_id = " + productBatchId);

            pStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
