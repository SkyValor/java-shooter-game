package exercises.holyMoly.entities.destructable.enemy.turret;

import exercises.holyMoly.Direction;
import exercises.holyMoly.entities.Alignment;
import exercises.holyMoly.entities.destructable.enemy.EnemyBullet;
import exercises.holyMoly.entities.destructable.enemy.AbstractEnemyBullet;
import exercises.holyMoly.entities.destructable.enemy.EnemyType;
import exercises.holyMoly.entities.Bullet;

import java.awt.*;

/**
 * The specification of an {@link EnemyBullet} for the {@link Turret}.
 * <br>
 * This bullet's position is affected not only by its initial position, but also by its {@code order number}.
 * This number indicates which bullet is being fired in this row of bullets.
 */
public class TurretBullet extends AbstractEnemyBullet {

    /**
     * Constructor for this bullet. Takes in the arguments for the initial position, the {@link Direction} of shooting,
     * the {@link Alignment} and the order of this bullet.
     * <br>
     * The {@code positionX} and {@code positionY} correspond to the {@link Turret}'s position. These values are
     * necessary, to use as parameters on the calculations for this bullet's actual initial position. Because the
     * turret shoots more than one bullet at one time, the {@code number} represents the numerical position of this
     * bullet to be created.
     * <br>
     * The {@link Alignment} corresponds to the type of {@code turret} that is shooting this bullet. This detail is also
     * important, so that we can assess how many bullets are to be shot and where they are starting from.
     * The {@code direction} indicates the direction of movement for this bullet.
     *
     * @param positionX the initial x
     * @param positionY the initial y
     * @param direction the direction of movement
     * @param alignment the alignment of this turret
     * @param number the numerical order of this bullet
     * @see TurretBullet#calculateInitialPosition(int, int, Direction, Alignment, int)
     */
    TurretBullet(int positionX, int positionY, Direction direction, Alignment alignment, int number) {
        super(positionX, positionY, direction, EnemyType.TURRET);

        Point correctedPosition = calculateInitialPosition(positionX, positionY, direction, alignment, number);

        setCorrectedPosition(correctedPosition);
    }

    /**
     * Calculates and returns the corrected initial position for this {@link Bullet}. Takes in the same arguments as
     * the constructor method, to perform the necessary verifications and calculations to determine the correct
     * position for these factors.
     *
     * @param positionX the initial x
     * @param positionY the initial y
     * @param direction the direction of shooting
     * @param alignment the alignment of this turret
     * @param number the numerical order of this bullet
     * @return the point of initial position
     */
    private Point calculateInitialPosition(int positionX, int positionY,
                                           Direction direction, Alignment alignment, int number) {

        Point p = new Point();

        if (alignment.equals(Alignment.VERTICAL)) {

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

        if (alignment.equals(Alignment.HORIZONTAL)) {

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

    /**
     * Sets the position of this bullet to be the new corrected position from the calculations.
     *
     * @param correctedPosition the position to set
     */
    private void setCorrectedPosition(Point correctedPosition) {

        getCollider().x = correctedPosition.x;
        getCollider().y = correctedPosition.y;
    }
}
