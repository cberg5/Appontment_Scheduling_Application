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
import sample.DAO.DBContact;
import sample.DAO.DBCountry;
import sample.DAO.DBReports;
import sample.Model.Appointment;
import sample.Model.Contact;
import sample.Model.Country;
import sample.Model.Month;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class ReportsController implements Initializable {

    Stage stage;
    Parent scene;
    String appointmentType;
    int monthNum = 0;

    @FXML
    private TableColumn<Appointment, Integer> apptIdCol;

    @FXML
    private TableView<Appointment> contactApptTableView;

    @FXML
    private ComboBox<Contact> contactComboBox;

    @FXML
    private ComboBox<Country> countryComboBox;

    @FXML
    private Label countryTxt;

    @FXML
    private TableColumn<Appointment, Integer> customerIdCol;

    @FXML
    private TableColumn<Appointment, String> descriptionCol;

    @FXML
    private TableColumn<Appointment, LocalDateTime> endDateTimeCol;

    @FXML
    private ComboBox<Month> monthComboBox;

    @FXML
    private Label monthTypeTxt;

    @FXML
    private Button returnBtn;

    @FXML
    private TableColumn<Appointment, LocalDateTime> startDateTimeCol;

    @FXML
    private TableColumn<Appointment, String> titleCol;

    @FXML
    private TableColumn<Appointment, String> typeCol;

    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    void onActionContactComboBox(ActionEvent event) {
        int contactId = contactComboBox.getSelectionModel().getSelectedItem().getId();
        try {
            contactApptTableView.setItems(DBReports.getAppointmentsByContact(contactId));
            apptIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            startDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
            endDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
            customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @FXML
    void onActionCountryComboBox(ActionEvent event) throws SQLException {
        int countryId = countryComboBox.getSelectionModel().getSelectedItem().getId();
        int numAppointments = DBReports.appointmentsByCountry(countryId);
        countryTxt.setText(": " + String.valueOf(numAppointments));
    }

    @FXML
    void onActionMonthComboBox(ActionEvent event) throws SQLException {
        monthNum = monthComboBox.getSelectionModel().getSelectedItem().getMonthNum();
        if(appointmentType != null){
            int numAppointments = DBReports.appointmentsByMonthType(monthNum, appointmentType);
            monthTypeTxt.setText(": " + String.valueOf(numAppointments));
        }

    }

    @FXML
    void onActionTypeComboBox(ActionEvent event) throws SQLException {
        appointmentType = typeComboBox.getSelectionModel().getSelectedItem();
        if(monthNum != 0){
            int numAppointments = DBReports.appointmentsByMonthType(monthNum, appointmentType);
            monthTypeTxt.setText(": " + String.valueOf(numAppointments));
        }

    }

    @FXML
    void onActionReturnBtn(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/View/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            contactComboBox.setItems(DBContact.getAllContacts());
            monthComboBox.setItems(Month.getAllMonths());
            typeComboBox.setItems(DBReports.getAllTypes());
            countryComboBox.setItems(DBCountry.getAllCountries());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
