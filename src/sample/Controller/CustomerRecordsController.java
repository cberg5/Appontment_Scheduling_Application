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
import sample.Model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * The CustomerRecordsController Class.
 * Provides control logic to the customer records menu to view all current
 * customers and select customers to update or delete as well as add new customers
 */
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

    /**
     * A static method to return the selected customer from the customer table.
     * @return
     */
    public static Customer getSelectedCustomer(){
        return selectedCustomer;
    }

    /**
     * On action method for the add button. Sends the user to the add customer menu.
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionAddBtn(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/View/AddCustomerRecord.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * On action method for the delete button.
     * Verifies if a customer is selected then deletes the selected customer and all appointments associated
     * with that customer. Asks for confirmation before deletion and messages again when complete.
     * @param event
     * @throws SQLException
     */
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

    /**
     * On action method for return button. Sends user back to the main menu.
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
     * On action method for the update button.
     * Verifies that a customer is selected and then sends the user to the update customer menu.
     * @param event
     * @throws IOException
     */
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

    /**
     * Initializes the controller. Populates the table with all current customers.
     * @param url
     * @param resourceBundle
     */
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
