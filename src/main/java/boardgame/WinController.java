package boardgame;

import boardgame.model.BoardGameModel;
import boardgame.model.LastResult;
import boardgame.model.Result;
import boardgame.model.Results;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.tinylog.Logger;

import javax.script.Bindings;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;


public class WinController {

    @FXML
    private TextField name;

    private String name1;

    private Results list;

    private int steps;


    @FXML
    private void initialize(){
        this.steps = BoardGameModel.getSteps();
    }

    @FXML
    private void switchBack(ActionEvent event) throws IOException {
        var objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        name1 = name.getText();
        var result = new Result(name1,BoardGameModel.getSteps());
        Logger.debug("Result this: {}, {} added", name1,steps);
        try {
            this.list = objectMapper.readValue(new FileReader("target/classes/result.json"), Results.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        list.getList().add(result);
        Collections.sort(list.getList());
        try(var writer = new FileWriter("target/classes/result.json") ){
            objectMapper.writeValue(writer, list);
        }


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/menu.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }



}
