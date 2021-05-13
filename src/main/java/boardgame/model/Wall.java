package boardgame.model;

/**
 * Egy pozicióhoz faltipus reprezentáló osztály
 */
public class Wall {

    private Position position;

    private WallType wallType;

    /**
     * Egy {@code Wall} objetumot hoz létre.
     *
     * @param position egy pozició
     * @param wallType egy fal típús
     */
    public Wall(Position position, WallType wallType) {
        this.position = position;
        this.wallType = wallType;
    }

    public Position getPosition() {
        return position;
    }

    public WallType getWallType() {
        return wallType;
    }
}
