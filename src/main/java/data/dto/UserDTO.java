package data.dto;

import java.io.Serializable;
import java.util.List;

public class UserDTO implements Serializable, IUserDTO {
    @Override
    public int getUserId() {
        return 0;
    }

    @Override
    public void setUserId(int userId) {

    }

    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public void setUserName(String userName) {

    }

    //@Override
    //public String getIni() {
    //    return null;
    //}

    //@Override
    //public void setIni(String ini) {

    //}

    //@Override
    //public List<String> getRoles() {
    //    return null;
    //}

    //@Override
    //public void setRoles(List<String> roles) {

    //}

    //@Override
    //public void addRole(String role) {

    //}

    //@Override
    //public boolean removeRole(String role) {
    //    return false;
    //} //TODO fix
}
