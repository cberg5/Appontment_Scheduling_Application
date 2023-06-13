package sample.Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.DAO.DBAppointment;
import sample.Model.Appointment;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * The AppointmentsController class.
 * Provides control logic to the appointments menu to view appointments by 'all', 'month, or 'week'.
 * Add new appointments, or select an appointment to delete or modify.
 */
public class AppointmentsController implements Initializable {

    Stage stage;
    Parent scene;
    private static Appointment selectedAppointment;

    @FXML
    private TableColumn<Appointment, Integer> apptIdCol;

    @FXML
    private TableView<Appointment> apptTableView;

    @FXML
    private TableColumn<Appointment, Integer> contactIdCol;

    @FXML
    private TableColumn<Appointment, Integer> customerIdCol;

    @FXML
    private TableColumn<Appointment, String> descriptionCol;

    @FXML
    private TableColumn<Appointment, String> endDateTimeCol;

    @FXML
    private TableColumn<Appointment, String> locationCol;

    @FXML
    private TableColumn<Appointment, String> startDateTimeCol;

    @FXML
    private TableColumn<Appointment, String> titleCol;

    @FXML
    private TableColumn<Appointment, String> typeCol;

    @FXML
    private TableColumn<Appointment, Integer> userIdCol;

    @FXML
    private RadioButton viewAllRBtn;

    @FXML
    private RadioButton viewMonthRBtn;

    @FXML
    private RadioButton viewWeekRBtn;

    /**
     * A static method to retrieve the selected appointment.
     * @return
     */
    public static Appointment getSelectedAppointment(){
        return selectedAppointment;
    }

    /**
     * On action method for the add button. Sends user to the add appointment menu.
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionAddBtn(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/View/AddAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * On action method for delete button.
     * Verifies that an appointment is selected.
     * Prompts user to confirm appointment deletion then shows confirmation message with
     * information on the appointment deleted.
     * @param event
     * @throws SQLException
     */
    @FXML
    void onActionDeleteBtn(ActionEvent event) throws SQLException {

        Appointment appointmentToDelete = apptTableView.getSelectionModel().getSelectedItem();

        if(appointmentToDelete == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select an appointment to delete.");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Please confirm if you would like to delete Appointment ID: "
                    + appointmentToDelete.getId() + ", Type: " + appointmentToDelete.getType());
            alert.setTitle("Confirm Appointment Deletion");
            Optional<ButtonType> result = alert.showAndWait();

            if(result.isPresent() && result.get() == ButtonType.OK) {
                DBAppointment.deleteAppointment(appointmentToDelete);
                apptTableView.setItems(DBAppointment.getAllAppointments());

                Alert alert2 = new Alert(Alert.AlertType.INFORMATION, "Appointment ID: " + appointmentToDelete.getId() +
                        ", Type: " + appointmentToDelete.getType() + ", has been deleted.");
                alert2.setTitle("Appointment Deleted");
                Optional<ButtonType> result2 = alert2.showAndWait();
            }
        }
    }

    /**
     * On action method for return button. Returns user to the main menu.
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionReturnBtn(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/View/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * On action method for update button.
     * Confirms the user selected an appointment and sends the user to the update appointment menu.
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionUpdateBtn(ActionEvent event) throws IOException {

        selectedAppointment = apptTableView.getSelectionModel().getSelectedItem();

        if(selectedAppointment == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select an appointment to update.");
            alert.showAndWait();
        }
        else {
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/sample/View/UpdateAppointment.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }

    }

    /**
     * On action method for the view all radio button.
     * Populates the table with all the appointments from the database by calling an appointment database access method
     * that returns a list of all appointments from the database.
     * @param event
     * @throws SQLException
     */
    @FXML
    void onActionViewAllRBtn(ActionEvent event) throws SQLException {
        if(viewAllRBtn.isSelected()) {
            apptTableView.setItems(DBAppointment.getAllAppointments());
        }

    }

    /**
     * On action method for the view by month radio button. Populates table with all appointments within the next month.
     * LAMBDA: A lambda expression is used to filter through the list of all appointments to return only appointments
     * that exist between the current date/time and 1 month from now.
     * Lambda was used here for a simpler method of retrieving the list of appointments that exist within a certain time frame.
     * The alternative was to create additional SQL queries which would have been more code.
     * @param event
     * @throws SQLException
     */
    @FXML
    void onActionViewMonthRBtn(ActionEvent event) throws SQLException {
        if(viewMonthRBtn.isSelected()){
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime oneMonth = LocalDateTime.now().plusMonths(1);
            apptTableView.setItems(DBAppointment.getAllAppointments().stream().filter(a -> a.getStartDateTime().isAfter(now)
                    && a.getStartDateTime().isBefore(oneMonth)).collect(Collectors.toCollection(FXCollections::observableArrayList)));
        }

    }

    /**
     * On action method for the view by week radio button. Populates table with all appointments within the next week.
     * LAMBDA: A lambda expression is used to filter through the list of all appointments to return only appointments
     * that exist between the current date/time and 1 week from now.
     * Lambda was used here for a simpler method of retrieving the list of appointments that exist within a certain time frame.
     * The alternative was to create additional SQL queries which would have been more code.
     * @param event
     * @throws SQLException
     */
    @FXML
    void onActionViewWeekRBtn(ActionEvent event) throws SQLException {
        if(viewWeekRBtn.isSelected()){
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime oneWeek = LocalDateTime.now().plusWeeks(1);
            apptTableView.setItems(DBAppointment.getAllAppointments().stream().filter(a -> a.getStartDateTime().isAfter(now)
                    && a.getStartDateTime().isBefore(oneWeek)).collect(Collectors.toCollection(FXCollections::observableArrayList)));

        }

    }

    /**
     * Initializes the controller. Populates the appointment table.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            apptTableView.setItems(DBAppointment.getAllAppointments());
            apptIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            contactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
            descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
            typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            startDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
            endDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
            userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
            customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
