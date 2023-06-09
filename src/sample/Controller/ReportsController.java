package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.DAO.DBContact;
import sample.DAO.DBReports;
import sample.Model.Appointment;
import sample.Model.Contact;
import sample.Model.Country;
import sample.Model.Month;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class ReportsController implements Initializable {

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

    }

    @FXML
    void onActionCountryComboBox(ActionEvent event) {

    }

    @FXML
    void onActionMonthComboBox(ActionEvent event) {

    }

    @FXML
    void onActionTypeComboBox(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            contactComboBox.setItems(DBContact.getAllContacts());
            monthComboBox.setItems(Month.getAllMonths());
            typeComboBox.setItems(DBReports.getAllTypes());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
