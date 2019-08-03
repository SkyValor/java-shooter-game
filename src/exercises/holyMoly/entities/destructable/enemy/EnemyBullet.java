package exercises.holyMoly.entities.destructable.enemy;

import exercises.holyMoly.Game;
import exercises.holyMoly.entities.Bullet;

/**
 * Interface containing the common behaviour for all enemy {@link Bullet}s in this {@link Game}.
 */
public interface EnemyBullet extends Bullet {

    /**
     * Sets the {@link EnemyType} of this bullet.
     *
     * @param enemyType the enemy type to set
     */
    void setEnemyType(EnemyType enemyType);
}
