package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Country;
import sample.Model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCountry {

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
