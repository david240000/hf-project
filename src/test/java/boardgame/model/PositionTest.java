package boardgame.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    Position position;

    @BeforeEach
    void init(){
        position = new Position(0,0);
    }

    void assertPosition(int row, int col, Position position){
        assertAll("position",
                () -> assertEquals(row, position.row()),
                () -> assertEquals(col, position.col()));
    }

    @Test
    void moveTo() {
        assertPosition(-1, 0,position.moveTo(PawnDirection.UP));
        assertPosition(0, 1, position.moveTo(PawnDirection.RIGHT));
        assertPosition(1, 0, position.moveTo(PawnDirection.DOWN));
        assertPosition(0, -1, position.moveTo(PawnDirection.LEFT));
    }

    @Test
    void testToString() {
        assertEquals("(0,0)", position.toString());
    }
}