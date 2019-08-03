package exercises.holyMoly;

import exercises.holyMoly.utils.managers.AssetManager;

import java.awt.*;

public class Spikes {

    private boolean visible;
    private Rectangle collider;
    private Image image;

    public Spikes(int positionX, int positionY) {

        visible = false;

        int colliderBounds = 37;
        collider = new Rectangle(positionX, positionY, colliderBounds, colliderBounds);

        image = AssetManager.loadSpikesImage();
    }

    boolean isVisible() {
        return visible;
    }

    void turnVisible() {
        visible = true;
    }

    int getX() {
        return collider.x;
    }

    int getY() {
        return collider.y;
    }

    Rectangle getCollider() {
        return collider;
    }

    Image getImage() {
        return image;
    }
}
