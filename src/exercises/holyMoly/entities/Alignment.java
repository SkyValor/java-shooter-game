package exercises.holyMoly.entities;

/**
 * Represents the abstraction of alignment.
 */
public enum Alignment {

    HORIZONTAL,
    VERTICAL;

    /**
     * Gets this instance of alignment's name in lower case.
     *
     * @return the name in lower case
     */
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
