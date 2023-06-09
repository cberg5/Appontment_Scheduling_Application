package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Contact;
import sample.Model.Customer;
import sample.Model.FLDivision;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBFLDivision {

    public static ObservableList<FLDivision> getAllDivisions() throws SQLException {

        ObservableList<FLDivision> allDivisions = FXCollections.observableArrayList();
        FLDivision flDivision;

        String sql = "SELECT * FROM first_level_divisions";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            flDivision = new FLDivision(rs.getInt("Division_ID"), rs.getString("Division"), 3);
            allDivisions.add(flDivision);
        }

        return allDivisions;
    }

    public static ObservableList<FLDivision> getUSDivisions() throws SQLException {

        ObservableList<FLDivision> allUSDivisions = FXCollections.observableArrayList();
        FLDivision flDivision;

        String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = 1";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            flDivision = new FLDivision(rs.getInt("Division_ID"), rs.getString("Division"), 1);
            allUSDivisions.add(flDivision);
        }

        return allUSDivisions;
    }

    public static ObservableList<FLDivision> getUKDivisions() throws SQLException {

        ObservableList<FLDivision> allUKDivisions = FXCollections.observableArrayList();
        FLDivision flDivision;

        String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = 2";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            flDivision = new FLDivision(rs.getInt("Division_ID"), rs.getString("Division"), 2);
            allUKDivisions.add(flDivision);
        }

        return allUKDivisions;
    }

    public static ObservableList<FLDivision> getCADivisions() throws SQLException {

        ObservableList<FLDivision> allCADivisions = FXCollections.observableArrayList();
        FLDivision flDivision;

        String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = 3";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            flDivision = new FLDivision(rs.getInt("Division_ID"), rs.getString("Division"), 3);
            allCADivisions.add(flDivision);
        }

        return allCADivisions;
    }


}
