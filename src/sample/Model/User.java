package sample.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.DAO.DBUser;

import java.sql.SQLException;

public class User {

    private int id;
    private String name;

    public User(int id, String name) {

        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() { return id; }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public String toString(){
        return (Integer.toString(id) + ": " + name);
    }
}
