import data.dal.IRoleDAO;
import data.dal.RoleDAO;
import data.dto.DALException;
import data.dto.RoleDTO;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class DALTestRoles {
    IRoleDAO roleDAO = new RoleDAO();

    @Test
    public void testCreateUser() throws DALException {

        RoleDTO testRole = new RoleDTO();
        testRole.setRoleName("Laborant2");

        roleDAO.createRole(testRole);
        RoleDTO receivedRole = roleDAO.getRoleName(12);
        String actual = receivedRole.getRoleName();
        String expected = testRole.getRoleName();
        assertEquals(expected, actual);

    }

}
