import data.dal.IUserDAO;
import data.dal.UserDAO;
import data.dto.UserDTO;

public class Main {

    public static void main(String[] args) throws IUserDAO.DALException {
        UserDAO userDAO = new UserDAO();
        //UserDTO user = new UserDTO(23,"Hejsa");

        //userDAO.createUser(user);

        userDAO.getUser(30);








       //System.out.println(user.toString());
    }


}
