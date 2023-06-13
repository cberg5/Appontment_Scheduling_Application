package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Country;
import sample.Model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The DBCountry class. Used to retrieve country data from the database.
 */
public class DBCountry {

    /**
     * A method to return a list of all countries in the database.
     * Runs a SQL query to find all the countries and a while loop to populate a list of all the countries.
     * @return list of all countries.
     * @throws SQLException
     */
    public static ObservableList<Country> getAllCountries() throws SQLException {

        ObservableList<Country> allCountries = FXCollections.observableArrayList();
        Country country;

        String sql = "SELECT * FROM countries";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            country = new Country(rs.getInt("Country_ID"), rs.getString("Country"));
            allCountries.add(country);
        }

        return allCountries;
    }
}
