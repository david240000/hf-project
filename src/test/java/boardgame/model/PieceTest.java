package boardgame.model;

import javafx.geometry.Pos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    Piece piece;

    @BeforeEach
    void init() {
        piece = new Piece(PieceType.PLAYER, new Position(0,0));
    }

    void assertPosition(int row, int col, Position position){
        assertAll("position",
                () -> assertEquals(row, position.row()),
                () -> assertEquals(col, position.col()));
    }


    @Test
    void moveTo_up() {
        Position oldposition = piece.getPosition();
        piece.moveTo(PawnDirection.UP);
        Position newposition = piece.getPosition();
        assertPosition(oldposition.row()-1, oldposition.col(), newposition);
    }

    @Test
    void moveTo_right() {
        Position oldposition = piece.getPosition();
        piece.moveTo(PawnDirection.RIGHT);
        Position newposition = piece.getPosition();
        assertPosition(oldposition.row(), oldposition.col()+1, newposition);
    }

    @Test
    void moveTo_down() {
        Position oldposition = piece.getPosition();
        piece.moveTo(PawnDirection.DOWN);
        Position newposition = piece.getPosition();
        assertPosition(oldposition.row()+1, oldposition.col(), newposition);
    }

    @Test
    void moveTo_left() {
        Position oldposition = piece.getPosition();
        piece.moveTo(PawnDirection.LEFT);
        Position newposition = piece.getPosition();
        assertPosition(oldposition.row(), oldposition.col()-1, newposition);
    }

    @Test
    void testToString() {
        assertEquals("PLAYER(0,0)", piece.toString());
    }
}