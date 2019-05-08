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


    public void test() throws IUserDAO.DALException {

        UserDTO testUser = new UserDTO();
        testUser.setUserId(12);
        testUser.setUserName("LabKing");

        userDAO.createUser(testUser);
        IUserDTO receivedUser = userDAO.getUser(12);
        assertEquals(testUser.getUserName(), receivedUser.getUserName());

    }

}
