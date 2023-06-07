package sample.Controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.DAO.*;
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

public class AddAppointmentController implements Initializable {

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
    void onActionSaveBtn(ActionEvent event) throws IOException, SQLException {

        try {
            boolean valid = true;
            Appointment newAppointment;
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

            newAppointment = new Appointment(title, description, location, type, startDateTime, endDateTime, userId, customerId, contactId);

            if (newAppointment.getStartDateTime().isAfter(newAppointment.getEndDateTime())) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid appointment time. The appointment starting time must be before the ending time");
                alert.showAndWait();
                valid = false;
            }
            if (DBAppointment.checkOverlap(newAppointment) == true) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "This customer already has an appointment scheduled during this time. Please select a different appointment time");
                alert.showAndWait();
                valid = false;
            }

            if(valid == true) {

                DBAppointment.addAppointment(newAppointment);

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            contactComboBox.setItems(DBContact.getAllContacts());
            userComboBox.setItems(DBUser.getAllUsers());
            customerComboBox.setItems(DBCustomer.getAllCustomers());
            apptDayDatePicker.setValue(LocalDate.now());
            LocalTime firstMeetingStart = LocalTime.of(8, 0);
            LocalTime firstMeetingEnd = LocalTime.of(9, 0);
            startTimeComboBox.setItems(TimeUtility.populateStartTimes(firstMeetingStart));
            endTimeComboBox.setItems(TimeUtility.populateEndTimes(firstMeetingEnd));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
