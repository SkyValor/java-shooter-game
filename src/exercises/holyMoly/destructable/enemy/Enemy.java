package exercises.holyMoly.destructable.enemy;

import exercises.holyMoly.game.Player;

import java.awt.*;

public interface Enemy {

    /**
     * Gets the x of this unit.
     *
     * @return the x
     */
    int getX();

    /**
     * Gets the y of this unit.
     *
     * @return the y
     */
    int getY();

    /**
     * Sets the position of this unit.
     *
     * @param positionX the x to set
     * @param positionY the y to set
     */
    void setPosition(int positionX, int positionY);

    /**
     * Gets the current health of this unit.
     *
     * @return the health
     */
    int getHealth();

    /**
     * Sets the initial health of this unit.
     *
     * @param initialHealth the initial health
     */
    void setInitialHealth(int initialHealth);

    /**
     * Gets the current image of this unit.
     *
     * @return the image
     */
    Image getImage();

    /**
     * Sets the current image of this unit.
     *
     * @param image the image to set
     */
    void setImage(Image image);

    /**
     * Updates the image of this unit, based on factors such as {@code facingDirection}, {@code walkingDirection} and
     * {@code slowSpeed}.
     */
    void updateImage();

    /**
     * Gets the collider of this unit.
     *
     * @return the collider
     */
    Rectangle getCollider();

    /**
     * Sets the bounds of this unit's collider.
     *
     * @param width the width to set
     * @param height the height to set
     */
    void setBounds(int width, int height);

    /**
     * The unit performs its unique set of actions, receiving the reference to the {@link Player} as argument.
     */
    void act(Player player);

    /**
     * Instantiates an {@link AbstractEnemyBullet} on a given position and sets the bullet's direction.
     */
    void shoot();

    /**
     * Subtracts the unit's health by the received {@code bulletDamage} amount.
     *
     * @param damage the amount to subtract
     */
    void takeDamage(int damage);
}
