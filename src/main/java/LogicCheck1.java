import BusinessLogic.BusinessLogic;
import data.dal.IUserDAO;
import data.dal.RoleDAO;
import data.dal.UserDAO;
import data.dal.UserRoleDAO;
import data.dto.DALException;
import data.dto.RoleDTO;
import data.dto.UserDTO;

public class LogicCheck1 {



    public static void main(String[] args) throws DALException, IUserDAO.DALException {

        BusinessLogic businessLogic = new BusinessLogic();

//        UserDTO userDTO = new UserDTO(10,"recipeCheck");
//
//        UserDAO userDAO = new UserDAO();
//        userDAO.createUser(userDTO);
//
//        RoleDAO roleDAO = new RoleDAO();
//
//        RoleDTO roleDTO = new RoleDTO(6,"farmaceut");
//        roleDAO.createRole(roleDTO);
//
//        UserRoleDAO userRoleDAO = new UserRoleDAO();
//
//        userRoleDAO.createUserRoles(10,5);
//        userRoleDAO.createUserRoles(10,6);


        System.out.println(businessLogic.createRecipe(10, "recipeTest1"));

    }
}
