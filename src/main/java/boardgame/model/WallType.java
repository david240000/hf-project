package boardgame.model;

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

    private final PawnDirection[] bedirections;

    WallType(PawnDirection[] directions) {
        this.bedirections =directions;
    }

    public PawnDirection[] getBedDirections(){
        return bedirections;
    }
}
