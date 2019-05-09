package data.dal;

import data.dto.DALException;
import data.dto.UserDTO;
import data.dto.RoleDTO;
import data.dal.RoleDAO;
import data.dal.UserDAO;
import data.dto.UserRoleDTO;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRoleDAO implements Serializable {
    private final String url = "jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185131?";
    private final String userName = "user=s185131";
    private final String pass = "password=f641omiIhm3Ly1oQR5khj";

    public void createUserRoles(int userId, int roleId) throws DALException {

        try (Connection connection = DriverManager.getConnection(url + userName + "&" + pass)) {

            PreparedStatement pStmt = connection.prepareStatement("INSERT INTO users_roles (user_id, role_id) VALUES(?,?)");

                RoleDTO role = getRoleIdFromUserDAO(roleId);
                UserDTO user = getUserIdFromUserDAO(userId);

                pStmt.setInt(1, user.getUserId());
                pStmt.setInt(2, role.getRoleId());

                pStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public UserDTO getUserIdFromUserDAO(int userId) throws DALException {
        UserDTO user = null;

        try(Connection connection = DriverManager.getConnection(url + userName + "&" + pass)){

            PreparedStatement pStmt = connection.prepareStatement(
                    "SELECT user_id FROM users_db WHERE user_id = ?");

            pStmt.setInt(1, userId);
            ResultSet resultSet = pStmt.executeQuery();
            resultSet.next();

            user = new UserDTO(userId);

        } catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    public RoleDTO getRoleIdFromUserDAO(int roleId) throws DALException {
        RoleDTO role = null;

        try(Connection connection = DriverManager.getConnection(url + userName + "&" + pass)){

            PreparedStatement pStmt = connection.prepareStatement(
                    "SELECT role_id FROM roles_db WHERE role_id = ?");

            pStmt.setInt(1, roleId);
            ResultSet resultSet = pStmt.executeQuery();
            resultSet.next();

            role = new RoleDTO(roleId);



        } catch (SQLException e){
            e.printStackTrace();
        }
        return role;
    }



    public List<UserRoleDTO> getRolesList(int userId)throws DALException{
        List<UserRoleDTO> roles = new ArrayList<>();
        UserRoleDTO role = null;


        try(Connection connection = DriverManager.getConnection(url + userName + "&" + pass)){

            PreparedStatement pStmt = connection.prepareStatement("SELECT role_id FROM users_roles WHERE user_id = ?");

            //UserDTO user = getUserIdFromUserDAO(userId);

            pStmt.setInt(1, userId);
            ResultSet resultset = pStmt.executeQuery();

            while(resultset.next()){
                role = new UserRoleDTO(resultset.getInt(1));
                roles.add(role);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }



}
