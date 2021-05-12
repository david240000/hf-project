package boardgame;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuApplication extends Application {

    @Override
    public void start(Stage menuStage) throws Exception {
        Parent root = FXMLLoader.load(MenuApplication.class.getResource("/menu.fxml"));
        menuStage.setTitle("Menu");
        menuStage.setScene(new Scene(root));
        menuStage.show();
    }

}
