package sample.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.DAO.DBContact;

import java.sql.SQLException;

/**
 * Contact class. Used to set and retrieve data on contact objects
 */
public class Contact {

    private int id;
    private String name;

    /**
     * Constructor for contact object
     * @param id
     * @param name
     */
    public Contact(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Retrieves ID from contact.
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Sets ID for contact.
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves name from contact.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name for contact.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * A method to retrieve a contact object.
     * Gets the contact ID from an appointment object and finds the matching contact from a list of all contacts.
     * @param appointment
     * @return Contact
     * @throws SQLException
     */
    public static Contact getContact(Appointment appointment) throws SQLException {
        int contactId = appointment.getContactId();
        Contact selectedContact = null;
        ObservableList<Contact> allContacts = FXCollections.observableArrayList();
        allContacts = DBContact.getAllContacts();

        for(Contact contact : allContacts){
            if(contact.getId() == contactId){
                selectedContact = contact;
            }
        }
        return selectedContact;
    }

    /**
     * Overrides the toString function for Contact class. Helps to properly display contact info in comboboxes.
     * @return
     */
    @Override
    public String toString(){
        return (Integer.toString(id) + ": " + name);
    }
}
