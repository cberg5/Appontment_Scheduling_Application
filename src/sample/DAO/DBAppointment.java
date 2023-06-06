package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Appointment;
import sample.Utilities.TimeUtility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DBAppointment {

    public static ObservableList<Appointment> getAllAppointments() throws SQLException {

        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        Appointment appointment;

        String sql = "SELECT * FROM appointments";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {

            appointment = new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"),
                    rs.getString("Description"), rs.getString("Location"), rs.getString("Type"),
                    rs.getTimestamp("Start").toLocalDateTime(), rs.getTimestamp("End").toLocalDateTime(), rs.getInt("Customer_ID"),
                    rs.getInt("User_ID"), rs.getInt("Contact_ID"));
            allAppointments.add(appointment);

        }
        return allAppointments;
    }

    public static ObservableList<Appointment> getMonthAppointments() throws SQLException {

        ObservableList<Appointment> monthAppointments = FXCollections.observableArrayList();
        Appointment appointment;
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime month = LocalDateTime.now().plusMonths(1);
        String sql = "SELECT * FROM appointments WHERE Start >= '" + now + "' AND Start <= '" + month + "'";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            appointment = new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"),
                    rs.getString("Description"), rs.getString("Location"), rs.getString("Type"),
                    rs.getTimestamp("Start").toLocalDateTime(), rs.getTimestamp("End").toLocalDateTime(), rs.getInt("Customer_ID"),
                    rs.getInt("User_ID"), rs.getInt("Contact_ID"));
            monthAppointments.add(appointment);
        }
        return monthAppointments;
    }

    public static ObservableList<Appointment> getWeekAppointments() throws SQLException {

        ObservableList<Appointment> weekAppointments = FXCollections.observableArrayList();
        Appointment appointment;
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime week = LocalDateTime.now().plusWeeks(1);
        String sql = "SELECT * FROM appointments WHERE Start >= '" + now + "' AND Start <= '" + week + "'";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            appointment = new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"),
                    rs.getString("Description"), rs.getString("Location"), rs.getString("Type"),
                    rs.getTimestamp("Start").toLocalDateTime(), rs.getTimestamp("End").toLocalDateTime(), rs.getInt("Customer_ID"),
                    rs.getInt("User_ID"), rs.getInt("Contact_ID"));
            weekAppointments.add(appointment);
        }
        return weekAppointments;
    }

    public static Appointment get15minAppointment() throws SQLException {

        Appointment appointment;
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime convertedLocalDT = TimeUtility.convertLocaltoUtc(now);
        LocalDateTime convertedLocalFifteen = convertedLocalDT.plusMinutes(15);

        String sql = "SELECT * FROM appointments WHERE Start >= '" + convertedLocalDT + "' AND Start <= '" + convertedLocalFifteen + "'";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            appointment = new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"),
                    rs.getString("Description"), rs.getString("Location"), rs.getString("Type"),
                    rs.getTimestamp("Start").toLocalDateTime(), rs.getTimestamp("End").toLocalDateTime(), rs.getInt("Customer_ID"),
                    rs.getInt("User_ID"), rs.getInt("Contact_ID"));

                    return appointment;
        }
        else {
            return null;
        }
    }

    public static void addAppointment(Appointment appointment) {

    }
}
