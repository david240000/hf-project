package boardgame.model;

import java.util.ArrayList;

@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.Data
public class Results {

    private ArrayList<Result> list = new ArrayList<>();
}