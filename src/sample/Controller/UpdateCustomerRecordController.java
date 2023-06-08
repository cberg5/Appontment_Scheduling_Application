package sample.Controller;

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
import sample.DAO.*;
import sample.Model.*;
import sample.Utilities.TimeUtility;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class UpdateCustomerRecordController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private ComboBox<FLDivision> FLDivisionComboBox;

    @FXML
    private TextField addressTxt;

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
    void onActionCountryComboBox(ActionEvent event) throws SQLException {

        Country selectedCountry = countryComboBox.getSelectionModel().getSelectedItem();
        int countryId = selectedCountry.getId();

        if(countryId == 1){
            FLDivisionComboBox.setItems(DBFLDivision.getUSDivisions());
        }
        if(countryId == 2){
            FLDivisionComboBox.setItems(DBFLDivision.getUKDivisions());
        }
        if(countryId == 3){
            FLDivisionComboBox.setItems(DBFLDivision.getCADivisions());
        }

    }
    @FXML
    void onActionCancelBtn(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/View/CustomerRecords.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionSaveBtn(ActionEvent event) throws IOException {

        try {
            Customer updatedCustomer;
            int id = Integer.parseInt(customerIdTxt.getText());
            String name = nameTxt.getText();
            String address = addressTxt.getText();
            String postalCode = postalCodeTxt.getText();
            String phone = phoneTxt.getText();
            int flDivisionId = FLDivisionComboBox.getSelectionModel().getSelectedItem().getId();
            String flDivision = FLDivisionComboBox.getSelectionModel().getSelectedItem().getName();
            int countryId = countryComboBox.getSelectionModel().getSelectedItem().getId();
            String country = countryComboBox.getSelectionModel().getSelectedItem().getName();

            updatedCustomer = new Customer(id, name, address, postalCode, phone, flDivisionId, flDivision, countryId, country);

            DBCustomer.updateCustomer(updatedCustomer);

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Customer selectedCustomer = CustomerRecordsController.getSelectedCustomer();
        try {
            customerIdTxt.setText(String.valueOf(selectedCustomer.getId()));
            nameTxt.setText(selectedCustomer.getName());
            addressTxt.setText(selectedCustomer.getAddress());
            postalCodeTxt.setText(selectedCustomer.getPostalCode());
            phoneTxt.setText(selectedCustomer.getPhone());
            countryComboBox.setItems(DBCountry.getAllCountries());
            countryComboBox.setValue(Country.getCountry(selectedCustomer));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
