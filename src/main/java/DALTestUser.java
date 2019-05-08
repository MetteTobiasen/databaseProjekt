import data.dal.IUserDAO;
import data.dto.UserDTO;
import data.dto.IUserDTO;
import data.dal.UserDAO;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class DALTestUser {

    IUserDAO userDAO = new UserDAO();

    @Test
    public void testCreateUser() throws IUserDAO.DALException {

        UserDTO testUser = new UserDTO();
        testUser.setUserName("LabKing");

        userDAO.createUser(testUser);
        UserDTO receivedUser = userDAO.getUser(30);
        String actual = receivedUser.getUserName();
        String expected = testUser.getUserName();
        assertEquals(expected, actual);

    }

    @Test
    public void testGetUser() throws IUserDAO.DALException {

    }

}
