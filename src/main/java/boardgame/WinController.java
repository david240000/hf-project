package boardgame;

import boardgame.model.BoardGameModel;
import boardgame.result.Result;
import boardgame.result.Results;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;


public class WinController {

    @FXML
    private TextField name;

    @FXML
    private Text stepsshow;

    private String name1;

    private Results list;

    private int steps;

    public void setSteps(int steps){
        this.steps = steps;
        setStepsshow();
        Logger.info("Settings steps to {}", steps);
    }

    private void setStepsshow(){
        stepsshow.setText(String.valueOf(steps)+" lépés");
    }

    @FXML
    private void switchBack(ActionEvent event) throws IOException {
        var objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        name1 = name.getText();
        var result = new Result(name1,steps);
        Logger.debug("Result this: {}, {} added", name1,steps);
        try {
            this.list = objectMapper.readValue(new FileReader("result.json"), Results.class);
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
        try(var writer = new FileWriter("result.json") ){
            objectMapper.writeValue(writer, list);
        }


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/result.fxml"));
        stage.setScene(new Scene(root));
        Logger.info("Going to the results screen");
        stage.show();
    }



}
