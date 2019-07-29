package exercises.holyMoly.destructable.enemy;

import exercises.holyMoly.Direction;

import java.awt.*;

/**
 * Interface which contains the essential methods for each of bullets shot by enemies in this game.
 */
public interface EnemyBullet {

    /**
     * Gets the x of this bullet
     *
     * @return the x
     */
    int getX();

    /**
     * Gets the y of this bullet
     *
     * @return the y
     */
    int getY();

    /**
     * Gets the image of this bullet
     *
     * @return the image
     */
    Image getImage();

    /**
     * Sets the image for this bullet, based on the direction of movement and the type of enemy that shoots the bullet.
     *
     * @param direction the direction of movement
     * @param enemyType the type of enemy
     */
    void setImage(Direction direction, EnemyType enemyType);

    /**
     * Gets the collider of this bullet
     *
     * @return the collider
     */
    Rectangle getCollider();

    /**
     * Gets the amount of damage that this bullet inflicts.
     *
     * @return the amount of damage
     */
    int getDamage();

    /**
     * Translates the position of this bullet to its {@code direction}.
     */
    void move();
}
