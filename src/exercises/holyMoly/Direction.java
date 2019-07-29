package exercises.holyMoly;

public enum Direction {

    UP(0, -1),
    RIGHT(1, 0),
    DOWN(0, 1),
    LEFT(-1, 0);

    private int polarityX;
    private int polarityY;

    Direction(int polarityX, int polarityY) {
        this.polarityX = polarityX;
        this.polarityY = polarityY;
    }

    public int getPolarityX() {
        return polarityX;
    }

    public int getPolarityY() {
        return polarityY;
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
