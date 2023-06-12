package sample.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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

    public static Appointment getSelectedAppointment(){
        return selectedAppointment;
    }

    @FXML
    void onActionAddBtn(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/View/AddAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

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

    @FXML
    void onActionReturnBtn(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/View/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

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

    @FXML
    void onActionViewAllRBtn(ActionEvent event) throws SQLException {
        if(viewAllRBtn.isSelected()) {
            apptTableView.setItems(DBAppointment.getAllAppointments());
        }

    }

    @FXML
    void onActionViewMonthRBtn(ActionEvent event) throws SQLException {
        if(viewMonthRBtn.isSelected()){
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime oneMonth = LocalDateTime.now().plusMonths(1);
            apptTableView.setItems(DBAppointment.getAllAppointments().stream().filter(a -> a.getStartDateTime().isAfter(now)
                    && a.getStartDateTime().isBefore(oneMonth)).collect(Collectors.toCollection(FXCollections::observableArrayList)));
        }

    }

    @FXML
    void onActionViewWeekRBtn(ActionEvent event) throws SQLException {
        if(viewWeekRBtn.isSelected()){
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime oneWeek = LocalDateTime.now().plusWeeks(1);
            apptTableView.setItems(DBAppointment.getAllAppointments().stream().filter(a -> a.getStartDateTime().isAfter(now)
                    && a.getStartDateTime().isBefore(oneWeek)).collect(Collectors.toCollection(FXCollections::observableArrayList)));

        }

    }

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
