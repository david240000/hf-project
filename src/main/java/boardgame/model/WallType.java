package boardgame.model;

/**
 * Egy faltípus amely egy adott poziciót jellemez.
 */
public enum WallType {
    /**
     * Fal csak fent.
     */
    UP(new PawnDirection[]{PawnDirection.UP}),
    /**
     * Fal csak jobbra.
     */
    RIGHT(new PawnDirection[]{PawnDirection.RIGHT}),
    /**
     * Fal csak lent.
     */
    DOWN(new PawnDirection[]{PawnDirection.DOWN}),
    /**
     * Fal csak balra.
     */
    LEFT(new PawnDirection[]{PawnDirection.LEFT}),
    /**
     * Fal fent és jobbra.
     */
    UP_RIGHT(new PawnDirection[]{PawnDirection.UP, PawnDirection.RIGHT}),
    /**
     * Fal fent és lent.
     */
    UP_DOWN(new PawnDirection[]{PawnDirection.UP, PawnDirection.DOWN}),
    /**
     * Fal fent és balra.
     */
    UP_LEFT(new PawnDirection[]{PawnDirection.UP, PawnDirection.LEFT}),
    /**
     * Fal jobbra és lent.
     */
    RIGHT_DOWN(new PawnDirection[]{PawnDirection.RIGHT, PawnDirection.DOWN}),
    /**
     * Fal jobbra és balra.
     */
    RIGHT_LEFT(new PawnDirection[]{PawnDirection.RIGHT, PawnDirection.LEFT}),
    /**
     * Fal lent és balra.
     */
    DOWN_LEFT(new PawnDirection[]{PawnDirection.DOWN, PawnDirection.LEFT}),
    /**
     * Fal fent, jobbra és lent.
     */
    UP_RIGHT_DOWN(new PawnDirection[]{PawnDirection.UP, PawnDirection.RIGHT, PawnDirection.DOWN}),
    /**
     * Fal fent, jobbra és balra.
     */
    UP_RIGHT_LEFT(new PawnDirection[]{PawnDirection.UP, PawnDirection.RIGHT, PawnDirection.LEFT}),
    /**
     * Fal jobbra, lent és balra.
     */
    UP_DOWN_LEFT(new PawnDirection[]{PawnDirection.UP, PawnDirection.DOWN, PawnDirection.LEFT}),
    /**
     * Fal jobbra, lent és balra.
     */
    RIGHT_DOWN_LEFT(new PawnDirection[]{PawnDirection.RIGHT, PawnDirection.DOWN, PawnDirection.LEFT}),
    /**
     * Nincs fal sehol.
     */
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
