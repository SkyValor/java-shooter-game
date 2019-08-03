package exercises.holyMoly.entities;

import exercises.holyMoly.Direction;
import exercises.holyMoly.utils.managers.AssetManager;

import java.awt.*;

/**
 * The superclass for all {@link Bullet}s in this game. It's an abstract class that holds the data and methods which
 * are common to all bullets.
 */
public abstract class AbstractBullet implements Bullet {

    private BulletType bulletType;
    private Direction direction;
    private Alignment alignment;

    private Image image;
    private Rectangle collider;

    /**
     * Constructor method. Takes in the arguments for the {@link Direction} and {@link BulletType} of this bullet.
     *
     * @param direction the direction of this bullet
     * @param bulletType the type of this bullet
     */
    public AbstractBullet(int positionX, int positionY, Direction direction, BulletType bulletType) {

        this.direction = direction;
        this.bulletType = bulletType;

        collider = new Rectangle();
        collider.x = positionX;
        collider.y = positionY;
    }

    protected void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    /**
     * @see Bullet#getX()
     */
    @Override
    public int getX() {
        return collider.x;
    }

    /**
     * @see Bullet#getY()
     */
    @Override
    public int getY() {
        return collider.y;
    }

    /**
     * @see Bullet#setBounds(int, int)
     */
    @Override
    public void setBounds(int width, int height) {

        collider.width = width;
        collider.height = height;
    }

    /**
     * @see Bullet#getImage()
     */
    @Override
    public Image getImage() {
        return image;
    }

    /**
     * @see Bullet#setImage()
     */
    @Override
    public void setImage() {

        switch (bulletType) {

            case MACHINEGUN:
                image = AssetManager.loadPlayerMachineGunBulletImage();
                break;

            case MEGAPHONE:
                image = AssetManager.loadPlayerMegaphoneBulletImage(alignment);
                break;

            case BAZOOKA:
                image = AssetManager.loadPlayerBazookaBulletImage(direction);
                break;

            case SENTRY:
                image = AssetManager.loadSentryBulletImage(alignment);
                break;

            case TURRET:
                image = AssetManager.loadTurretBulletImage();
        }
    }

    /**
     * @see Bullet#setImage(Image)
     */
    @Override
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * @see Bullet#getCollider()
     */
    @Override
    public Rectangle getCollider() {
        return collider;
    }

    /**
     * @see Bullet#getDamage()
     */
    @Override
    public int getDamage() {
        return bulletType.getDamage();
    }

    /**
     * @see Bullet#move()
     */
    @Override
    public void move() {

        int velocity = bulletType.getVelocity();

        switch (direction) {

            case UP:
                collider.translate(0, -velocity);
                break;

            case RIGHT:
                collider.translate(velocity, 0);
                break;

            case DOWN:
                collider.translate(0, velocity);
                break;

            case LEFT:
                this.collider.translate(-velocity, 0);
        }
    }
}