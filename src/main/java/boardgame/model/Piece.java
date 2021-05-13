package boardgame.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Egy {@code Piece} objetumot reprezentál.
 */
public class Piece {

    private final PieceType type;
    private final ObjectProperty<Position> position = new SimpleObjectProperty<>();

    /**
     * Egy {@code Piece} objektumot hoz létre.
     *
     * @param type egy darab típus
     * @param position egy kezdő pozició
     */
    public Piece(PieceType type, Position position) {
        this.type = type;
        this.position.set(position);
    }

    /**
     * A {@code Piece} típusát adja vissza.
     *
     * @return {@code Piece} típusát adja vissza
     */
    public PieceType getType() {
        return type;
    }

    /**
     * A {@code Piece} objektum pozicióját adja vissza.
     *
     * @return a {@code Piece} objektum pozicióját adja vissza
     */
    public Position getPosition() {
        return position.get();
    }

    /**
     * Egy {@code Piece} objektumot mozgat az adott irányba.
     *
     * @param direction az irány amelybe mozgunk
     */
    public void moveTo(Direction direction) {
        Position newPosition = position.get().moveTo(direction);
        position.set(newPosition);
    }

    /**
     * Egy pozicióhoz tartozó tulajdonságot add vissza.
     *
     * @return egy pozicót ad vissza
     */
    public ObjectProperty<Position> positionProperty() {
        return position;
    }

    /**
     * Az objektum kiíratásához szükséges sztringet állít elő.
     *
     * @return egy olyan sztring amely az objektumot reprezentálja
     */
    public String toString() {
        return type.toString() + position.get().toString();
    }

}
