package exercises.holyMoly.entities.destructable.enemy.sentry;

import exercises.holyMoly.Direction;
import exercises.holyMoly.entities.destructable.enemy.AbstractEnemyBullet;
import exercises.holyMoly.entities.destructable.enemy.EnemyBullet;
import exercises.holyMoly.entities.destructable.enemy.EnemyType;

/**
 * The specification of an {@link EnemyBullet} for the {@link Sentry}.
 */
public class SentryBullet extends AbstractEnemyBullet {

    /**
     * Constructor for this bullet. Takes in the arguments for the initial position and the {@link Direction} of movement.
     * Passes those values to the constructor of the superclass, as well as the correct {@code enemyType}.
     *
     * @param positionX the initial x
     * @param positionY the initial y
     * @param direction the direction of movement
     */
    SentryBullet(int positionX, int positionY, Direction direction) {
        super(positionX, positionY, direction, EnemyType.SENTRY);
    }
}
