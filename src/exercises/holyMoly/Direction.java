package exercises.holyMoly;

/**
 * Represents the abstraction of direction.
 */
public enum Direction {

    UP(0, -1),
    RIGHT(1, 0),
    DOWN(0, 1),
    LEFT(-1, 0),
    NONE(0, 0);

    private int polarityX;
    private int polarityY;

    /**
     * Constructor method. Takes in the arguments for property initialization.
     *
     * @param polarityX the polarity of x
     * @param polarityY the polarity of y
     */
    Direction(int polarityX, int polarityY) {

        this.polarityX = polarityX;
        this.polarityY = polarityY;
    }

    /**
     * Gets the polarity of x.
     *
     * @return the polarity of x
     */
    public int getPolarityX() {
        return polarityX;
    }

    /**
     * Gets the polarity of y.
     *
     * @return the polarity of y
     */
    public int getPolarityY() {
        return polarityY;
    }

    /**
     * Gets this instance of direction's name in lower case.
     *
     * @return the name in lower case
     */
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
