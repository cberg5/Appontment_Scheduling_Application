package sample.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


import java.net.URL;
import java.util.ResourceBundle;

public class CustomerRecordsController implements Initializable {

    @FXML
    private TableColumn<?, ?> FLDivisionCol;

    @FXML
    private Button addBtn;

    @FXML
    private TableColumn<?, ?> addressCol;

    @FXML
    private TableColumn<?, ?> countryCol;

    @FXML
    private TableColumn<?, ?> customerIdCol;

    @FXML
    private TableView<?> customerRecordsTableView;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableColumn<?, ?> nameCol;

    @FXML
    private TableColumn<?, ?> phoneCol;

    @FXML
    private TableColumn<?, ?> postalCodeCol;

    @FXML
    private Button returnBtn;

    @FXML
    private Button updateBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
