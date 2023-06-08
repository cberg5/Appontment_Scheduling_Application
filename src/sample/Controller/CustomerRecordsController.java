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
import sample.DAO.DBCustomer;
import sample.Model.Appointment;
import sample.Model.Country;
import sample.Model.Customer;
import sample.Model.FLDivision;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerRecordsController implements Initializable {

    Stage stage;
    Parent scene;
    private static Customer selectedCustomer;

    @FXML
    private TableColumn<Customer, Integer> FLDivisionIdCol;

    @FXML
    private TableColumn<Customer, String> addressCol;

    @FXML
    private TableColumn<Customer, Integer> countryIdCol;

    @FXML
    private TableColumn<Customer, Integer> customerIdCol;

    @FXML
    private TableView<Customer> customerRecordsTableView;

    @FXML
    private TableColumn<Customer, String> nameCol;

    @FXML
    private TableColumn<Customer, String> phoneCol;

    @FXML
    private TableColumn<Customer, String> postalCodeCol;

    public static Customer getSelectedCustomer(){
        return selectedCustomer;
    }
    @FXML
    void onActionAddBtn(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/View/AddCustomerRecord.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionDeleteBtn(ActionEvent event) throws SQLException {

        Customer customerToDelete = customerRecordsTableView.getSelectionModel().getSelectedItem();

        if(customerToDelete == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select an customer to delete.");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Please confirm if you would like to delete customer: "
                    + customerToDelete.getName() + ", and any associated appointments.");
            alert.setTitle("Confirm Customer Deletion");
            Optional<ButtonType> result = alert.showAndWait();

            if(result.isPresent() && result.get() == ButtonType.OK) {
                DBAppointment.deleteAppointment(customerToDelete.getId());
                DBCustomer.deleteCustomer(customerToDelete);
                customerRecordsTableView.setItems(DBCustomer.getAllCustomers());

                Alert alert2 = new Alert(Alert.AlertType.INFORMATION, "Customer: "
                        + customerToDelete.getName() + ", and any associated appointments have been deleted.");
                alert2.setTitle("Customer Deleted");
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

        selectedCustomer = customerRecordsTableView.getSelectionModel().getSelectedItem();

        if(selectedCustomer == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a customer to update.");
            alert.showAndWait();
        }
        else {
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/sample/View/UpdateCustomerRecord.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            customerRecordsTableView.setItems(DBCustomer.getAllCustomers());
            customerIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
            postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
            phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
            FLDivisionIdCol.setCellValueFactory(new PropertyValueFactory<>("flDivisionId"));
            countryIdCol.setCellValueFactory(new PropertyValueFactory<>("countryId"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
