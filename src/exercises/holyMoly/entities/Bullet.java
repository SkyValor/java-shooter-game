package exercises.holyMoly.entities;

import exercises.holyMoly.Game;
import exercises.holyMoly.utils.managers.AssetManager;

import java.awt.*;

/**
 * Interface containing the common behaviour for all bullets in this {@link Game}.
 */
public interface Bullet {

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
     * Sets the bounds of this bullet.
     *
     * @param width the width
     * @param height the height
     */
    void setBounds(int width, int height);

    /**
     * Gets the {@link Image} of this bullet
     *
     * @return the image
     */
    Image getImage();

    /**
     * Sets the {@link Image} of this bullet. The image is fetched through the {@link AssetManager}, passing the
     * required properties are arguments.
     */
    void setImage();

    /**
     * Sets the {@link Image} of this bullet to be the {@code image} received as argument.
     *
     * @param image the image to set
     */
    void setImage(Image image);

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
