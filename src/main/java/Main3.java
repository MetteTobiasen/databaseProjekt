import data.dal.IUserDAO;
import data.dal.RoleDAO;
import data.dal.UserDAO;
import data.dal.UserRoleDAO;
import data.dto.DALException;
import data.dto.RoleDTO;
import data.dto.UserDTO;
import data.dto.UserRoleDTO;

public class Main3 {

    public static void main(String[] args) throws DALException, IUserDAO.DALException {

        UserRoleDAO userRoleDAO = new UserRoleDAO();
        UserDAO userDAO = new UserDAO();
        UserDTO user = new UserDTO("Bo");

        //userDAO.deleteUser(5);
        //userDAO.deleteUser(6);

        userDAO.createUser(user);

        //userRoleDAO.createUserRoles(1,1);
        //userRoleDAO.createUserRoles(1,4);

        //userRoleDAO.deleteUserRoles(1);

        //System.out.println(userRoleDAO.getRolesList(1));

        //userDAO.deleteUser(1);

    }
}
