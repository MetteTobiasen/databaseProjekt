package data.dal;

import data.dto.DALException;
import data.dto.ProducerDTO;
import data.dto.ProductBatchDTO;
import data.dto.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProducerDAO implements IProducerDAO {
    private final String url = "jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185131?";
    private final String userName= "user=s185131";
    private final String pass = "password=f641omiIhm3Ly1oQR5khj";

    @Override
    public void createProducer(ProducerDTO producer) throws DALException {
        try(Connection connection = DriverManager.getConnection(url + userName +"&"+ pass)){   // med det syntax behøver man ikke lave final og connection.close()
            // try with resources
            PreparedStatement pStmt = connection.prepareStatement("INSERT INTO producers VALUES(?,?,?,?)");

            pStmt.setString(1 ,producer.getProducerName());
            pStmt.setString(2, producer.getEmail());
            pStmt.setInt(3, producer.getAreaCode());
            pStmt.setInt(4, producer.getPhoneNumber());

            pStmt.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public ProducerDTO getProducer(String producerName) throws DALException {
        ProducerDTO producer = null;

        // closes itself if something fails
        try(Connection connection = DriverManager.getConnection(url + userName + "&" + pass)){

            PreparedStatement pStmt = connection.prepareStatement(
                    "SELECT * FROM producers WHERE producer_name = ?");

            pStmt.setString(1, producerName);
            ResultSet resultSet = pStmt.executeQuery();
            resultSet.next();

            producer = new ProducerDTO(producerName, resultSet.getString(2), resultSet.getInt(3),resultSet.getInt(4));

        } catch (SQLException e){
            e.printStackTrace();
        }
        return producer;
    }

    @Override
    public List<ProducerDTO> getProducerList() throws DALException {
        List<ProducerDTO> producers = new ArrayList<>();
        ProducerDTO producer = null;

        try(Connection connection = DriverManager.getConnection(url + userName +"&"+ pass)){

            PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM producers");

            ResultSet resultset = pStmt.executeQuery();

            while(resultset.next()){
                producer = new ProducerDTO(resultset.getString(1),resultset.getString(2),resultset.getInt(3),resultset.getInt(4));

                producers.add(producer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producers;
    }

    @Override
    public void updateProducer(ProducerDTO producer) throws DALException {

        try (Connection connection = DriverManager.getConnection(url + userName + "&" + pass)) {

            PreparedStatement pStmt = connection.prepareStatement("UPDATE producers SET email = ?, area_code = ?, phone_number = ? WHERE producer_name = ?");

            pStmt.setString(4, producer.getProducerName());
            pStmt.setString(1, producer.getEmail());
            pStmt.setInt(2, producer.getAreaCode());
            pStmt.setInt(3, producer.getPhoneNumber());
            pStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProducer(int producerName) throws DALException{
        try(Connection connection = DriverManager.getConnection(url + userName + "&" + pass)){

            PreparedStatement pStmt = connection.prepareStatement("DELETE FROM producers WHERE producer_name = ?");

            pStmt.setInt(1,producerName);
            pStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
