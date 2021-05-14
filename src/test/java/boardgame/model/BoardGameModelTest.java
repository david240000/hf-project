package boardgame.model;

import org.ietf.jgss.Oid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BoardGameModelTest {

    BoardGameModel model = new BoardGameModel();


    Piece player1 = new Piece(PieceType.PLAYER, new Position(0,0));

    Piece monster1 = new Piece(PieceType.MONSTER, new Position(4,4));

    Piece player2 = new Piece(PieceType.PLAYER, new Position(-1,9));

    Piece player3 = new Piece(PieceType.PLAYER, new Position(0, 4));

    Piece monster2 = new Piece(PieceType.MONSTER, new Position(0, 0));

    BoardGameModel model1 = new BoardGameModel(player1, monster1);

    BoardGameModel model2 = new BoardGameModel(player3, monster1);

    BoardGameModel model3 = new BoardGameModel(player1, monster2);

    void assertPosition(int row, int col, Position position){
        assertAll("position",
                () -> assertEquals(row, position.row()),
                () -> assertEquals(col, position.col()));
    }

    @Test
    void isValidMove_player1() {
        assertFalse(model.isValidMove(player1, PawnDirection.UP));
        assertTrue(model.isValidMove(player1, PawnDirection.RIGHT));
        assertFalse(model.isValidMove(player1, PawnDirection.DOWN));
        assertFalse(model.isValidMove(player1, PawnDirection.LEFT));
    }

    @Test
    void isValidMove_monster1() {
        assertTrue(model.isValidMove(monster1, PawnDirection.UP));
        assertTrue(model.isValidMove(monster1, PawnDirection.RIGHT));
        assertTrue(model.isValidMove(monster1, PawnDirection.DOWN));
        assertTrue(model.isValidMove(monster1, PawnDirection.LEFT));
    }

    @Test
    void getValidMoves_player1() {
        Set<PawnDirection> expectedset = Stream.of(PawnDirection.RIGHT).collect(Collectors.toSet());
        assertEquals(expectedset, model.getValidMoves(player1));
    }

    @Test
    void getValidMoves_monster1() {
        Set<PawnDirection> expectedset = Stream.of(PawnDirection.UP, PawnDirection.RIGHT, PawnDirection.DOWN, PawnDirection.LEFT).collect(Collectors.toSet());
        assertEquals(expectedset, model.getValidMoves(monster1));
    }

    @Test
    void getValidMoves_player3() {
        Set<PawnDirection> expectedset = Stream.of(PawnDirection.DOWN, PawnDirection.LEFT).collect(Collectors.toSet());
        assertEquals(expectedset, model.getValidMoves(player3));
    }

    @Test
    void move_up() {
        Piece player1 = new Piece(PieceType.PLAYER, new Position(0,0));
        model.move(player1,PawnDirection.UP);
        assertPosition(-1 ,0 ,player1.getPosition());
    }

    @Test
    void move_right() {
        Piece player1 = new Piece(PieceType.PLAYER, new Position(0,0));
        model.move(player1,PawnDirection.RIGHT);
        assertPosition(0 ,1 ,player1.getPosition());
    }

    @Test
    void move_down() {
        Piece player1 = new Piece(PieceType.PLAYER, new Position(0,0));
        model.move(player1,PawnDirection.DOWN);
        assertPosition(1 ,0 ,player1.getPosition());
    }

    @Test
    void move_left() {
        Piece player1 = new Piece(PieceType.PLAYER, new Position(0,0));
        model.move(player1,PawnDirection.LEFT);
        assertPosition(0 ,-1 ,player1.getPosition());
    }

    @Test
    void monsterMove() {
        BoardGameModel model = new BoardGameModel();
        model.monsterMove();
        model.monsterMove();
        assertPosition(1,3,model.getMonster().getPosition());
    }

    @Test
    void isOnBoard() {
        assertTrue(model.isOnBoard(player1.getPosition()));
        assertFalse(model.isOnBoard(player2.getPosition()));
    }

    @Test
    void isMonsterWin() {
        assertFalse(model.isMonsterWin());
        assertTrue(model3.isMonsterWin());
    }

    @Test
    void isPlayerWin() {
        assertFalse(model.isPlayerWin());
        assertTrue(model2.isPlayerWin());
    }

    @Test
    void isEnd() {
        assertFalse(model.isEnd());
        assertTrue(model2.isEnd());
        assertTrue(model3.isEnd());
    }

    @Test
    void testToString() {
        assertEquals("[PLAYER(0,0),MONSTER(2,4)]", model.toString());
        assertEquals("[PLAYER(0,0),MONSTER(0,0)]", model3.toString());
    }
}