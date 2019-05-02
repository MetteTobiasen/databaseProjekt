package data.dal;

import java.util.List;

public class UserDAO implements data.dal.IUserDAO{
    @Override
    public void createUser(IUserDTO user) throws DALException {

    }

    @Override
    public IUserDTO getUser(int userId) throws DALException {
        return null;
    }

    @Override
    public List<IUserDTO> getUserList() throws DALException {
        return null;
    }

    @Override
    public void updateUser(IUserDTO user) throws DALException {

    }

    @Override
    public void deleteUser(int userId) throws DALException {

    }
}
