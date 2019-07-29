package exercises.holyMoly.destructable.enemy.sentry;

import exercises.holyMoly.Direction;
import exercises.holyMoly.destructable.enemy.AbstractEnemy;
import exercises.holyMoly.destructable.enemy.Enemy;
import exercises.holyMoly.destructable.enemy.EnemyBullet;
import exercises.holyMoly.game.Player;
import exercises.holyMoly.utils.AssetManager;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * The Sentry is the moving enemy unit of this game.
 * <p>
 * It has a pattern movement, unique to each sentry. This pattern is given at instantiation. It consists of a
 * collection of way-points and directions, which tell the unit where he is supposed to be moving into at all times.
 * <p>
 * The sentry is also capable of detecting if the {@link Player} is nearby and on-sight (not behind a
 * {@link exercises.holyMoly.game.Wall} or a {@link exercises.holyMoly.game.Crate}) and attempt to shoot at the
 * direction that is closest to the {@code player}.
 */
public class Sentry extends AbstractEnemy {

    private final int VELOCITY;
    private int slowSpeed;      // is 2 if unit is slowed; is 1 otherwise

    private Direction walkingDirection;
    private Direction facingDirection;
    private boolean lockingOnPlayer;

    private List<EnemyBullet> bullets;
    private boolean isOnCooldown;

    private SentryAI ai;

    /**
     * Constructor for this sentry. Takes in the arguments for starting position and initial index for the list of
     * behaviours, as well as the initial direction.
     *
     * @param positionX the position in the X axis
     * @param positionY the position in the Y axis
     * @param initialDirection the initial direction that this unit is facing
     * @param initialIndex the initial index for the list of behaviours
     * @see Enemy#setPosition(int, int)
     * @see Enemy#setBounds(int, int)
     * @see Sentry#updateImage()
     */
    public Sentry(int positionX, int positionY, Direction initialDirection, int initialIndex) {

        // TODO: the health needs to be set

        VELOCITY = 2;
        slowSpeed = 1;

        walkingDirection = initialDirection;
        facingDirection = initialDirection;

        int imageToColliderOffset = 15;

        setPosition(positionX + imageToColliderOffset, positionY + imageToColliderOffset);
        setBounds(imageToColliderOffset, imageToColliderOffset);

        updateImage();

        lockingOnPlayer = false;
        bullets = new LinkedList<>();
        isOnCooldown = false;

        ai = new SentryAI(initialIndex);
    }

    /**
     * Gets this unit's position in the X axis. Because the image is larger than it appears, the position has an offset
     * from the collider, which requires a small calculation.
     *
     * @return the x
     * @see Enemy#getX()
     */
    @Override
    public int getX() {
        return super.getX() - 15;
    }

    /**
     * Gets this unit's position in the Y axis. Because the image is larger than it appears, the position has an offset
     * from the collider, which requires a small calculation.
     *
     * @return the y
     * @see Enemy#getY()
     */
    @Override
    public int getY() {
        return super.getY() - 15;
    }

    /**
     * Sets the value of lock-on of this unit to the specified in the parameter.
     *
     * @param isOn the value to set
     */
    public void setLockingOnPlayer(boolean isOn) {
        lockingOnPlayer = isOn;
    }

    /**
     * Gets the list of {@link EnemyBullet}s of this unit.
     *
     * @return the list of bullets
     */
    public List<EnemyBullet> getBullets() {
        return bullets;
    }

    /**
     * Performs the optimal action for this unit at the moment.
     * <p>
     * If the unit is locking on the {@link Player}, it will attack. Otherwise, it will move.
     *
     * @param player the player
     * @see Sentry#attackThePlayer(Player)
     * @see Sentry#move()
     */
    @Override
    public void act(Player player) {

        if (lockingOnPlayer) {
            attackThePlayer(player);
        }

        else {
            move();
        }
    }

    /**
     * Performs the rotation to the {@link Player} and shoots a new {@link SentryBullet} to its
     * {@code facingDirection}.
     *
     * @param player the player
     * @see Sentry#rotateToPlayer(Player)
     * @see Sentry#shoot()
     */
    private void attackThePlayer(Player player) {

        rotateToPlayer(player);
        shoot();
    }

    /**
     * Makes the unit face the {@link Player}, by changing the {@code facingDirection} and updating the image.
     *
     * @param player the player to face
     * @see Sentry#getAngleToPlayer(Player)
     */
    private void rotateToPlayer(Player player) {

        double angle = getAngleToPlayer(player);

        if (angle >= 45 & angle < 135) {
            facingDirection = Direction.DOWN;
        }

        else if (angle >= 135 && angle < 225) {
            facingDirection = Direction.LEFT;
        }

        else if (angle >= 225 && angle < 315) {
            facingDirection = Direction.UP;
        }

        else {
            facingDirection = Direction.RIGHT;
        }

        updateImage();
    }

    /**
     * Calculates the direction that the {@link Player} is, compared to this unit and returns the value.
     *
     * @param player the player
     * @return the correct direction
     * @see Math#toDegrees(double)
     * @see Math#atan2(double, double)
     */
    private double getAngleToPlayer(Player player) {

        double angle = Math.toDegrees(Math.atan2(player.getY() - (getY() + 35), player.getX() - (getX() + 35)));

        if (angle < 0)
            angle += 360;

        else if (angle > 360)
            angle -= 360;

        return angle;
    }

    // TODO: later, check if this method can be private
    // TODO: make the superclass method not abstract

