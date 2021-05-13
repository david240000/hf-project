package boardgame.model;

import javafx.beans.property.ObjectProperty;

import java.util.*;
import java.util.stream.Stream;

/**
 * Egy játéktáblát ánrázoló osztály.
 */
public class BoardGameModel {

    /**
     * A tábla mérete.
     */
    public static int BOARD_SIZE = 6;

    /**
     * A tábla egyes mezőihez tartozó faltipusokat tartalmazza.
     */
    public static WallType[][] map = {{WallType.UP_DOWN_LEFT, WallType.UP, WallType.UP, WallType.UP, WallType.RIGHT, WallType.UP_RIGHT_LEFT},
                                        {WallType.UP_LEFT, WallType.RIGHT_DOWN, WallType.LEFT, WallType.RIGHT, WallType.RIGHT_DOWN_LEFT, WallType.RIGHT_LEFT},
                                        {WallType.RIGHT_LEFT, WallType.UP_RIGHT_LEFT, WallType.RIGHT_DOWN_LEFT, WallType.LEFT, WallType.UP_DOWN, WallType.RIGHT},
                                        {WallType.RIGHT_LEFT, WallType.LEFT, WallType.UP_DOWN, WallType.RIGHT, WallType.UP_LEFT, WallType.RIGHT},
                                        {WallType.RIGHT_LEFT, WallType.RIGHT_LEFT, WallType.UP_LEFT, WallType.NONE, WallType.NONE, WallType.RIGHT},
                                        {WallType.DOWN_LEFT, WallType.DOWN, WallType.DOWN, WallType.DOWN, WallType.DOWN, WallType.RIGHT_DOWN}};

    /**
     * A cél poziciót ábrázolja, ahonnan ki lehet jutni a labirintusból.
     */
    public static Position GOAL= new Position(0,4);

    private int steps;

    private final Piece player;

    private final Piece monster;

    private final Wall[][] walls = new Wall[BOARD_SIZE][BOARD_SIZE];

    /**
     * A játék kezdőállapotát hozza létre.
     */
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

    public int getSteps(){return steps;}

    public Wall getWall(int row, int col) {
        return walls[row][col];
    }

    /**
     * Egy pozicóhoz meghatározza hogy a szörny vagy a játékos van rajta.
     *
     * @param position a poició melyről tudni szeretnénk mi áll rajta
     * @return a szornyet vagy a jáékost adja vissza
     */
    public Piece getPiece(Position position) {
        if (position.equals(getMonster().getPosition())) {
            return monster;
        }
        return player;
    }

    /**
     *  Egy {@code Piece} objetumhoz tartozó poziót ad vissza.
     *
     * @param piece az objektum menjnek a pociójára vagyunk kíváncsiak
     * @return az pieccehez tartozo pozicioval tér vissza
     */
    public Position getPiecePosition(Piece piece) {
        return piece.getPosition();
    }

    /**
     * Egy {@code Piece} objektumhoz tartozó pozició tulajdonságot határoz meg.
     *
     * @param piece a {@code Piece} objektum melnynek a poziciótulajdonságára vagyunk kívánnncsiak
     * @return a piece pozició tulajdonsága
     */
    public ObjectProperty<Position> positionProperty(Piece piece) {
        return piece.positionProperty();
    }

    /**
     * Megmodnja hogy  egy mozgás végrehajtható vagy sem.
     * Egy adott {@code Piece} objektum egy adott irányba való mozgatásáról eldönti, hogy szabályos e vagy sem.
     *
     * @param piece az a darab olyektum amelynek az adott irányba való mozgásának helyeségéy hatáározza meg
     * @param direction az az irány amelybe szerentnénk mozgati a darabot.
     * @return
     */
    public boolean isValidMove(Piece piece, PawnDirection direction) {
        Position oldPosition = piece.getPosition();
        WallType wall = walls[oldPosition.row()][oldPosition.col()].getWallType();
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

    /**
     * Egy {@code Piece} objektumhoz tartozó szabályos lépéseket határozza meg.
     *
     * @param piece az objektum melynek szabályos lépéseire kíváncsiak vagyunk
     * @return a lehetséges irányok egy halmazával tér vissza
     */
    public Set<PawnDirection> getValidMoves(Piece piece) {
        EnumSet<PawnDirection> validMoves = EnumSet.noneOf(PawnDirection.class);
        for (var direction : PawnDirection.values()) {
            if (isValidMove(piece, direction)) {
                validMoves.add(direction);
            }

        }
        return validMoves;
    }

    /**
     * A {@code Piece} objektumot az adott irányba mozgatja.
     *
     * @param player a mozgatni kívánt objektum
     * @param direction a mozgatás irányta
     */
    public void move(Piece player, PawnDirection direction) {
        player.moveTo(direction);
        steps++;
    }

    /**
     * A szörny mozgását végzi.
     */
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


    /**
     * Egy poziciórol eldönti hogy a táblán van e vagy sem.
     *
     * @param position a pozició amiről el akarjuk dönteni hogy a táblán van e vagy sem
     * @return logikai értékkel tér vissza attól függőe, hpgy a pozició a táblán van e vagy sem
     */
    public static boolean isOnBoard(Position position) {
        return 0 <= position.row() && position.row() < BOARD_SIZE
                && 0 <= position.col() && position.col() < BOARD_SIZE;
    }


    /**
     * Azt dönti el, hogy a szörny nyert vagy sem.
     *
     * @return logikai értékell tér viszza azzal kapcsolatban hogy a szörny nyert e
     */
    public boolean isMonsterWin() {
        return getPlayer().getPosition().equals(getMonster().getPosition());
    }

    /**
     * Azt dönti  el, hogy a játékos nyert e vagy sem.
     *
     * @return logikai értékkel tér vissza attól függően, hogy nyert e a játékos
     */
    public boolean isPlayerWin() {
        return getPlayer().getPosition().equals(GOAL);
    }

    /**
     * Eldőnti, hogy véget ért e a játék.
     *
     * @return logikai értékkel tér vissza, hogy véget ért e a játék
     */
    public boolean isEnd() {
        return isMonsterWin() || isPlayerWin();
    }

    public String toString() {
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        joiner.add(player.toString());
        joiner.add(monster.toString());
        return joiner.toString();
    }

}
