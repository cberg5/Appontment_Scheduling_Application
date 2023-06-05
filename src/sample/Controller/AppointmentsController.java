package sample.Controller;

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
import java.util.ResourceBundle;

public class AppointmentsController implements Initializable {

    Stage stage;
    Parent scene;

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

    @FXML
    void onActionAddBtn(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/View/AddAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionDeleteBtn(ActionEvent event) {

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

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/View/UpdateAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionViewAllRBtn(ActionEvent event) {

    }

    @FXML
    void onActionViewMonthRBtn(ActionEvent event) {

    }

    @FXML
    void onActionViewWeekRBtn(ActionEvent event) {

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
