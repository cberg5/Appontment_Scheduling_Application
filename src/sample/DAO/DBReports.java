package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The DBReports class. Used to access the database to run a series of reports for the reports menu.
 */
public class DBReports {

    /**
     * A method to return the number of appointments based on the month and type.
     * Runs a SQL query to get data of all appointments that are in the selected month and type. Then runs a while loop to count the number of appointments.
     * @param month
     * @param type
     * @return number of appointments
     * @throws SQLException
     */
    public static int appointmentsByMonthType(int month, String type) throws SQLException {
        int numAppointments = 0;

        String sql = "SELECT * FROM appointments WHERE month(Start) = ? AND Type = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, month);
        ps.setString(2, type);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            ++numAppointments;
        }

        return numAppointments;
    }

    /**
     * A method to return the number of appointments based on country.
     * Runs a SQL query to get the data of all appointments that exist within the provided country.
     * Then runs a while loop to count the number of appointments.
     * @param countryId
     * @return number of appointments
     * @throws SQLException
     */
    public static int appointmentsByCountry(int countryId) throws SQLException {
        int numAppointments = 0;

        String sql = "SELECT * FROM appointments Join customers ON " +
                "appointments.Customer_ID = customers.Customer_ID Join first_level_divisions ON " +
                "customers.Division_ID = first_level_divisions.Division_ID Join countries ON " +
                "first_level_divisions.Country_ID = countries.Country_ID WHERE countries.Country_ID = " + countryId;
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            ++numAppointments;
        }

        return numAppointments;
    }

    /**
     * A method to return a list of all appointments that exist for a provided contact.
     * Runs a SQL query to get all appointment data for the matching contact.
     * Then runs a while loop to populate a list of all appointments that are for the provided contact.
     * @param contactId
     * @return list of appointments
     * @throws SQLException
     */
    public static ObservableList<Appointment> getAppointmentsByContact(int contactId) throws SQLException {

        ObservableList<Appointment> contactAppointments = FXCollections.observableArrayList();
        Appointment appointment;

        String sql = "SELECT * FROM appointments JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID WHERE contacts.Contact_Id = " + contactId;;
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {

            appointment = new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"),
                    rs.getString("Description"), rs.getString("Location"), rs.getString("Type"),
                    rs.getTimestamp("Start").toLocalDateTime(), rs.getTimestamp("End").toLocalDateTime(), rs.getInt("User_ID"),
                    rs.getInt("Customer_ID"), rs.getInt("Contact_ID"));
            contactAppointments.add(appointment);

        }
        return contactAppointments;
    }

    /**
     * A method to return a list of all unique types of appointments in the database.
     * Runs a SQL query to get data of all unique appointment types. Runs a while loop to populate a list
     * of appointment types.
     * @return list of types
     * @throws SQLException
     */
    public static ObservableList<String> getAllTypes() throws SQLException {

        ObservableList<String> allTypes = FXCollections.observableArrayList();
        String type;

        String sql = "SELECT DISTINCT(Type) FROM appointments";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){

            type = rs.getString("Type");
            allTypes.add(type);
        }

        return allTypes;

    }

}
