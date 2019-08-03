package exercises.holyMoly.entities.destructable.enemy;

import exercises.holyMoly.entities.Entity;
import exercises.holyMoly.entities.player.Player;

public interface Enemy extends Entity {

    /**
     * Sets the velocity of this unit.
     *
     * @param velocity the velocity to set
     */
    void setVelocity(int velocity);

    /**
     * Gets the velocity of this unit.
     *
     * @return the velocity
     */
    int getVelocity();

    /**
     * Sets the slow speed of this unit.
     *
     * @param slowSpeed the value to set
     */
    void setSlowSpeed(int slowSpeed);

    /**
     * Gets the slow speed of this unit.
     *
     * @return the slow speed
     */
    int getSlowSpeed();

    /**
     * Sets the state of lock-on of this unit.
     *
     * @param lockingOn the state to set
     */
    void setLockingOn(boolean lockingOn);

    /**
     * Gets the state of lock-on of this unit.
     *
     * @return the lock-on state
     */
    boolean isLockingOn();

    /**
     * The unit performs its unique set of actions, receiving the reference to the {@link Player} as argument.
     */
    void act(Player player);
}
