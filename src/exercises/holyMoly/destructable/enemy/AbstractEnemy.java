package exercises.holyMoly.destructable.enemy;

import exercises.holyMoly.Direction;
import exercises.holyMoly.game.Player;

import java.awt.*;

public abstract class AbstractEnemy implements Enemy {

    private int health;
    private Rectangle collider = new Rectangle();
    private Image image;

    @Override
    public int getX() {
        return collider.x;
    }

    @Override
    public int getY() {
        return collider.y;
    }

    @Override
    public void setPosition(int positionX, int positionY) {

        collider.x = positionX;
        collider.y = positionY;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setInitialHealth(int initialHealth) {
        health = initialHealth;
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public abstract void updateImage();

    @Override
    public abstract void act(Player player);

    @Override
    public Rectangle getCollider() {
        return collider;
    }

    @Override
    public void setBounds(int width, int height) {

        collider.width = width;
        collider.height = height;
    }

    @Override
    public abstract void shoot();

    @Override
    public void takeDamage(int damage) {

        health -= damage;

        if (health < 0) {
            health = 0;
        }
    }
}
