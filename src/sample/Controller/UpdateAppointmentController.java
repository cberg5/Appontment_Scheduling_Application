package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.DAO.DBAppointment;
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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

/**
 * The UpdateAppointmentController Class.
 * Provides control logic to the update appointment menu by populating the text fields, combo boxes,
 * and date picker with the selected appointment information and allowing the user to change this information.
 */
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

    /**
     * On action method for cancel button. Returns the user to the appointments menu.
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionCancelBtn(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/View/Appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * On action event for save button. Takes updated user input data from text fields, combo boxes,
     * and a date picker to create a new appointment object. Calls an appointment database access function to check if
     * there are any overlapping appointments. Verifies if the user inputted start time is before the end time.
     * Calls appointment database access method to update the appointment in the database.
     * Returns user to appointment menu when complete.
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionSaveBtn(ActionEvent event) throws IOException {

        try {
            boolean valid = true;
            Appointment updatedAppointment;
            int id = Integer.parseInt(apptIdTxt.getText());
            String title = titleTxt.getText();
            String description = descriptionTxt.getText();
            String location = locationTxt.getText();
            String type = typeTxt.getText();
            LocalDate date = apptDayDatePicker.getValue();
            LocalTime startTime = startTimeComboBox.getSelectionModel().getSelectedItem();
            LocalTime endTime = endTimeComboBox.getSelectionModel().getSelectedItem();
            int customerId = customerComboBox.getSelectionModel().getSelectedItem().getId();
            int userId = userComboBox.getSelectionModel().getSelectedItem().getId();
            int contactId = contactComboBox.getSelectionModel().getSelectedItem().getId();
            LocalDateTime startDateTime = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(),
                    startTime.getHour(), startTime.getMinute());
            LocalDateTime endDateTime = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(),
                    endTime.getHour(), endTime.getMinute());

            updatedAppointment = new Appointment(id, title, description, location, type, startDateTime, endDateTime, userId, customerId, contactId);

            if (updatedAppointment.getStartDateTime().isAfter(updatedAppointment.getEndDateTime())) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid appointment time. The appointment starting time must be before the ending time");
                alert.showAndWait();
                valid = false;
            }
            if (DBAppointment.checkOverlapUpdated(updatedAppointment) == true) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "This customer already has an appointment scheduled during this time. Please select a different appointment time");
                alert.showAndWait();
                valid = false;
            }

            if(valid == true) {

                DBAppointment.updateAppointment(updatedAppointment);

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/sample/View/Appointments.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please input valid data for each field.");
            alert.showAndWait();
        }

    }

    /**
     * Initializes the controller. Retrieves the selected appointment from the appointment menu.
     * Populates the text fields, combo boxes, and date picker with the selected appointment information.
     * @param url
     * @param resourceBundle
     */
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
