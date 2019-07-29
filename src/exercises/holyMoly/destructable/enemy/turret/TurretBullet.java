package exercises.holyMoly.destructable.enemy.turret;

import exercises.holyMoly.Direction;
import exercises.holyMoly.destructable.enemy.AbstractEnemyBullet;
import exercises.holyMoly.destructable.enemy.EnemyType;

import java.awt.*;

/**
 * The specification of an {@link exercises.holyMoly.destructable.enemy.EnemyBullet} for the {@link Turret}.
 * <br>
 */
public class TurretBullet extends AbstractEnemyBullet {

    /**
     * Constructor for this bullet. Takes in the arguments for the initial position, the direction of shooting, the
     * type of turret and the number of this bullet.
     * <br>
     * The {@code positionX} and {@code positionY} correspond to the {@link Turret}'s position. These values are
     * necessary, to use as parameters on the calculations for this bullet's actual initial position. Because the
     * turret shoots more than one bullet at one time, the {@code number} represents the numerical position of this
     * bullet to be created.
     * <br>
     * The {@code turretType} corresponds to the type of turret that is shooting this bullet. This detail is also
     * important, so that we can assess how many bullets are to be shot and where. The {@code direction} indicates
     * the direction of movement for this bullet.
     *
     * @param positionX the turret's x
     * @param positionY the turret's y
     * @param direction the direction of movement
     * @param turretType the type of turret
     * @param number the numerical order of this bullet
     * @see TurretBullet#calculateInitialPosition(int, int, Direction, TurretType, int)
     */
    TurretBullet(int positionX, int positionY, Direction direction, TurretType turretType, int number) {
        super(direction, EnemyType.TURRET);

        Point initialPosition = calculateInitialPosition(positionX, positionY, direction, turretType, number);

        setPosition(initialPosition.x, initialPosition.y);
    }

    /**
     * Calculates and returns the initial position for this bullet. Takes in the same arguments as the constructor
     * method, to perform the necessary verifications and calculations to determine the correct position for
     * these factors.
     *
     * @param positionX the turret's x
     * @param positionY the turret's y
     * @param direction the direction of shooting
     * @param turretType the type of turret
     * @param number the numerical order of this bullet
     * @return the point of initial position
     */
    private Point calculateInitialPosition(int positionX, int positionY,
                                           Direction direction, TurretType turretType, int number) {

        Point p = new Point();

        if (turretType.equals(TurretType.VERTICAL)) {

            switch (direction) {

                case UP:

                    if (number == 1) {
                        p.x = positionX;
                        p.y = positionY - 5;
                        break;
                    }

                    else {
                        p.x = positionX + 25;
                        p.y = positionY - 5;
                        break;
                    }

                case RIGHT:

                    if (number == 1) {
                        p.x = positionX + getCollider().width + 5;
                        p.y = positionY - 5;
                        break;
                    }

                    else if (number == 2) {
                        p.x = positionX + getCollider().width + 5;
                        p.y = positionY + 19;
                        break;
                    }

                    else {
                        p.x = positionX + getCollider().width + 5;
                        p.y = positionY + 42;
                        break;
                    }


                case DOWN:

                    if (number == 1) {
                        p.x = positionX;
                        p.y = positionY + getCollider().height + 5;
                        break;
                    }

                    else {
                        p.x = positionX + 25;
                        p.y = positionY + getCollider().height + 5;
                        break;
                    }


                case LEFT:

                    if (number == 1) {
                        p.x = positionX - 5;
                        p.y = positionY - 5;
                        break;
                    }

                    else if (number == 2) {
                        p.x = positionX - 5;
                        p.y = positionY + 19;
                        break;
                    }

                    else {
                        p.x = positionX - 5;
                        p.y = positionY + 42;
                        break;
                    }
            }
        }

        if (turretType.equals(TurretType.HORIZONTAL)) {

            switch (direction) {

                case UP:

                    if (number == 1) {
                        p.x = positionX - 5;
                        p.y = positionY - 5;
                        break;
                    }

                    else if (number == 2) {
                        p.x = positionX + 19;
                        p.y = positionY - 5;
                        break;
                    }

                    else {
                        p.x = positionX + 42;
                        p.y = positionY - 5;
                        break;
                    }

                case RIGHT:

                    if (number == 1) {
                        p.x = positionX + getCollider().width + 5;
                        p.y = positionY;
                        break;
                    }

                    else {
                        p.x = positionX + getCollider().width + 5;
                        p.y = positionY + 25;
                        break;
                    }

                case DOWN:

                    if (number == 1) {
                        p.x = positionX - 5;
                        p.y = positionY + getCollider().height + 5;
                        break;
                    }

                    else if (number == 2) {
                        p.x = positionX + 19;
                        p.y = positionY + getCollider().height + 5;
                        break;
                    }

                    else {
                        p.x = positionX + 42;
                        p.y = positionY + getCollider().height + 5;
                        break;
                    }

                case LEFT:

                    if (number == 1) {
                        p.x = positionX - 5;
                        p.y = positionY;
                        break;
                    }

                    else {
                        p.x = positionX - 5;
                        p.y = positionY + 25;
                        break;
                    }
            }
        }

        return p;
    }
}
