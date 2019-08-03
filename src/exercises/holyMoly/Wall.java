package exercises.holyMoly;

import java.awt.*;

public class Wall {

    private Rectangle collider;

    public Wall(int positionX, int positionY, int width, int height) {
        collider = new Rectangle(positionX, positionY, width, height);
    }

    public Rectangle getCollider() {
        return collider;
    }
}
