package boardgame.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Result implements Comparable<Result> {

    private String name;
    private int steps;

    @Override
    public int compareTo(Result otherResult){
        return Integer.compare(getSteps(), otherResult.getSteps());
    }
}