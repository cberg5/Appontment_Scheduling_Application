package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The DBContact Class. Used to retrieve data on contacts from the database.
 */
public class DBContact {

    /**
     * A method to get a list of all contacts in the database.
     * Runs a SQL query to get a list of all contacts a populates and returns a list of those contacts.
     * @return list of all contacts.
     * @throws SQLException
     */
    public static ObservableList<Contact> getAllContacts() throws SQLException {

        ObservableList<Contact> allContacts = FXCollections.observableArrayList();
        Contact contact;

        String sql = "SELECT * FROM contacts";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            contact = new Contact(rs.getInt("Contact_ID"), rs.getString("Contact_Name"));
            allContacts.add(contact);
        }

        return allContacts;
    }

}