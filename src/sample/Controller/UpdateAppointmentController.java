package sample.Controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DAO.DBContact;
import sample.DAO.DBCustomer;
import sample.DAO.DBUser;
import sample.Model.Appointment;
import sample.Model.Contact;
import sample.Model.Customer;
import sample.Model.User;
import sample.Utilities.TimeUtility;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class UpdateAppointmentController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private DatePicker apptDayDatePicker;

    @FXML
    private TextField apptIdTxt;

    @FXML
    private ComboBox<Contact> contactComboBox;

    @FXML
    private ComboBox<Customer> customerComboBox;

    @FXML
    private TextField descriptionTxt;

    @FXML
    private ComboBox<LocalTime> endTimeComboBox;

    @FXML
    private TextField locationTxt;

    @FXML
    private ComboBox<LocalTime> startTimeComboBox;

    @FXML
    private TextField titleTxt;

    @FXML
    private TextField typeTxt;

    @FXML
    private ComboBox<User> userComboBox;

    @FXML
    void onActionCancelBtn(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/View/Appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionSaveBtn(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/View/Appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Appointment selectedAppointment = AppointmentsController.getSelectedAppointment();
        try {
            apptIdTxt.setText(String.valueOf(selectedAppointment.getId()));
            titleTxt.setText(selectedAppointment.getTitle());
            locationTxt.setText(selectedAppointment.getLocation());
            descriptionTxt.setText(selectedAppointment.getDescription());
            typeTxt.setText(selectedAppointment.getType());
            userComboBox.setItems(DBUser.getAllUsers());
            userComboBox.setValue(User.getUser(selectedAppointment));
            customerComboBox.setItems(DBCustomer.getAllCustomers());
            customerComboBox.setValue(Customer.getCustomer(selectedAppointment));
            contactComboBox.setItems(DBContact.getAllContacts());
            contactComboBox.setValue(Contact.getContact(selectedAppointment));
            LocalTime firstMeetingStart = LocalTime.of(8, 0);
            LocalTime firstMeetingEnd = LocalTime.of(9, 0);
            startTimeComboBox.setItems(TimeUtility.populateStartTimes(firstMeetingStart));
            endTimeComboBox.setItems(TimeUtility.populateEndTimes(firstMeetingEnd));
            LocalTime selectedStart = selectedAppointment.getStartDateTime().toLocalTime();
            LocalTime selectedEnd = selectedAppointment.getEndDateTime().toLocalTime();
            startTimeComboBox.setValue(selectedStart);
            endTimeComboBox.setValue(selectedEnd);
            LocalDate selectedDate = selectedAppointment.getStartDateTime().toLocalDate();
            apptDayDatePicker.setValue(selectedDate);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
