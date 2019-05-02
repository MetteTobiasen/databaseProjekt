package data.dal;

import data.dto.IUserDTO;
import data.dto.UserDTO;


import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDAO implements Serializable, data.dal.IUserDAO{
    private final String url = "jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185131?";
    private final String userName= "user=s185131";
    private final String pass = "password=f641omiIhm3Ly1oQR5khj";

    @Override
    public void createUser(IUserDTO user) throws DALException {
        try(Connection connection = DriverManager.getConnection(url + userName +"&"+ pass)){   // med det syntax behøver man ikke lave final og connection.close()
            // try with resources
            PreparedStatement pStmt = connection.prepareStatement("INSERT INTO users_cdio VALUES(?,?,?,?,?,?)");

            pStmt.setInt(1, user.getUserId());
            pStmt.setString(2 ,user.getUserName());
            //pStmt.setString(3, user.getIni());
            //skal slettes
            //pStmt.setString(4, user.getCpr());
            //pStmt.setString(5, user.getPassword());

            String roles = "";
            for (String s : user.getRoles()){
                if (s.length() == 1)
                    roles = s;
                else
                    roles += s + ", ";
            }

            pStmt.setString(6, roles);
            pStmt.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public UserDTO getUser(int userId) throws IUserDAO.DALException {
        UserDTO user = null;

        // closes itself if something fails
        try(Connection connection = DriverManager.getConnection(url + userName + "&" + pass)){

            PreparedStatement pStmt = connection.prepareStatement(
                    "SELECT * FROM users_cdio WHERE user_id = ?");

            pStmt.setInt(1, userId);
            ResultSet resultSet = pStmt.executeQuery();
            resultSet.next();

            user = new UserDTO();
            user.setUserId(userId);
            user.setUserName(resultSet.getString(2));
            //user.setIni(resultSet.getString(3));
            //user.setCpr(resultSet.getString(4));
            //user.setPassword(resultSet.getString(5));

            // get roles
            String str = resultSet.getString(6);
            List<String> roles = new ArrayList<String>(Arrays.asList(str.split(", ")));
            user.setRoles(roles);

        } catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<IUserDTO> getUserList() throws DALException {
     /*   List<UserDTO> users = new ArrayList<>();
        UserDTO user = null;
        // closes itself if something fails
        try(Connection connection = DriverManager.getConnection(url + userName + "&" + pass)){
            // get all users
            PreparedStatement pStmt = connection.prepareStatement(
                    "SELECT * FROM users_cdio");
            ResultSet resultSet = pStmt.executeQuery();

            while(resultSet.next()){
                // set user object equal to results from database
                user = new UserDTO();
                user.setUserId(resultSet.getInt(1));
                user.setUserName(resultSet.getString(2));
                //user.setIni(resultSet.getString(3));
                //user.setCpr(resultSet.getString(4));
                //user.setPassword(resultSet.getString(5));

                // get roles
                String str = resultSet.getString(6);
                List<String> roles = new ArrayList<String>(Arrays.asList(str.split(", ")));
                user.setRoles(roles);

                // add user to users list
                users.add(user);*/
     return null;
            }

    @Override
    public void updateUser(IUserDTO user) throws DALException {
        try(Connection connection = DriverManager.getConnection(url + userName +"&"+ pass)){   // med det syntax behøver man ikke lave final og connection.close()
            String sqlStatement = "UPDATE users_cdio SET userName = ?, ini = ?, cpr = ?, password = ?, roles = ? WHERE user_id = " + user.getUserId();
            PreparedStatement pStmt = connection.prepareStatement(sqlStatement);

            pStmt.setString(1, user.getUserName());
            //pStmt.setString(2, user.getIni());
            //pStmt.setString(3, user.getCpr());
            //pStmt.setString(4, user.getPassword());

            String roles = "";
            for (String s : user.getRoles()){
                if (s.length() == 1)
                    roles = s;
                else
                    roles += s + ", ";
            }

            pStmt.setString(5, roles);
            pStmt.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int userId) throws DALException {
        try(Connection connection = DriverManager.getConnection(url + userName +"&"+ pass)){
            //Sletter user_id fra databasen
            String sqlStatement = "DELETE FROM users_cdio WHERE user_id = " + userId;
            PreparedStatement pStmt = connection.prepareStatement(sqlStatement);
            pStmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}

