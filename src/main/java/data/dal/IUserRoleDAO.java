package data.dal;

import data.dto.DALException;
import data.dto.RoleDTO;
import data.dto.UserDTO;
import data.dto.UserRoleDTO;

import java.io.Serializable;
import java.util.List;

public interface IUserRoleDAO extends Serializable {
    void createUserRoles(int userId, int roleId) throws DALException;

    UserDTO getUserIdFromUserDAO(int userId) throws DALException;

    RoleDTO getRoleIdFromUserDAO(int roleId) throws DALException;

    List<UserRoleDTO> getRolesList(int userId)throws DALException;

    void deleteUserRoles(int userId) throws DALException;
}
