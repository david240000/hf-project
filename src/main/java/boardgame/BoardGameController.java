package boardgame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javafx.stage.Stage;
import org.tinylog.Logger;

import boardgame.model.BoardGameModel;
import boardgame.model.PawnDirection;
import boardgame.model.Position;


public class BoardGameController {

    private enum SelectionPhase {
        SELECT_FROM,
        SELECT_TO;

        public SelectionPhase alter() {
            return switch (this) {
                case SELECT_FROM -> SELECT_TO;
                case SELECT_TO -> SELECT_FROM;
            };
        }
    }

    private SelectionPhase selectionPhase = SelectionPhase.SELECT_FROM;

    private List<Position> selectablePositions = new ArrayList<>();

    private Position selected;

    private BoardGameModel model = new BoardGameModel();

    @FXML
    private GridPane board;

    @FXML
    private void initialize() {
        createBoard();
        createPlayer();
        cteateMonster();
        setSelectablePositions();
        showSelectablePositions();
    }

    private void createBoard() {
        for (int i = 0; i < board.getRowCount(); i++) {
            for (int j = 0; j < board.getColumnCount(); j++) {
                var square = createSquare(i, j);
                board.add(square, j, i);
            }
        }
    }

    private StackPane createSquare(int i, int j) {
        var square = new StackPane();
        var wallType = model.getWall(i,j).getWallType();
        switch (wallType){
            case UP:square.getStyleClass().add("wallup");break;
            case RIGHT:square.getStyleClass().add("wallright");break;
            case DOWN:square.getStyleClass().add("walldown");break;
            case LEFT:square.getStyleClass().add("wallleft");break;
            case UP_RIGHT:square.getStyleClass().add("wallupright");break;
            case UP_DOWN:square.getStyleClass().add("walldown");break;
            case UP_LEFT:square.getStyleClass().add("wallupleft");break;
            case RIGHT_DOWN:square.getStyleClass().add("wallrightdown");break;
            case RIGHT_LEFT:square.getStyleClass().add("wallrightleft");break;
            case DOWN_LEFT:square.getStyleClass().add("walldownleft");break;
            case UP_RIGHT_DOWN:square.getStyleClass().add("walluprightdown");break;
            case UP_RIGHT_LEFT:square.getStyleClass().add("walluprightleft");break;
            case UP_DOWN_LEFT:square.getStyleClass().add("wallupdownleft");break;
            case RIGHT_DOWN_LEFT:square.getStyleClass().add("wallrightdownleft");break;
            case NONE:square.getStyleClass().add("square");break;
        }
        square.setOnMouseClicked(this::handleMouseClick);
        return square;
    }

    private void createPlayer() {
        model.positionProperty(model.getPlayer()).addListener(this::piecePositionChange);
        var player = createPiece(Color.valueOf("skyblue"));
        getSquare(model.getPiecePosition(model.getPlayer())).getChildren().add(player);

    }

    private  void cteateMonster() {
        model.positionProperty(model.getMonster()).addListener(this::piecePositionChange);
        var monster = createPiece(Color.valueOf("black"));
        getSquare(model.getPiecePosition(model.getMonster())).getChildren().add(monster);
    }

    private Circle createPiece(Color color) {
        var piece = new Circle(40);
        piece.setFill(color);
        return piece;
    }

    @FXML
    private void handleMouseClick(MouseEvent event) {
        var square = (StackPane) event.getSource();
        var row = GridPane.getRowIndex(square);
        var col = GridPane.getColumnIndex(square);
        var position = new Position(row, col);
        Logger.debug("Click on square {}", position);
        handleClickOnSquare(position, event);
    }

    private void handleClickOnSquare(Position position, MouseEvent event) {
        switch (selectionPhase) {
            case SELECT_FROM -> {
                if (selectablePositions.contains(position)) {
                    selectPosition(position);
                    alterSelectionPhase();
                }
            }
            case SELECT_TO -> {
                if (selectablePositions.contains(position)) {
                    var direction = PawnDirection.of(position.row() - selected.row(), position.col() - selected.col());
                    Logger.debug("Moving piece {}", direction);
                    model.move(model.getPlayer(), direction);
                    deselectSelectedPosition();
                    alterSelectionPhase();

                    model.monsterMove();
                    model.monsterMove();
                    if (model.isEnd()){
                        end(event);
                    }
                }

            }
        }
    }

    private void end(MouseEvent event) {
        Alert about = new Alert(Alert.AlertType.INFORMATION);
        about.setTitle("Vége");
        about.setHeaderText("JavaFX Text Editor");

        if (model.isMonsterWin()) {
            about.setHeaderText("A szörny nyert");
            about.showAndWait();
            Logger.info("Monster win");
            try {
                switchToMenu(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            Logger.info("Win");
            try {
                switchToWin(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void alterSelectionPhase() {
        selectionPhase = selectionPhase.alter();
        hideSelectablePositions();
        setSelectablePositions();
        showSelectablePositions();
    }

    private void selectPosition(Position position) {
        selected = position;
        showSelectedPosition();
    }

    private void showSelectedPosition() {
        var square = getSquare(selected);
        square.getStyleClass().add("selected");
    }

    private void deselectSelectedPosition() {
        hideSelectedPosition();
        selected = null;
    }

    private void hideSelectedPosition() {
        var square = getSquare(selected);
        square.getStyleClass().remove("selected");
    }

    private void setSelectablePositions() {
        selectablePositions.clear();
        switch (selectionPhase) {
            case SELECT_FROM -> selectablePositions.add(model.getPlayer().getPosition());
            case SELECT_TO -> {
                for (var direction : model.getValidMoves(model.getPlayer())) {
                    selectablePositions.add(selected.moveTo(direction));
                }
            }
        }
    }

    private void showSelectablePositions() {
        for (var selectablePosition : selectablePositions) {
            var square = getSquare(selectablePosition);
            square.getStyleClass().add("selectable");
        }
    }

    private void hideSelectablePositions() {
        for (var selectablePosition : selectablePositions) {
            var square = getSquare(selectablePosition);
            square.getStyleClass().remove("selectable");
        }
    }

    private StackPane getSquare(Position position) {
        for (var child : board.getChildren()) {
            if (GridPane.getRowIndex(child) == position.row() && GridPane.getColumnIndex(child) == position.col()) {
                return (StackPane) child;
            }
        }
        throw new AssertionError();
    }

    private void piecePositionChange(ObservableValue<? extends Position> observable, Position oldPosition, Position newPosition) {
        Logger.debug("{} move: {} -> {}", model.getPiece(newPosition).getType().name(), oldPosition, newPosition);
        StackPane oldSquare = getSquare(oldPosition);
        StackPane newSquare = getSquare(newPosition);
        newSquare.getChildren().addAll(oldSquare.getChildren());
        oldSquare.getChildren().clear();
    }

    private void switchToMenu(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/menu.fxml"));
        stage.setTitle("Menu");
        stage.setScene(new Scene(root));
        Logger.info("Going to menu");
        stage.show();
    }

    private void switchToWin(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/win.fxml"));
        Parent root = fxmlLoader.load();
        WinController controller = fxmlLoader.<WinController>getController();
        controller.setSteps(model.getSteps());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
