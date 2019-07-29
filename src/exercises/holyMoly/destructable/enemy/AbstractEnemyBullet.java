package exercises.holyMoly.destructable.enemy;

import exercises.holyMoly.Direction;
import exercises.holyMoly.utils.AssetManager;

import java.awt.*;

/**
 * Abstract class which integrates all the necessary data and methods that are common for the derived classes.
 */
public class AbstractEnemyBullet implements EnemyBullet {

    private final int VELOCITY = 5;
    private EnemyType enemyType;

    private Direction direction;

    private Image image;
    private Rectangle collider;

    /**
     * Constructor for this bullet. Takes in the arguments for the direction of movement and the enemy type that
     * created the bullet, for correct bound settings.
     *
     * @param direction the direction of movement
     * @param enemyType the type of enemy that is shooting the bullet
     * @see AbstractEnemyBullet#setImage(Direction, EnemyType)
     */
    protected AbstractEnemyBullet(Direction direction, EnemyType enemyType) {

        this.enemyType = enemyType;
        this.direction = direction;
        collider = new Rectangle();

        switch (enemyType) {

            case SENTRY:

                int bulletLength = 34;
                int bulletWidth = 16;

                if (direction.equals(Direction.UP) || direction.equals(Direction.DOWN)) {
                    collider.width = bulletWidth;
                    collider.height = bulletLength;
                }

                if (direction.equals(Direction.LEFT) || direction.equals(Direction.RIGHT)) {
                    collider.width = bulletLength;
                    collider.height = bulletWidth;
                }
                break;

            case TURRET:

                int bulletBounds = 20;
                collider.width = bulletBounds;
                collider.height = bulletBounds;
                break;
        }

        setImage(direction, enemyType);
    }

    /**
     * Sets the initial position of this bullet.
     *
     * @param positionX the x to set
     * @param positionY the y to set
     */
    public void setPosition(int positionX, int positionY) {

        collider.x = positionX;
        collider.y = positionY;
    }

    /**
     * @see EnemyBullet#getX()
     */
    @Override
    public int getX() {
        return collider.x;
    }

    /**
     * @see EnemyBullet#getY()
     */
    @Override
    public int getY() {
        return collider.y;
    }

    /**
     * @see EnemyBullet#getImage()
     */
    @Override
    public Image getImage() {
        return image;
    }

    /**
     * Sets the image of this bullet by fetching it from the {@link AssetManager}, passing it the {@code direction} and
     * {@code enemyType}.
     */
    @Override
    public void setImage(Direction direction, EnemyType enemyType) {

        switch (enemyType) {

            case SENTRY:
                image = AssetManager.loadSentryBulletImage(direction);
                break;

            case TURRET:
                image = AssetManager.loadTurretBulletImage();
        }
    }

    /**
     * @see EnemyBullet#getCollider()
     */
    @Override
    public Rectangle getCollider() {
        return collider;
    }

    /**
     * @see EnemyBullet#getDamage()
     */
    @Override
    public int getDamage() {
        return enemyType.getBulletDamage();
    }

    /**
     * @see EnemyBullet#move()
     */
    @Override
    public void move() {

        switch (direction) {

            case UP:
                collider.translate(0, -VELOCITY);
                break;

            case RIGHT:
                collider.translate(VELOCITY, 0);
                break;

            case DOWN:
                collider.translate(0, VELOCITY);
                break;

            case LEFT:
                this.collider.translate(-VELOCITY, 0);
        }
    }
}
