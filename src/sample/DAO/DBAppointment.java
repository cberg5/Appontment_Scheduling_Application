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

/**
 * The DBAppointment class.
 * Used to retrieve, add and update appointments in the database. Additionally checks for appointment overlaps and upcoming appointments.
 */
public class DBAppointment {

    /**
     * A method to return a list of all appointments in the database.
     * Runs SQL query and runs a while loop to populate a list of all appointments.
     * @return list of all appointments
     * @throws SQLException
     */
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

    /**
     * A method to retrieve appointments that are within 15 minutes of when the method is called.
     * Converts the local system time to UTC time and queries the database for any appointments within the
     * time of when the method is called and 15 minutes from then.
     * @return appointment
     * @throws SQLException
     */
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

    /**
     * A method to check if the appointment parameter is overlapping with any appointment in the database with the same customer ID.
     * Runs a SQL query to get a list of all appointments that are of the same customer. Then checks the list of all
     * appointments of the customer against the new customer appointment to see if the times/dates have any overlap
     * @param customerAppointment
     * @return true if there is overlap, false if there is no overlap.
     * @throws SQLException
     */
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

    /**
     * A method to check if the updated appointment parameter is overlapping with any appointment in the database with the same customer ID.
     * Runs a SQL query to get a list of all appointments that are of the same customer but of different appointment IDs.
     * Then checks the list of all appointments of the customer against the new customer appointment to see
     * if the times/dates have any overlap.
     * @param customerAppointment
     * @return true if there is overlap, false if there is no overlap.
     * @throws SQLException
     */
    public static boolean checkOverlapUpdated(Appointment customerAppointment) throws SQLException {

        boolean appointmentOverlap = false;
        ObservableList<Appointment> allCustomerAppointments = FXCollections.observableArrayList();
        Appointment appointment;
        String sql = "SELECT * FROM appointments WHERE customer_ID = " + customerAppointment.getCustomerId() + " AND Appointment_ID <> " + customerAppointment.getId();
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

    /**
     * A method to add a new appointment into the database.
     * Runs a SQL insert statement to add a new appointment into the database
     * @param appointment
     * @returnmrows affected
     * @throws SQLException
     */
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

    /**
     * A method to update an appointment in the database.
     * Runs a SQL update statement to update an existing appointment in the database.
     * @param appointment
     * @return rows affected
     * @throws SQLException
     */
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

    /**
     * A method to delete an appointment from the database.
     * Runs a SQL statement to delete a specific appointment from the database based on the appointment ID.
     * @param appointment
     * @return rows affected
     * @throws SQLException
     */
    public static int deleteAppointment(Appointment appointment) throws SQLException {

        String sql = "DELETE FROM appointments WHERE Appointment_ID = " + appointment.getId();
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);

        int rowsAffected = ps.executeUpdate();

        return rowsAffected;

    }

    /**
     * A method to delete an appointment from the database.
     * Runs a SQL statement to delete any appointment from the database based on the customer ID.
     * @param customerId
     * @return rows affected
     * @throws SQLException
     */
    public static int deleteAppointment(int customerId) throws SQLException {

        String sql = "DELETE FROM appointments WHERE Customer_ID = " + customerId;
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);

        int rowsAffected = ps.executeUpdate();

        return rowsAffected;

    }
}
