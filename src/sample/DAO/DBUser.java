package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The DBUser class. Used to validate user login credentials and retrieve user data from the database.
 */
public class DBUser {

    private static User loginUser;

    /**
     * A static method to return the logged in user data.
     * @return
     */
    public static User getLoginUser() {
        return loginUser;

    }

    /**
     * A method to validate that the username and password match the database information.
     * Runs a SQL query to see if there is a matching user based on the provided username and password.
     * Checks to see if the SQL query found a match and if so creates a new logged in user object.
     * @param username
     * @param password
     * @return true if valid login, false if login not valid
     * @throws SQLException
     */
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

    /**
     * A method to return a list of all users in the database.
     * Runs a SQL query to get data on all users in the database. Runs a while loop to populate a list of all
     * users in the database.
     * @return list of all users
     * @throws SQLException
     */
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
