package boardgame.model;

/**
 * Egy pozicióhoz faltipus reprezentáló osztály.
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

    /**
     * A {@code Wall} pozicióját adja vissza.
     *
     * @return a {@code Wall} pozicióját adja vissza
     */
    public Position getPosition() {
        return position;
    }

    /**
     * A {@code Wall} típusát adja vissza.
     *
     * @return a {@code Wall} típusát adja vissza
     */
    public WallType getWallType() {
        return wallType;
    }
}
