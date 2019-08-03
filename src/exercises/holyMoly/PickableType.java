package exercises.holyMoly;

/**
 * Represents the {@link Pickable} types in this {@link Game}.
 */
public enum PickableType {

    CROSS,
    MEGAPHONE,
    BATTERY,
    BAZOOKA,
    ROCKET;

    /**
     * Gets this instance of pickable type's name in lower case.
     *
     * @return the name in lower case
     */
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
