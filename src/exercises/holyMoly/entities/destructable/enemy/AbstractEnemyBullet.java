package exercises.holyMoly.entities.destructable.enemy;

import exercises.holyMoly.Direction;
import exercises.holyMoly.entities.AbstractBullet;

/**
 * Abstract class that holds the date and methods which are common to all enemy bullets.
 */
public class AbstractEnemyBullet extends AbstractBullet {

    private EnemyType enemyType;

    /**
     * Constructor for this bullet. Takes in the arguments for the direction of movement and the enemy type that
     * created the bullet.
     *
     * @param direction the direction of movement
     * @param enemyType the type of enemy that is shooting the bullet
     */
    public AbstractEnemyBullet(int positionX, int positionY, Direction direction, EnemyType enemyType) {
        super(positionX, positionY, direction, enemyType.getBulletType());

        this.enemyType = enemyType;

        switch (enemyType) {

            case SENTRY:

                int bulletLength = 34;
                int bulletWidth = 16;

                if (direction.equals(Direction.UP) || direction.equals(Direction.DOWN)) {
                    setBounds(bulletWidth, bulletLength);
                }

                if (direction.equals(Direction.LEFT) || direction.equals(Direction.RIGHT)) {
                    setBounds(bulletLength, bulletWidth);
                }
                break;

            case TURRET:

                int bulletBounds = 20;
                setBounds(bulletBounds, bulletBounds);
                break;
        }
    }
}
