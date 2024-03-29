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
import sample.DAO.JDBC;
import sample.Model.Appointment;
import sample.Utilities.TimeUtility;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * The loginController class.
 * Provides control logic to the login menu by verifying username and password,
 * adjusting language based on user location, and showing the time zone the user currently resides in.
 */
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

    /**
     * On action method for the cancel button. Exits the application.
     * @param event
     */
    @FXML
    void onActionCancel(ActionEvent event) {
        JDBC.closeConnection();
        System.exit(0);
    }

    /**
     * On action method for the login button.
     * Takes the user input for the username and password and calls a user database access method to verify the login
     * credentials. If valid, calls an appointment database access method to see if there are any upcoming appointments
     * and prompts the user if so. Additionally, creates a filewriter to write to a seperate .txt file to log every
     * login attempt, and whether it was successful or not with the date/time. Sends user to the main menu if login successful.
     *
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    void onActionLogin(ActionEvent event) throws IOException, SQLException {

        String loginUsername = usernameTxt.getText();
        String loginPassword = passwordTxt.getText();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime utcNow = TimeUtility.convertLocaltoUtc(now);
        String fileName = "login_activity.txt";

        FileWriter fileWriter = new FileWriter(fileName, true);
        PrintWriter outputFile = new PrintWriter(fileWriter);

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

                    LocalDateTime time = appointment.getStartDateTime();
                    String dateTimeFormatter= "yyyy-MM-dd HH:mm:ss";
                    DateTimeFormatter format = DateTimeFormatter.ofPattern(dateTimeFormatter);
                    String formatDateTime = time.format(format);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Upcoming appointment");
                    alert.setHeaderText("There is an upcoming appointment");
                    alert.setContentText("Appointment " + id + ", starts at " + formatDateTime + ".");
                    Optional<ButtonType> result = alert.showAndWait();

                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            outputFile.println("User: " + DBUser.getLoginUser().getName() + ", successful login at " + utcNow + " UTC");
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/sample/View/MainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }

        else {
            outputFile.println("User: " + loginUsername + ", failed login attempt at " + utcNow + " UTC");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(alertTitle);
            alert.setHeaderText(alertHeader);
            alert.setContentText(alertContent);
            alert.showAndWait();

        }
        outputFile.close();


    }


    /**
     * Initializes the controller.
     * Sets all the text in the window to English or French depending on the users location using a language resource bundle.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        zoneIdLabel.setText(ZoneId.systemDefault().toString());
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
