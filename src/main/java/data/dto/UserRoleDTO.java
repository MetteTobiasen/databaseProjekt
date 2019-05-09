package data.dto;

public class UserRoleDTO {

    private int userId;
    private int roleId;

    public UserRoleDTO(){
    }

    public UserRoleDTO(int userId, int roleId){
        this.userId = userId;
        this.roleId = roleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String toString(){
        return "UserRoleDTO [ userId = " + userId + "roleId = " + roleId + "]";
    }
}
