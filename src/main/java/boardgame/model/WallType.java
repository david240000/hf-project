package boardgame.model;

/**
 * Egy faltípus amely egy adott poziciót jellemez
 */
public enum WallType {
    UP(new PawnDirection[]{PawnDirection.UP}),
    RIGHT(new PawnDirection[]{PawnDirection.RIGHT}),
    DOWN(new PawnDirection[]{PawnDirection.DOWN}),
    LEFT(new PawnDirection[]{PawnDirection.LEFT}),
    UP_RIGHT(new PawnDirection[]{PawnDirection.UP, PawnDirection.RIGHT}),
    UP_DOWN(new PawnDirection[]{PawnDirection.UP, PawnDirection.DOWN}),
    UP_LEFT(new PawnDirection[]{PawnDirection.UP, PawnDirection.LEFT}),
    RIGHT_DOWN(new PawnDirection[]{PawnDirection.RIGHT, PawnDirection.DOWN}),
    RIGHT_LEFT(new PawnDirection[]{PawnDirection.RIGHT, PawnDirection.LEFT}),
    DOWN_LEFT(new PawnDirection[]{PawnDirection.DOWN, PawnDirection.LEFT}),
    UP_RIGHT_DOWN(new PawnDirection[]{PawnDirection.UP, PawnDirection.RIGHT, PawnDirection.DOWN}),
    UP_RIGHT_LEFT(new PawnDirection[]{PawnDirection.UP, PawnDirection.RIGHT, PawnDirection.LEFT}),
    UP_DOWN_LEFT(new PawnDirection[]{PawnDirection.UP, PawnDirection.DOWN, PawnDirection.LEFT}),
    RIGHT_DOWN_LEFT(new PawnDirection[]{PawnDirection.RIGHT, PawnDirection.DOWN, PawnDirection.LEFT}),
    NONE(new PawnDirection[]{});

    private final PawnDirection[] beddirections;

    /**
     * Létrehoz egy {@code WallType} objektumot.
     *
     * @param directions egy irányok egy töbje
     */
    WallType(PawnDirection[] directions) {
        this.beddirections = directions;
    }

    /**
     * Egy faltípushoz visszadja azokat az irányokat amelyekbe nem lehet haladni.
     *
     * @return egy tőmbőt ad vissza melben a rossz irányok vannak
     */
    public PawnDirection[] getBedDirections(){
        return beddirections;
    }
}
