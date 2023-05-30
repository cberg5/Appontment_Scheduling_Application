package sample.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentsController implements Initializable {

    @FXML
    private Button addBtn;

    @FXML
    private ToggleGroup apptFilterTG;

    @FXML
    private TableColumn<?, ?> apptIdCol;

    @FXML
    private TableView<?> apptTableView;

    @FXML
    private TableColumn<?, ?> contactCol;

    @FXML
    private TableColumn<?, ?> customerIdCol;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableColumn<?, ?> descriptionCol;

    @FXML
    private TableColumn<?, ?> endDateTimeCol;

    @FXML
    private TableColumn<?, ?> locationCol;

    @FXML
    private TableColumn<?, ?> startDateTimeCol;

    @FXML
    private TableColumn<?, ?> titleCol;

    @FXML
    private TableColumn<?, ?> typeCol;

    @FXML
    private Button updateBtn;

    @FXML
    private TableColumn<?, ?> userIdCol;

    @FXML
    private RadioButton viewAllRBtn;

    @FXML
    private RadioButton viewMonthRBtn;

    @FXML
    private RadioButton viewWeekRBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
