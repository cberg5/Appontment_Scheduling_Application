package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUser {

    private static User loginUser;

    public static User getLoginUser() {
        return loginUser;

    }

    public static boolean validateLogin(String username, String password) throws SQLException {

        String sql = "SELECT * FROM users WHERE User_Name = '" + username + "' AND Password = '" + password + "'";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        if (rs.next()){
            int userId = rs.getInt("User_ID");
            loginUser = new User(userId, username);
            return true;
        }
        else{
            return false;
        }

    }

    public static ObservableList<User> getAllUsers() throws SQLException {

        ObservableList<User> allUsers = FXCollections.observableArrayList();
        User user;

        String sql = "SELECT * FROM users";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            user = new User(rs.getInt("User_ID"), rs.getString("User_Name"));
            allUsers.add(user);
        }

        return allUsers;
    }
}
