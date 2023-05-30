package sample.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {

    @FXML
    private DatePicker apptDayDatePicker;

    @FXML
    private TextField apptIdTxt;

    @FXML
    private Button cancelBtn;

    @FXML
    private ComboBox<?> contactComboBox;

    @FXML
    private ComboBox<?> customerIdTxt;

    @FXML
    private TextField descriptionTxt;

    @FXML
    private ComboBox<?> endTimeComboBox;

    @FXML
    private TextField locationTxt;

    @FXML
    private Button saveBtn;

    @FXML
    private ComboBox<?> startTimeComboBox;

    @FXML
    private TextField titleTxt;

    @FXML
    private TextField typeTxt;

    @FXML
    private ComboBox<?> userIdTxt;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
