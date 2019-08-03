package exercises.holyMoly;

import exercises.holyMoly.utils.managers.AssetManager;

import java.awt.*;

public class Pickable {

    private PickableType pickableType;
    private Rectangle collider;
    private Image image;

    public Pickable(int positionX, int positionY, PickableType pickableType) {

        this.pickableType = pickableType;

        int colliderBounds = 37;
        collider = new Rectangle(positionX, positionY, colliderBounds, colliderBounds);

        image = AssetManager.loadPickableImage(pickableType);
    }

    int getX() {
        return collider.x;
    }

    int getY() {
        return collider.y;
    }

    Image getImage() {
        return image;
    }

    PickableType getPickableType() {
        return pickableType;
    }
}

