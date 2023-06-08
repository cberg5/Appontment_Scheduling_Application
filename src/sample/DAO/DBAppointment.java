package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Appointment;
import sample.Utilities.TimeUtility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
                    rs.getTimestamp("Start").toLocalDateTime(), rs.getTimestamp("End").toLocalDateTime(), rs.getInt("User_ID"),
                    rs.getInt("Customer_ID"), rs.getInt("Contact_ID"));
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
                    rs.getTimestamp("Start").toLocalDateTime(), rs.getTimestamp("End").toLocalDateTime(), rs.getInt("User_ID"),
                    rs.getInt("Customer_ID"), rs.getInt("Contact_ID"));
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
                    rs.getTimestamp("Start").toLocalDateTime(), rs.getTimestamp("End").toLocalDateTime(), rs.getInt("User_ID"),
                    rs.getInt("Customer_ID"), rs.getInt("Contact_ID"));
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
                    rs.getTimestamp("Start").toLocalDateTime(), rs.getTimestamp("End").toLocalDateTime(), rs.getInt("User_ID"),
                    rs.getInt("Customer_ID"), rs.getInt("Contact_ID"));

                    return appointment;
        }
        else {
            return null;
        }
    }

    public static boolean checkOverlap(Appointment customerAppointment) throws SQLException {

        boolean appointmentOverlap = false;
        ObservableList<Appointment> allCustomerAppointments = FXCollections.observableArrayList();
        Appointment appointment;
        String sql = "SELECT * FROM appointments WHERE customer_ID = " + customerAppointment.getCustomerId();
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            appointment = new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"),
                    rs.getString("Description"), rs.getString("Location"), rs.getString("Type"),
                    rs.getTimestamp("Start").toLocalDateTime(), rs.getTimestamp("End").toLocalDateTime(), rs.getInt("User_ID"),
                    rs.getInt("Customer_ID"), rs.getInt("Contact_ID"));
            allCustomerAppointments.add(appointment);
        }

        for (Appointment appt : allCustomerAppointments) {

            if (appt.getStartDateTime().isEqual(customerAppointment.getStartDateTime()) || appt.getEndDateTime().isEqual(customerAppointment.getEndDateTime())) {
                appointmentOverlap = true;
            }
            if (appt.getStartDateTime().isBefore(customerAppointment.getStartDateTime()) && appt.getEndDateTime().isAfter(customerAppointment.getStartDateTime())) {
                appointmentOverlap = true;
            }
            if (appt.getStartDateTime().isAfter(customerAppointment.getStartDateTime()) && appt.getStartDateTime().isBefore(customerAppointment.getEndDateTime())) {
                appointmentOverlap = true;
            }
        }
        return appointmentOverlap;
    }

    public static int addAppointment(Appointment appointment) throws SQLException {

        String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID)" +
                " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, appointment.getTitle());
        ps.setString(2, appointment.getDescription());
        ps.setString(3, appointment.getLocation());
        ps.setString(4, appointment.getType());
        ps.setTimestamp(5, Timestamp.valueOf(appointment.getStartDateTime()));
        ps.setTimestamp(6, Timestamp.valueOf(appointment.getEndDateTime()));
        ps.setInt(7, appointment.getCustomerId());
        ps.setInt(8, appointment.getUserId());
        ps.setInt(9, appointment.getContactId());

        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    public static int updateAppointment(Appointment appointment) throws SQLException {

        String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?," +
                " Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = " + appointment.getId();
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, appointment.getTitle());
        ps.setString(2, appointment.getDescription());
        ps.setString(3, appointment.getLocation());
        ps.setString(4, appointment.getType());
        ps.setTimestamp(5, Timestamp.valueOf(appointment.getStartDateTime()));
        ps.setTimestamp(6, Timestamp.valueOf(appointment.getEndDateTime()));
        ps.setInt(7, appointment.getCustomerId());
        ps.setInt(8, appointment.getUserId());
        ps.setInt(9, appointment.getContactId());

        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }
}
