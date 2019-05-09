package data.dto;

import data.dal.UserDAO;

import java.io.Serializable;
import java.util.List;

public class UserDTO implements Serializable, IUserDTO {

    private static final long serialVersionUID = 4545864587995944260L;
    private int	userId;
    private String userName;

//    private List<String> roles;

    public UserDTO(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public UserDTO(int userId){
        this.userId = userId;
    }

    public UserDTO() {

    }


    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
    this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserDTO [userId=" + userId + ", userName=" + userName + "]";
    }

}
