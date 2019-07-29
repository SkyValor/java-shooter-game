package exercises.holyMoly.destructable.enemy.sentry;

import exercises.holyMoly.Direction;
import exercises.holyMoly.destructable.enemy.AbstractEnemyBullet;
import exercises.holyMoly.destructable.enemy.EnemyType;

/**
 * The specification of an {@link exercises.holyMoly.destructable.enemy.EnemyBullet} for the {@link Sentry}.
 */
public class SentryBullet extends AbstractEnemyBullet {

    /**
     * Constructor for this bullet. Takes in the arguments for the initial position and the direction of movement.
     * Passes those values to the constructor of the superclass, as well as the correct {@code enemyType}.
     *
     * @param positionX the initial x
     * @param positionY the initial y
     * @param direction the direction of movement
     */
    SentryBullet(int positionX, int positionY, Direction direction) {
        super(direction, EnemyType.SENTRY);

        setPosition(positionX, positionY);
    }
}
