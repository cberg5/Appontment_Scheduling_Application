package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import sample.DAO.DBAppointment;
import sample.DAO.JDBC;
import sample.Model.Appointment;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * The MainMenuController class.
 * Provides control logic to the main menu to allow user to navigate to the other menus of the application.
 */
public class MainMenuController implements Initializable {
    Stage stage;
    Parent scene;

    /**
     * On action method for the appointment button. Sends the user to the appointments menu.
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionAppointmentsBtn(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/View/Appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * On action method for the customer records button. Sends the user to the customer records menu.
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionCustomerRecordsBtn(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/View/CustomerRecords.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * On action method for the exit button. Exits the application.
     * @param event
     */
    @FXML
    void onActionExitBtn(ActionEvent event) {

        JDBC.closeConnection();
        System.exit(0);
    }

    /**
     * On action method for the reports button. Sends the user to the reports menu.
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionReportsBtn(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/View/Reports.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }


    /**
     * Initializes the controller.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
