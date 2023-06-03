package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    Stage stage;
    Parent scene;

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
    void onActionLogin(ActionEvent event) throws IOException {

        String userName = usernameTxt.getText()

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/View/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

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

    }
}
