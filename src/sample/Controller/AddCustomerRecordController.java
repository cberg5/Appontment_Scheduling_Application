package sample.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;


import java.net.URL;
import java.util.ResourceBundle;

public class AddCustomerRecordController implements Initializable {

    @FXML
    private ComboBox<?> FLDivisionComboBox;

    @FXML
    private TextField addressTxt;

    @FXML
    private Button cancelBtn;

    @FXML
    private ComboBox<?> countryComboBox;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
