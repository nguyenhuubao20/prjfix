
package model;

public class User {

    private int userId;
    private String userName;
    private int roleId;
    private String email;
    private String password;
    private String role;

    public User() {
    }

    public User(String userName, int roleID, String email, String password, String role) {
        this.userName = userName;
        this.roleId = roleID;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(int userId, String userName, int roleID, String email, String password, String role) {
        this.userId = userId;
        this.userName = userName;
        this.roleId = roleID;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", userName=" + userName + ", roleID=" + roleId + ", email=" + email + ", password=" + password + ", role=" + role + '}';
    }

}
