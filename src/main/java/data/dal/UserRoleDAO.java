package data.dal;

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

    public void createUserRoles(UserRoleDTO userRole, int userId) throws IUserDAO.DALException {
        try (Connection connection = DriverManager.getConnection(url + userName + "&" + pass)) {
            PreparedStatement pStmt = connection.prepareStatement("INSERT INTO user_roles VALUES(?,?) WHERE user_id = " + userId);

            pStmt.setInt(1, userId);
            pStmt.setInt(2, userRole.getRoleId());

            pStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        public UserRoleDAO getUserRoles(int userId) throws IUserDAO.DALException {

            UserDTO user = null;
            RoleDTO role = null;

            try (Connection connection = DriverManager.getConnection(url + userName + "&" + pass)) {
                PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM user_roles WHERE user_id = ?");

                pStmt.setInt(1, userId);
                ResultSet resultSet = pStmt.executeQuery();
                resultSet.next();

                user = new UserDTO();
                user.setUserId(userId);

            } catch (SQLException e) {
                e.printStackTrace();
            }


        }


    }

    }
}
