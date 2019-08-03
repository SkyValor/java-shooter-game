package exercises.holyMoly.entities.player.bullets;

import exercises.holyMoly.Direction;
import exercises.holyMoly.entities.AbstractBullet;
import exercises.holyMoly.entities.BulletType;

abstract class AbstractPlayerBullet extends AbstractBullet {

    AbstractPlayerBullet(int positionX, int positionY, Direction direction, BulletType bulletType) {
        super(positionX, positionY, direction, bulletType);

        correctPosition(positionX, positionY, direction);
    }

    private void correctPosition(int positionX, int positionY, Direction direction) {

        int correctedPositionX = 0;
        int correctedPositionY = 0;

        switch (direction) {

            case UP:
                correctedPositionX = positionX + 41;
                correctedPositionY = positionY;
                break;

            case RIGHT:
                correctedPositionX = positionX + 70;
                correctedPositionY = positionY + 41;
                break;

            case DOWN:
                correctedPositionX = positionX + 28;
                correctedPositionY = positionY + 70;
                break;

            case LEFT:
                correctedPositionX = positionX;
                correctedPositionY = positionY + 28;
        }

        getCollider().x = correctedPositionX;
        getCollider().y = correctedPositionY;
    }
}
