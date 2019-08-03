package exercises.holyMoly.entities;

import java.awt.*;

public abstract class AbstractEntity implements Entity {

    private EntityType entityType;
    private int health;
    private Image image;

    private Rectangle collider = new Rectangle();

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
    }   // TODO: erase setPosition() ; position in constructor

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
    public Rectangle getCollider() {
        return collider;
    }

    @Override
    public void setBounds(int width, int height) {

        collider.width = width;
        collider.height = height;
    }

    @Override
    public EntityType getEntityType() {
        return entityType;
    }

    @Override
    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
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
