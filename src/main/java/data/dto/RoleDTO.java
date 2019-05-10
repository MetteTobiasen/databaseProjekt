package data.dto;

public class RoleDTO {

    private int roleId;
    private String roleName;

    public RoleDTO(int roleId, String roleName){
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public RoleDTO(int roleId){
        this.roleId = roleId;
    }

    public RoleDTO(String roleName){
        this.roleName = roleName;
    }

    public RoleDTO() {

    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName(){
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String toString(){
        return "RoleDTO [roleId = " + roleId + ", roleName = " + roleName + "]";
    }
}
