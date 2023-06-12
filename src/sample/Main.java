package sample;

import sample.DAO.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Cody Bergin
 */

/**
 * The Main class that creates the application and opens/closes the database connection.
 */

public class Main extends Application {

    /** The start method that initiates the stage and loads the Main Menu screen.
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/Login.fxml"));
        primaryStage.setTitle("Scheduling Desktop Application");
        primaryStage.setScene(new Scene(root, 411, 274));
        primaryStage.show();
    }

    /**
     * The main method that starts the application and opens/closes database connection.
     * @param args
     */
    public static void main(String[] args) {

        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();
    }
}
