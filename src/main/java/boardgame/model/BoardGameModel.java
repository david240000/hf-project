package boardgame.model;

import javafx.beans.property.ObjectProperty;

import java.util.*;
import java.util.stream.Stream;

public class BoardGameModel {

    public static int BOARD_SIZE = 6;

    public static WallType[][] map = {{WallType.UP_DOWN_LEFT, WallType.UP, WallType.UP, WallType.UP, WallType.RIGHT, WallType.UP_RIGHT_LEFT},
                                        {WallType.UP_LEFT, WallType.RIGHT_DOWN, WallType.LEFT, WallType.RIGHT, WallType.RIGHT_DOWN_LEFT, WallType.RIGHT_LEFT},
                                        {WallType.RIGHT_LEFT, WallType.UP_RIGHT_LEFT, WallType.RIGHT_DOWN_LEFT, WallType.LEFT, WallType.UP_DOWN, WallType.RIGHT},
                                        {WallType.RIGHT_LEFT, WallType.LEFT, WallType.UP_DOWN, WallType.RIGHT, WallType.UP_LEFT, WallType.RIGHT},
                                        {WallType.RIGHT_LEFT, WallType.RIGHT_LEFT, WallType.UP_LEFT, WallType.NONE, WallType.NONE, WallType.RIGHT},
                                        {WallType.DOWN_LEFT, WallType.DOWN, WallType.DOWN, WallType.DOWN, WallType.DOWN, WallType.RIGHT_DOWN}};

    public static Position GOAL= new Position(0,4);

    private static int steps;

    private final Piece player;

    private final Piece monster;

    private final Wall[][] walls = new Wall[BOARD_SIZE][BOARD_SIZE];

    public BoardGameModel() {
        for (int i=0; i<BOARD_SIZE; i++){
            for (int j=0; j<BOARD_SIZE; j++){
                walls[i][j] = (new Wall(new Position(i, j), map[i][j]));
            }
        }
        this.player = new Piece(PieceType.PLAYER, new Position(0, 0));
        this.monster = new Piece(PieceType.MONSTER, new Position(2, 4));
        steps = 0;
    }

    public Piece getPlayer() {
        return player;
    }

    public Piece getMonster() {
        return monster;
    }

    public static int getSteps(){return steps;}

    public Wall getWall(int row, int col) {
        return walls[row][col];
    }

    public Piece getPiece(Position position) {
        if (position.equals(getMonster().getPosition())) {
            return monster;
        }
        return player;
    }

    private void checkPieces(Piece[] pieces) {
        var seen = new HashSet<Position>();
        for (var piece : pieces) {
            if (! isOnBoard(piece.getPosition()) || seen.contains(piece.getPosition())) {
                throw new IllegalArgumentException();
            }
            seen.add(piece.getPosition());
        }
    }


    public PieceType getPieceType(Piece piece) {
        return piece.getType();
    }

    public Position getPiecePosition(Piece piece) {
        return piece.getPosition();
    }

    public ObjectProperty<Position> positionProperty(Piece piece) {
        return piece.positionProperty();
    }

    public boolean isValidMove(Piece piece, PawnDirection direction) {
        Position oldPosition = piece.getPosition();
        WallType wall = walls[oldPosition.row()][oldPosition.col()].wallType();
        Position newPosition = piece.getPosition().moveTo(direction);
        if (! isOnBoard(newPosition)) {
            return false;
        }
        if (direction == PawnDirection.UP && Stream.of(wall.getBedDirections()).anyMatch(x -> x == PawnDirection.UP)){
            return false;
        }
        if (direction == PawnDirection.RIGHT && Stream.of(wall.getBedDirections()).anyMatch(x -> x == PawnDirection.RIGHT)){
            return false;
        }
        if (direction == PawnDirection.DOWN && Stream.of(wall.getBedDirections()).anyMatch(x -> x == PawnDirection.DOWN)){
            return false;
        }
        if (direction == PawnDirection.LEFT && Stream.of(wall.getBedDirections()).anyMatch(x -> x == PawnDirection.LEFT)){
            return false;
        }

        return true;
    }

    public Set<PawnDirection> getValidMoves(Piece piece) {
        EnumSet<PawnDirection> validMoves = EnumSet.noneOf(PawnDirection.class);
        for (var direction : PawnDirection.values()) {
            if (isValidMove(piece, direction)) {
                validMoves.add(direction);
            }

        }
        return validMoves;
    }

    public void move(Piece player, PawnDirection direction) {
        player.moveTo(direction);
        steps++;
    }

    public void monsterMove() {
        var goal = getPlayer().getPosition();
        var position = getMonster().getPosition();
        if (goal.col() < position.col() && isValidMove(getMonster(), PawnDirection.LEFT)) {
            getMonster().moveTo(PawnDirection.LEFT);
        }else if (goal.col() > position.col() && isValidMove(getMonster(), PawnDirection.RIGHT)){
            getMonster().moveTo(PawnDirection.RIGHT);
        }else if (goal.row() < position.row() && isValidMove(getMonster(), PawnDirection.UP)){
            getMonster().moveTo(PawnDirection.UP);
        }else if (goal.row() > position.row() && isValidMove(getMonster(), PawnDirection.DOWN)) {
            getMonster().moveTo(PawnDirection.DOWN);
        }else if (! goal.equals(position)){
            Set<PawnDirection> validMoves = getValidMoves(monster);
            //int item = new Random().nextInt(validMoves.size());
            //int i = 0;
            var direction = validMoves.iterator().next();
            /*for(PawnDirection dir : validMoves)
            {
                if (i == item) {
                    direction = dir;
                    break;
                }
                i++;
            }*/
            getMonster().moveTo(direction);
        }
    }



    public static boolean isOnBoard(Position position) {
        return 0 <= position.row() && position.row() < BOARD_SIZE
                && 0 <= position.col() && position.col() < BOARD_SIZE;
    }

    public List<Position> getPiecePositions() {
        List<Position> positions = new ArrayList<>(2);
        positions.add(player.getPosition());
        positions.add(monster.getPosition());

        return positions;
    }

    public boolean isMonsterWin() {
        return getPlayer().getPosition().equals(getMonster().getPosition());
    }

    public boolean isPlayerWin() {
        return getPlayer().getPosition().equals(GOAL);
    }

    public boolean isEnd() {
        return isMonsterWin() || isPlayerWin();
    }

    public String toString() {
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        joiner.add(player.toString());
        joiner.add(monster.toString());
        return joiner.toString();
    }

    public static void main(String[] args) {
        BoardGameModel model = new BoardGameModel();
        System.out.println(model);
    }

}
