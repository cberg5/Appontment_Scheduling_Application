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
import sample.Model.Appointment;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML
    void onActionAppointmentsBtn(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/View/Appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionCustomerRecordsBtn(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/View/CustomerRecords.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionExitBtn(ActionEvent event) {

        System.exit(0);
    }

    @FXML
    void onActionReportsBtn(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/View/Reports.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
