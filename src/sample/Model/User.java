package sample.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.DAO.DBUser;

import java.sql.SQLException;

/**
 * User class. Used to set and retrieve data from user objects
 */
public class User {

    private int id;
    private String name;

    /**
     * Constructor for a user object.
     * @param id
     * @param name
     */
    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Retrieve name from a user.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieve ID from a user.
     * @return ID
     */
    public int getId() { return id; }

    /**
     * Set name for a user.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set ID for a user.
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * A method to retrieve a user object.
     * Gets the user ID from an appointment object and compares to a list of all users and returns the matching user.
     * @param appointment
     * @return
     * @throws SQLException
     */
    public static User getUser(Appointment appointment) throws SQLException {
        int userId = appointment.getUserId();
        User selectedUser = null;
        ObservableList<User> allUsers = FXCollections.observableArrayList();
        allUsers = DBUser.getAllUsers();

        for(User user : allUsers){
            if(user.getId() == userId){
                selectedUser = user;
            }
        }
        return selectedUser;
    }

    /**
     * Overrides the toString function for User class. Helps to properly display user info in comboboxes.
     * @return
     */
    @Override
    public String toString(){
        return (Integer.toString(id) + ": " + name);
    }
}
