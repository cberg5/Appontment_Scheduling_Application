package sample.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUser {

    public static boolean validateLogin(String username, String password) throws SQLException {

        String sql = "SELECT * FROM users WHERE User_Name = '" + username + "' AND Password = '" + password + "'";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        rs.next();

        if (rs.getString("User_Name").equals(username) && rs.getString("Password").equals(password)){
            return true;
        }
        else{
            return false;
        }

    }
}
