package boardgame.result;

import java.util.ArrayList;

@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.Data

/**
 * Eredmények tárolására használható osztály.
 */
public class Results {

    private ArrayList<Result> list = new ArrayList<>();
}