package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

public class UserDAO extends DBContext {

    private PreparedStatement ps;
    private ResultSet rs;

    public void addUser(User user) throws Exception {
        try {
            ps = connection.prepareStatement("INSERT INTO Users (UserName, RoleID, Email, Password) VALUES (?, ?, ?, ?);");
            ps.setString(1, user.getUserName());
            ps.setInt(2, user.getRoleId());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<User> getUser() throws Exception {
        List<User> user = new ArrayList<>();
        try {
            String sqlQuery = "SELECT u.UserID, u.UserName, u.RoleID , u.Email, u.Password,r.Role\n"
                    + "FROM Users u, Roles r\n"
                    + "WHERE u.RoleID = r.RoleID";
            PreparedStatement stm = connection.prepareStatement(sqlQuery);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                User u = new User();
                u.setUserId(rs.getInt("UserID"));
                u.setUserName(rs.getString("UserName"));
                u.setRoleId(rs.getInt("RoleID"));
                u.setEmail(rs.getString("Email"));
                u.setPassword(rs.getString("Password"));
                u.setRole(rs.getString("Role"));
                user.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public User getUserByEmail(String email) throws Exception {
        String sql = "SELECT u.UserID,  u.UserName, u.RoleID , u.Email, u.Password,r.Role\n"
                + "FROM Users u, Roles r\n"
                + "WHERE Email =  ? AND u.RoleID = r.RoleID";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("UserID"),
                        rs.getString("userName"),
                        rs.getInt("RoleID"),
                        email,
                        rs.getString("Password"),
                        rs.getString("Role"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public User getAccount(String email, String password) throws Exception {
        String sql = "SELECT u.UserID,  u.UserName, u.RoleID , u.Email, u.Password,r.Role\n"
                + "FROM Users u, Roles r\n"
                + "WHERE Email =  ? AND Password = ? AND u.RoleID = r.RoleID";

        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, email);
        stm.setString(2, password);

        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            return new User(rs.getInt("UserID"),
                    rs.getString("userName"),
                    rs.getInt("RoleID"),
                    email,
                    password,
                    rs.getString("Role"));
        }
        return null;
    }

//    public static void main(String[] args) {
//
//        UserDAO a = new UserDAO();
//        try {
//            System.out.println(a.getAccount("student@example.com", "1"));
//
//        } catch (Exception ex) {
//            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
}
