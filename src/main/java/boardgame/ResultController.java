package boardgame;

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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ResultController {
    private Results list;

    @FXML
    private Text name0;

    @FXML
    private Text name1;

    @FXML
    private Text name2;

    @FXML
    private Text step0;

    @FXML
    private Text step1;

    @FXML
    private Text step2;

    @FXML
    private void initialize() {
        var objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
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
        try {
            name0.setText(list.getList().get(0).getName());
            step0.setText(String.valueOf(list.getList().get(0).getSteps()));
            name1.setText(list.getList().get(1).getName());
            step1.setText(String.valueOf(list.getList().get(1).getSteps()));
            name2.setText(list.getList().get(2).getName());
            step2.setText(String.valueOf(list.getList().get(2).getSteps()));
        }catch (Exception e){}
    }

    @FXML
    private void switchBack(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/menu.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
