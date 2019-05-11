package data.dal;

import data.dto.DALException;
import data.dto.RoleDTO;

import java.io.Serializable;
import java.util.List;

public interface IRoleDAO extends Serializable {
    void createRole(RoleDTO role)throws DALException;

    List<RoleDTO> getRoleList() throws DALException;

    RoleDTO getRole(int roleId) throws DALException;

    RoleDTO getRoleName(int roleId) throws DALException;

    void updateRole(RoleDTO role) throws DALException;

    void deleteRole(int roleId) throws DALException;
}
