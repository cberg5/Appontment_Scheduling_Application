package sample.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.DAO.DBContact;
import sample.DAO.DBCustomer;

import java.sql.SQLException;

public class Contact {

    private int id;
    private String name;


    public Contact(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    @Override
    public String toString(){
        return (Integer.toString(id) + ": " + name);
    }
}
