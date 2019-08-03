package exercises.holyMoly.entities.destructable.enemy.boss;

/**
 * Represents the state of the {@link Boss}'s current health.
 */
public enum BossHealth {

    HIGH,
    MEDIUM,
    LOW,
    WEAK;

    /**
     * Gets this instance of boss health's name in lower case.
     *
     * @return the name in lower case
     */
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
