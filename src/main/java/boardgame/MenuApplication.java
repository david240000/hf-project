package boardgame;

import java.io.File;
import java.io.FileWriter;

import boardgame.result.Results;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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
        File file = new File("result.json");
        if (!file.isFile()){
            file.createNewFile();
            var objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            Results list = new Results();
            try(var writer = new FileWriter(file) ){
                objectMapper.writeValue(writer, list);
            }
        }
    }

}
