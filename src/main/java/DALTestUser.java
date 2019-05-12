import data.dal.IUserDAO;
import data.dto.UserDTO;
import data.dal.UserDAO;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

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

}
