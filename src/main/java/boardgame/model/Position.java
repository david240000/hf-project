package boardgame.model;

/**
 * Egy poziciót ábrázoló osztály.
 *
 * @param row sor index
 * @param col oszlop index
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

    /**
     * Az objektum kiíratásához szükséges sztringet állít elő.
     *
     * @return egy olyan sztring amely az objektumot reprezentálja
     */
    public String toString() {
        return String.format("(%d,%d)", row, col);
    }

}