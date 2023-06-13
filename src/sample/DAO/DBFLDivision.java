package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.FLDivision;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The DBFLDivision class. Used to access the database to retrieve division data.
 */
public class DBFLDivision {

    /**
     * A static method to retrieve a list of all first level division in the database.
     * Runs a SQL query and then a while loop to populate a list of all first level divisions
     * @return List of all first level divisions
     * @throws SQLException
     */
    public static ObservableList<FLDivision> getAllDivisions() throws SQLException {

        ObservableList<FLDivision> allDivisions = FXCollections.observableArrayList();
        FLDivision flDivision;

        String sql = "SELECT * FROM first_level_divisions";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            flDivision = new FLDivision(rs.getInt("Division_ID"), rs.getString("Division"), rs.getInt("Country_ID"));
            allDivisions.add(flDivision);
        }

        return allDivisions;
    }
}
