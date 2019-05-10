import data.dal.IUserDAO;
import data.dal.RoleDAO;
import data.dal.UserDAO;
import data.dal.UserRoleDAO;
import data.dto.DALException;
import data.dto.RoleDTO;
import data.dto.UserDTO;
import data.dto.UserRoleDTO;

public class Main3 {

    public static void main(String[] args) throws DALException {

        UserRoleDAO userRoleDAO = new UserRoleDAO();

        userRoleDAO.createUserRoles(1,4);

        System.out.println(userRoleDAO.getRolesList(1));

    }
}
