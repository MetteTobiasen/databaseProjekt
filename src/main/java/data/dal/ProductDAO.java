package data.dal;

import data.dto.ProductDTO;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements Serializable {

    private final String url = "jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185131?";
    private final String userName= "user=s185131";
    private final String pass = "password=f641omiIhm3Ly1oQR5khj";

    public void createProduct(ProductDTO product) throws IUserDAO.DALException {
        try(Connection connection = DriverManager.getConnection(url + userName +"&"+ pass)){

            PreparedStatement pStmt = connection.prepareStatement("INSERT INTO products VALUES(?,?,?)");

            pStmt.setInt(1, product.getProductId());
            pStmt.setString(2 , product.getProductName());
            pStmt.setInt(3 , product.getProductAmount());

            pStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ProductDTO> getProductList() throws IUserDAO.DALException {
        List<ProductDTO> products = new ArrayList<>();
        ProductDTO product = null;

        try(Connection connection = DriverManager.getConnection(url + userName +"&"+ pass)){

            PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM products");

            ResultSet resultset = pStmt.executeQuery();

            while(resultset.next()){
                product = new ProductDTO();
                product.setProductId(resultset.getInt(1));
                product.setProductName(resultset.getString(2));
                product.setProductAmount(resultset.getInt(3));

                // adds product to productlist
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public ProductDTO getProduct(int productId) throws IUserDAO.DALException {
        ProductDTO product = null;

        try(Connection connection = DriverManager.getConnection(url + userName +"&"+ pass)){

            PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM products WHERE product_id = ? ");

            pStmt.setInt(1, productId);

            ResultSet resultset = pStmt.executeQuery();

            resultset.next();

            product.setProductId(productId);
            product.setProductName(resultset.getString(2));
            product.setProductAmount(resultset.getInt(3));



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    public void updateProduct(ProductDTO product) throws IUserDAO.DALException {

        try(Connection connection = DriverManager.getConnection(url + userName +"&"+ pass)){

            PreparedStatement pStmt = connection.prepareStatement("UPDATE products SET product_name = ?, product_amount_in_stk = ? WHERE product_id = " + product.getProductId());

            pStmt.setString(1, product.getProductName());
            pStmt.setInt(2, product.getProductAmount());

            pStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProductAmount(ProductDTO product) throws IUserDAO.DALException {

        try(Connection connection = DriverManager.getConnection(url + userName +"&"+ pass)){

            PreparedStatement pStmt = connection.prepareStatement("UPDATE products SET product_amount_in_stk = ? WHERE product_id = " + product.getProductId());

            pStmt.setInt(1, product.getProductAmount());

            pStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int productId) throws IUserDAO.DALException {

        try(Connection connection = DriverManager.getConnection(url + userName +"&"+ pass)){

            PreparedStatement pStmt = connection.prepareStatement("DELETE products WHERE product_id = " + productId);

            pStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // mangler måske metode getProductAmount

}
