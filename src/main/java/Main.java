import data.dal.IUserDAO;
import data.dal.UserDAO;
import data.dto.UserDTO;

public class Main {

    public static void main(String[] args) throws IUserDAO.DALException {
        UserDAO userDAO = new UserDAO();
        UserDTO user = new UserDTO(23,"halløjsa2222222222222222");

        //userDAO.createUser(user);

//        userDAO.getUser(30);

        userDAO.updateUser(user);







       //System.out.println(user.toString());
    }


}
