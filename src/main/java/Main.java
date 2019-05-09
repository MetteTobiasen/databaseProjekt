import data.dal.IUserDAO;
import data.dal.ProductBatchDAO;
import data.dal.RoleDAO;
import data.dal.UserDAO;
import data.dto.ProductBatchDTO;
import data.dto.RoleDTO;
import data.dto.UserDTO;
import sun.util.calendar.LocalGregorianCalendar;

import java.util.Date;

public class Main {

    public static void main(String[] args) throws IUserDAO.DALException {
        UserDAO userDAO = new UserDAO();
        //UserDTO user = new UserDTO(23,"Hejsa");

        //userDAO.createUser(user);

        //tester createProductBatch in productBatchDAO
        ProductBatchDAO productBatchDAO = new ProductBatchDAO();
        //ProductBatchDTO productBatch = new ProductBatchDTO(1,new java.sql.Date(2019-8-5),23,3);

        //productBatchDAO.createProductBatch(productBatch);
        //System.out.println(productBatchDAO.getProductBatch(2));

        RoleDAO roleDAO = new RoleDAO();
        RoleDTO role = new RoleDTO(5,"admin");

        roleDAO.createRole(role);

        RoleDTO role1 = new RoleDTO(3, "LabKing");

        roleDAO.updateRole(role1);


        System.out.println(roleDAO.getRoleList());










       //System.out.println(user.toString());
    }


}
