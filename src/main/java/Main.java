import data.dal.IUserDAO;
import data.dal.ProductBatchDAO;
import data.dal.RoleDAO;
import data.dal.UserDAO;
import data.dto.DALException;
import data.dto.ProductBatchDTO;
import data.dto.RoleDTO;
import data.dto.UserDTO;
import sun.util.calendar.LocalGregorianCalendar;

import java.sql.Date;

public class Main {

    public static void main(String[] args) throws DALException {
        //UserDAO userDAO = new UserDAO();
        //UserDTO user = new UserDTO(23,"Hejsa");

        //userDAO.createUser(user);


        //tester createProductBatch in productBatchDAO
        //ProductBatchDAO productBatchDAO = new ProductBatchDAO();
        //String date = "2019-10-12";

        //ProductBatchDTO productBatch = new ProductBatchDTO(1, Date.valueOf(date),23,4);

        //productBatchDAO.createProductBatch(productBatch);
        //System.out.println(productBatchDAO.getProductBatch(2));

        RoleDAO roleDAO = new RoleDAO();
        RoleDTO role = new RoleDTO(1,"admin");

        roleDAO.createRole(role);

        RoleDTO role1 = new RoleDTO(2, "LabKing");

        roleDAO.createRole(role1);


        System.out.println(roleDAO.getRoleList());


       //System.out.println(user.toString());
    }


}
