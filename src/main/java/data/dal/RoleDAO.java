package data.dal;

import data.dto.DALException;
import data.dto.RoleDTO;

import javax.xml.transform.Result;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO implements IRoleDAO {
    private final String url = "jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185131?";
    private final String userName= "user=s185131";
    private final String pass = "password=f641omiIhm3Ly1oQR5khj";

@Override
public void createRole(RoleDTO role)throws DALException {
    try(Connection connection = DriverManager.getConnection(url + userName + "&" + pass)){
        PreparedStatement pStmt = connection.prepareStatement("INSERT INTO roles_db (rolename) VALUES(?)");

        pStmt.setString(1, role.getRoleName());

        pStmt.executeUpdate();


    } catch (SQLException e) {
        e.printStackTrace();
    }
}

@Override
public List<RoleDTO> getRoleList() throws DALException{
    List<RoleDTO> roles = new ArrayList<>();
    RoleDTO role = null;

    try(Connection connection = DriverManager.getConnection(url + userName +"&" + pass)){
        PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM roles_db");
        ResultSet resultSet = pStmt.executeQuery();

        while(resultSet.next()){

            role = new RoleDTO();
            role.setRoleId(resultSet.getInt(1));
            role.setRoleName(resultSet.getString(2));

            roles.add(role);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return roles;
}

@Override
public RoleDTO getRole(int roleId) throws DALException{
    RoleDTO role = null;
    try(Connection connection = DriverManager.getConnection(url + userName + "&" + pass)){
        PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM roles_db WHERE role_id = ?");

        pStmt.setInt(1, roleId);
        ResultSet resultSet = pStmt.executeQuery();
        resultSet.next();

        role = new RoleDTO();
        role.setRoleId(roleId);
        role.setRoleName(resultSet.getString(2));


    } catch (SQLException e) {
        e.printStackTrace();
    }
    return role;
}

@Override
public RoleDTO getRoleName(int roleId) throws DALException{
    RoleDTO roleName = null;

    try(Connection connection = DriverManager.getConnection(url + userName + "&" + pass)){
        PreparedStatement pStmt = connection.prepareStatement("SELECT rolename FROM roles_db WHERE role_id = ?");

        pStmt.setInt(1, roleId);
        ResultSet resultSet = pStmt.executeQuery();
        resultSet.next();

        roleName = new RoleDTO();
        roleName.setRoleName(resultSet.getString(2));


    } catch (SQLException e) {
        e.printStackTrace();
    }
    return roleName;

}

@Override
public void updateRole(RoleDTO role) throws DALException{
    try(Connection connection = DriverManager.getConnection(url + userName + "&" + pass)){

        PreparedStatement pStmt = connection.prepareStatement("UPDATE roles_db SET rolename = ? WHERE role_id = ?");

        pStmt.setString(1, role.getRoleName());
        pStmt.setInt(2, role.getRoleId());
        pStmt.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

@Override
public void deleteRole(int roleId) throws DALException{
    try(Connection connection = DriverManager.getConnection(url + userName + "&" + pass)){

        PreparedStatement pStmt = connection.prepareStatement("DELETE FROM roles_db WHERE role_id = ?");

        pStmt.setInt(1,roleId);
        pStmt.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

}