    /**
     * If the weapon is on cooldown, this method does nothing.
     * <p>
     * Otherwise, creates a new bullet on the unit's position and sets its direction to the unit's. Afterwards, the
     * weapon enters its cooldown state, rendering it unusable for 2 seconds, which returns to normal after the delay
     * has ended. If the unit is slowed at the moment of shooting, the delay is 4 seconds.
     *
     * @see Enemy#shoot()
     * @see java.util.Timer#schedule(TimerTask, Date)
     * @see TimerTask#run()
     */
    @Override
    public void shoot() {

        if (isOnCooldown) {
            return;
        }

        bullets.add(new SentryBullet(getX(), getY(), facingDirection));
        isOnCooldown = true;

        int delayInMilliseconds = 2000;

        new java.util.Timer().schedule(
                new TimerTask() {

                    @Override
                    public void run() { isOnCooldown = false; }
                }, delayInMilliseconds * slowSpeed);

    }

    /**
     * If the unit is targeting the player, this method does nothing.
     * <p>
     * Otherwise, informs the AI to verify the current behaviour and make any changes, if necessary. Afterwards,
     * updates the {@link Image} of the unit and performs the actual moving.
     *
     * @see SentryAI#verifyBehaviour()
     */
    private void move() {

        if (lockingOnPlayer) {
            return;
        }

        ai.verifyBehaviour();
        updateImage();

        int deltaX = (getX() + VELOCITY * walkingDirection.getPolarityX()) / slowSpeed;
        int deltaY = (getY() + VELOCITY * walkingDirection.getPolarityY()) / slowSpeed;
        getCollider().translate(deltaX, deltaY);
    }

    /**
     * Fetches the new {@link Image} to set from the {@link AssetManager}, while passing the necessary arguments of
     * {@code facingDirection} and {@code slowSpeed}, and assigns the image to the unit's property.
     *
     * @see Enemy#updateImage()
     * @see AssetManager#loadSentryImage(Direction, int)
     */
    @Override
    public void updateImage() {

        Image newImage = AssetManager.loadSentryImage(facingDirection, slowSpeed);
        setImage(newImage);
    }

    /**
     * Sets the speed of this unit to half for 6 seconds. Afterwards, the speed of the unit returns to normal after the
     * delay has ended.
     *
     * @see java.util.Timer#schedule(TimerTask, Date)
     * @see TimerTask#run()
     */
    public void setSlowDebuff() {

        slowSpeed = 2;
        int delayInMilliseconds = 6000;

        new java.util.Timer().schedule(
                new java.util.TimerTask() {

                    @Override
                    public void run() { slowSpeed = 1; }
                }, delayInMilliseconds);
    }



    /**
     * Contains all the methods for the {@link Sentry}'s AI behaviour.
     * <p>
     * This unit must know where to move at all times. The list of {@code behaviours} is actually a list of
     * {@link Direction}, which contains the pattern that the unit follows. There is also a list of {@link Point}
     * called {@code changingPoints}, which tells the unit when to change into the next direction of movement.
     */
    private class SentryAI {

        private List<Direction> behaviours;
        private List<Point> changingPoints;
        private int curBehaviourIndex;

        /**
         * Constructor of the Sentry. Takes in one parameter, which sets the {@code curBehaviourIndex}, as this unit
         * might not be starting at the exact beginning of its movement pattern.
         *
         * @param curBehaviourIndex the first index to start the unit's movement
         */
        private SentryAI(int curBehaviourIndex) {
            this.curBehaviourIndex = curBehaviourIndex;
        }

        /**
         * Add a new behaviour to the list of behaviours. The {@code direction} parameter will make the unit move in
         * that direction until it reaches the changing point. The {@code changingPointX} and {@code changingPointY}
         * will make the {@link Point} that represents the changing point for the each direction.
         * <p>
         * By passing a negative value in any of the integer parameters, it is implied that the coordinate is
         * irrelevant. At the verification method, this coordinate cannot be verified, as it would always
         * return {@code false}.
         *
         * @param direction the direction tha the unit must walk into
         * @param changingPointX the x of the changing point to this direction
         * @param changingPointY the y of the changing point to this direction
         */
        public void addBehaviour(Direction direction, int changingPointX, int changingPointY) {

            behaviours.add(direction);
            changingPoints.add(new Point(changingPointX, changingPointY));
        }

        /**
         * Updates the {@code facingDirection} and {@code walkingDirection} of this unit by fetching the current
         * {@link Direction} from the list of behaviours.
         */
        private void updateDirection() {

            facingDirection = behaviours.get(curBehaviourIndex);
            walkingDirection = behaviours.get(curBehaviourIndex);
        }

        /**
         * Verifies if this unit already contains the {@code changingPoint} to the current {@code behaviour}'s
         * direction, in which case {@code curBehaviourIndex} and {@code walkingDirection} are updated.
         * <p>
         * This verification takes into account the current {@code changingPoint}, which certainly has a negative value
         * in one of its properties. Only the value that is not negative must be verified. The other value is
         * replaced by the unit's current respective coordinate, so that the point will match the unit's position.
         *
         * @see SentryAI#updateDirection()
         */
        private void verifyBehaviour() {

            Point curChangingPoint = changingPoints.get(curBehaviourIndex);

            if ((walkingDirection.equals(Direction.UP) || walkingDirection.equals(Direction.DOWN))
                && getCollider().contains(getX(), curChangingPoint.y)) {

                ++curBehaviourIndex;

                if (curBehaviourIndex > behaviours.size() - 1) {
                    curBehaviourIndex = 0;
                }

                updateDirection();
                return;
            }

            if (walkingDirection.equals(Direction.LEFT) || walkingDirection.equals(Direction.RIGHT)
                && getCollider().contains(curChangingPoint.x, getY())) {

                ++curBehaviourIndex;

                if (curBehaviourIndex > behaviours.size() - 1) {
                    curBehaviourIndex = 0;
                }

                updateDirection();
            }
        }
    }
}