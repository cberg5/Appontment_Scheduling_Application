package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                    rs.getString("Start"), rs.getString("End"), rs.getInt("Customer_ID"),
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

    }

}
