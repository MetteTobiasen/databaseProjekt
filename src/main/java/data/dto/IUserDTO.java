package data.dto;

import java.util.List;

public interface IUserDTO {

    int getUserId();

    void setUserId(int userId);

    String getUserName();

    void setUserName(String userName);


    // skal fjernes da det ikke bliver brugt i databasen
    //String getIni();

    // skal fjernes da det ikke blier brugt i databasen
    //void setIni(String ini);

    //List<String> getRoles();

    //void setRoles(List<String> roles);

    //void addRole(String role);

    //boolean removeRole(String role);

}
