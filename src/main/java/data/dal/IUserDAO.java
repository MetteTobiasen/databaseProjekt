package data.dal;

import data.dto.IUserDTO;
import data.dto.UserDTO;

import java.util.List;

public interface IUserDAO {

    //Create
    void createUser(UserDTO user) throws DALException;

    //Read
    UserDTO getUser(int userId) throws DALException;

    List<UserDTO> getUserList() throws DALException;

    //Update
    void updateUser(UserDTO user) throws DALException;

    //Delete
    void deleteUser(int userId) throws DALException;

    public class DALException extends Exception {
        //Til Java serialisering...
        private static final long serialVersionUID = 7355418246336739229L;

        public DALException(String msg, Throwable e) {
            super(msg,e);
        }

        public DALException(String msg) {
            super(msg);
        }

    }


}
