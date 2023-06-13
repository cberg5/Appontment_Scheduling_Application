package sample.Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DAO.DBCountry;
import sample.DAO.DBCustomer;
import sample.DAO.DBFLDivision;
import sample.Model.Country;
import sample.Model.Customer;
import sample.Model.FLDivision;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * The AddCustomerRecordController Class.
 * Provides control logic to the AddCustomerRecord menu to allow new customers to be added using user input
 * from text fields and combo boxes.
 */
public class AddCustomerRecordController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private ComboBox<FLDivision> FLDivisionComboBox;

    @FXML
    private TextField addressTxt;

    @FXML
    private Button cancelBtn;

    @FXML
    private ComboBox<Country> countryComboBox;

    @FXML
    private TextField customerIdTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField phoneTxt;

    @FXML
    private TextField postalCodeTxt;

    @FXML
    private Button saveBtn;

    /**
     * On action method for the country combo box. When a country is selected will populate the division combo box with the proper divisions associated with that country.
     * LAMBDA: Lambda expressions are used to filter through the list of all first level divisions depending on which country is selected.
     * Lambda was used here for a simpler method of retrieving the list of divisions that correspond to the selected country.
     * The alternative was to create additional SQL queries which would have been more code.
     * @param event
     * @throws SQLException
     */
    @FXML
    void onActionCountryComboBox(ActionEvent event) throws SQLException {

        Country selectedCountry = countryComboBox.getSelectionModel().getSelectedItem();
        int countryId = selectedCountry.getId();

        if(countryId == 1){
            FLDivisionComboBox.setItems(DBFLDivision.getAllDivisions().stream().filter(d -> d.getCountryId() == 1).collect(Collectors.toCollection(FXCollections::observableArrayList)));
        }
        if(countryId == 2){
            FLDivisionComboBox.setItems(DBFLDivision.getAllDivisions().stream().filter(d -> d.getCountryId() == 2).collect(Collectors.toCollection(FXCollections::observableArrayList)));
        }
        if(countryId == 3){
            FLDivisionComboBox.setItems(DBFLDivision.getAllDivisions().stream().filter(d -> d.getCountryId() == 3).collect(Collectors.toCollection(FXCollections::observableArrayList)));
        }


    }

    /**
     * On action method for the cancel button. Returns user to customer records menu.
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionCancelBtn(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/View/CustomerRecords.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * On action method for save button.
     * Takes the user inputted data from the text fields and combo boxes and creates a new customer object and calls
     * the customer database access method to add the customer to the database. Returns user to customer record menu when complete.
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    void onActionSaveBtn(ActionEvent event) throws IOException, SQLException {

        try {
            Customer newCustomer;
            String name = nameTxt.getText();
            String address = addressTxt.getText();
            String postalCode = postalCodeTxt.getText();
            String phone = phoneTxt.getText();
            int flDivisionId = FLDivisionComboBox.getSelectionModel().getSelectedItem().getId();
            String flDivision = FLDivisionComboBox.getSelectionModel().getSelectedItem().getName();
            int countryId = countryComboBox.getSelectionModel().getSelectedItem().getId();
            String country = countryComboBox.getSelectionModel().getSelectedItem().getName();

            newCustomer = new Customer(name, address, postalCode, phone, flDivisionId, flDivision, countryId, country);

            DBCustomer.addCustomer(newCustomer);

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/sample/View/CustomerRecords.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please input valid data for each field.");
            alert.showAndWait();
        }

    }

    /**
     * Initializes the controller. Populates the country combo box.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            countryComboBox.setItems(DBCountry.getAllCountries());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
