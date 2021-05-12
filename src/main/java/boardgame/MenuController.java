package boardgame;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.IOException;

public class MenuController {

    @FXML
    private void switchToGame(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/ui.fxml"));
        stage.setTitle("Játék");
        stage.setScene(new Scene(root));
        Logger.info("The game start");
        stage.show();
    }

    @FXML
    private void switchToResult(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/result.fxml"));
        stage.setTitle("Eredmlnyek");
        stage.setScene(new Scene(root));
        Logger.info("Results showing");
        stage.show();
    }

    @FXML
    private void onQuit(ActionEvent event){
        Logger.info("Terminating");
        Platform.exit();
    }
}
