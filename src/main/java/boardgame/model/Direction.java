package boardgame.model;

/**
 * Az irányokhoz tartoző interface.
 */
public interface Direction {

    /**
     * A sorváltozáshoz tartozó metódus.
     *
     * @return a sorváltozás mértéke
     */
    int getRowChange();

    /**
     * Az oszlopváltozáshoz tartozó metódus.
     *
     * @return az oszlopváltozás mértéke
     */
    int getColChange();

}
