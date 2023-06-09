package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBReports {

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

    public static int appointmentsByCountry(String country) throws SQLException {
        int numAppointments = 0;

        String sql = "SELECT * FROM appointments Join customers ON " +
                "appointments.Customer_ID = customers.Customer_ID Join first_level_divisions ON " +
                "customers.Division_ID = first_level_divisions.Division_ID Join countries ON " +
                "first_level_divisions.Country_ID = countries.Country_ID WHERE Country = " + country;
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            ++numAppointments;
        }

        return numAppointments;
    }

    public static ObservableList<Appointment> getAppointmentsByContact(String contact) throws SQLException {

        ObservableList<Appointment> contactAppointments = FXCollections.observableArrayList();
        Appointment appointment;

        String sql = "SELECT * FROM appointments JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID WHERE Contact_Name = " + contact;;
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

    public static ObservableList<String> getAllTypes() throws SQLException {

        ObservableList<String> allTypes = FXCollections.observableArrayList();
        String type;

        String sql = "SELECT Type FROM appointments";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){

            type = rs.getString("Type");
            allTypes.add(type);
        }

        return allTypes;

    }

}
