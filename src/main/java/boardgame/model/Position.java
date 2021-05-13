package boardgame.model;

/**
 * Egy poziciót ábrázoló osztály.
 */
public record Position(int row, int col) {

    /**
     *Egy pozicó értékét a iránynak megfelelően változtatja.
     *
     * @param direction egy irány
     * @return egy új {@code Position} objektumot ad vissza
     */
    public Position moveTo(Direction direction) {
        return new Position(row + direction.getRowChange(), col + direction.getColChange());
    }

    public String toString() {
        return String.format("(%d,%d)", row, col);
    }

}