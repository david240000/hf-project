package boardgame.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WallTypeTest {

    Wall wall;

    @Test
    void getBedDirections() {
        wall = new Wall(new Position(0,0), WallType.UP);
        assertSame(new PawnDirection[]{PawnDirection.UP}, wall.getWallType().getBedDirections());
    }
}