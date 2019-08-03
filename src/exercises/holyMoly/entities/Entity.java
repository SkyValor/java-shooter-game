package exercises.holyMoly.entities;

import exercises.holyMoly.entities.destructable.enemy.EnemyType;

import java.awt.*;

public interface Entity {

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
     * Sets the current image of this unit to the one received as argument.
     *
     * @param image the image to set
     */
    void setImage(Image image);

    /**
     * Updates the image of this unit, based on factors of the respective derived class.
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
     * Gets the type of this entity.
     *
     * @return the type
     */
    EntityType getEntityType();

    /**
     * Sets the type of this entity.
     *
     * @param entityType the type to set
     */
    void setEntityType(EntityType entityType);

    /**
     * Instantiates a {@link Bullet}.
     */
    void shoot();

    /**
     * Subtracts the unit's health by the received {@code damage} amount.
     *
     * @param damage the amount to subtract
     */
    void takeDamage(int damage);
}
