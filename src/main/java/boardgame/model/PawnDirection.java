package boardgame.model;

/**
 * Egy irány határoz meg.
 */
public enum PawnDirection implements Direction {
    /**
     * Felfele irány.
     */
    UP(-1, 0),
    /**
     * Jobbra irány.
     */
    RIGHT(0, 1),
    /**
     * Lefele irány.
     */
    DOWN(1, 0),
    /**
     * Balra irány.
     */
    LEFT(0, -1);

    private final int rowChange;
    private final int colChange;

    /**
     * Egy {@code PawnDirection} objektumot hoz létre.
     * Bemutatja hogy egy irányba való elmozdulás milyen sor és oszlopváltozásokkal jár.
     *
     * @param rowChange egy egész szám amely a sorváltozást reprezentálja
     * @param colChange egy egész szám amely a oszlopváltozást reprezentálja
     */
    PawnDirection(int rowChange, int colChange) {
        this.rowChange = rowChange;
        this.colChange = colChange;
    }

    /**
     * A sorváltozáshoz tartozó metódus.
     *
     * @return a sorváltozás mértéke
     */
    public int getRowChange() {
        return rowChange;
    }

    /**
     * Az oszlopváltozáshoz tartozó metódus.
     *
     * @return az oszlopváltozás mértéke
     */
    public int getColChange() {
        return colChange;
    }

    /**
     *  Adott sor és oszlopváltozáshoz határoz meg egy irányt.
     *
     * @param rowChange egy egész szám amely a sorváltozást reprezentálja
     * @param colChange egy egész szám amely a oszlopváltozást reprezentálja
     * @return egy irányt ad vissza
     * @throws IllegalArgumentException ha rossz a paraméterezés
     */
    public static PawnDirection of(int rowChange, int colChange) {
        for (var direction : values()) {
            if (direction.rowChange == rowChange && direction.colChange == colChange) {
                return direction;
            }
        }
        throw new IllegalArgumentException();
    }

}
