package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.DAO.DBAppointment;
import sample.DAO.DBUser;
import sample.Model.Appointment;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    Stage stage;
    Parent scene;
    String alertTitle;
    String alertHeader;
    String alertContent;

    @FXML
    private Button cancelBtn;

    @FXML
    private Label location;

    @FXML
    private Button loginBtn;

    @FXML
    private Label password;

    @FXML
    private TextField passwordTxt;

    @FXML
    private Label title;

    @FXML
    private Label username;

    @FXML
    private TextField usernameTxt;

    @FXML
    private Label zoneIdLabel;
    @FXML
    void onActionCancel(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onActionLogin(ActionEvent event) throws IOException, SQLException {

        String loginUsername = usernameTxt.getText();
        String loginPassword = passwordTxt.getText();

        if (DBUser.validateLogin(loginUsername, loginPassword) == true) {

            try {
                Appointment appointment = DBAppointment.get15minAppointment();

                if (appointment == null){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("There are no upcoming appointments");
                    Optional<ButtonType> result = alert.showAndWait();
                }
                else {
                    int id = appointment.getId();
                    String time = appointment.getStartDateTime();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Upcoming appointment");
                    alert.setHeaderText("There is an upcoming appointment.");
                    alert.setContentText("Appointment " + id + ", starts at " + time + ".");
                    Optional<ButtonType> result = alert.showAndWait();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/sample/View/MainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }

        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(alertTitle);
            alert.setHeaderText(alertHeader);
            alert.setContentText(alertContent);
            alert.showAndWait();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        zoneIdLabel.setText(ZoneId.systemDefault().toString());
      //  resourceBundle = ResourceBundle.getBundle("sample.Language", new Locale("fr"));
        resourceBundle = ResourceBundle.getBundle("sample.Language", Locale.getDefault());
        title.setText(resourceBundle.getString("title"));
        username.setText(resourceBundle.getString("username"));
        password.setText(resourceBundle.getString("password"));
        loginBtn.setText(resourceBundle.getString("loginBtn"));
        cancelBtn.setText(resourceBundle.getString("cancelBtn"));
        location.setText(resourceBundle.getString("location"));
        alertTitle = resourceBundle.getString("alertTitle");
        alertHeader = resourceBundle.getString("alertHeader");
        alertContent = resourceBundle.getString("alertContent");

    }
}
