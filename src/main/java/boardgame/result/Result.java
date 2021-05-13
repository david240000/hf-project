package boardgame.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

/**
 * Egy játékmenet eredményének reprezentálására használható osztály.
 */
public class Result implements Comparable<Result> {

    private String name;
    private int steps;

    /**
     * Két {@code Result} típusú objektum összehasonlítását végzi.
     * A két {@code Result} objetum összehasolításakor a lépésszámot veszi figyelembe.
     *
     * @param otherResult egy {@code Result} objektum
     * @return egylogikai értékkel tér vissza attól függően, hogy a paraméterként kapott objektum lépésszáma nagyobb e vagy kissebb az adott objektuménál
     */
    @Override
    public int compareTo(Result otherResult){
        return Integer.compare(getSteps(), otherResult.getSteps());
    }
}