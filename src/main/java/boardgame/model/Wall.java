package boardgame.model;

import static java.lang.Math.abs;

public class Wall {

    private Position position;

    private WallType wallType;

    public Wall(Position position, WallType wallType) {
        this.position = position;
        this.wallType = wallType;
    }

    public Position getPosition() {
        return position;
    }

    public WallType wallType() {
        return wallType;
    }
}
