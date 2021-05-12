package boardgame.model;

public class LastResult {

    private static int steps;

    private LastResult(){};

    public static void setSteps(int steps) {
        LastResult.steps = steps;
    }

    public static int getSteps(){
        return steps;
    }
}
