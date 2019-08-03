package exercises.holyMoly.entities.destructable.enemy.sentry;

import exercises.holyMoly.Game;
import exercises.holyMoly.Direction;
import exercises.holyMoly.entities.Bullet;
import exercises.holyMoly.entities.EntityType;
import exercises.holyMoly.entities.destructable.enemy.AbstractEnemy;
import exercises.holyMoly.entities.destructable.enemy.Enemy;
import exercises.holyMoly.entities.player.Player;
import exercises.holyMoly.Wall;
import exercises.holyMoly.entities.destructable.Crate;
import exercises.holyMoly.utils.managers.AssetManager;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * The Sentry is the moving enemy unit of this {@link Game}.
 * <br>
 * It has a pattern movement, unique to each sentry. This pattern is given at instantiation. It consists of a
 * collection of way-points and directions, which tell the unit where it is supposed to be moving into at all times.
 * <br>
 * The sentry is also capable of locking-on to the {@link Player}, if it's nearby and on-sight (not behind a
 * {@link Wall} or a {@link Crate}) and shoot at the {@link Direction} that is closest to the {@code player}.
 * <br>
 * Its inner class {@link SentryAI} does the calculations and management of the moving patterns.
 */
public class Sentry extends AbstractEnemy {

    private Direction walkingDirection;
    private Direction facingDirection;

    private List<Bullet> bullets;
    private boolean isOnCooldown;

    private SentryAI ai;

    /**
     * Constructor for this sentry. Takes in the arguments for starting {@code position} and initial {@code index} for
     * the list of behaviours, as well as the initial {@link Direction}.
     *
     * @param positionX the initial x
     * @param positionY the initial y
     * @param initialDirection the initial direction that this unit is facing
     * @param initialIndex the initial index for the list of behaviours
     * @see Enemy#setPosition(int, int)
     * @see Enemy#setBounds(int, int)
     * @see Sentry#updateImage()
     */
    public Sentry(int positionX, int positionY, Direction initialDirection, int initialIndex) {

        setEntityType(EntityType.SENTRY);
        setInitialHealth(100);

        setVelocity(2);
        setSlowSpeed(1);

        walkingDirection = initialDirection;
        facingDirection = initialDirection;

        int imageToColliderOffset = 15;
        int colliderBounds = 70 - (2 * imageToColliderOffset);

        setPosition(positionX + imageToColliderOffset, positionY + imageToColliderOffset);
        setBounds(colliderBounds, colliderBounds);

        updateImage();

        setLockingOn(false);
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
     * Gets the list of {@link Bullet}s of this unit.
     *
     * @return the list of bullets
     */
    public List<Bullet> getBullets() {
        return bullets;
    }

    /**
     * Performs the optimal action for this unit at the moment.
     * <br>
     * If the unit is locking on the {@link Player}, it will attack. Otherwise, it will move.
     *
     * @param player the player
     * @see Sentry#attackThePlayer(Player)
     * @see Sentry#move()
     */
    @Override
    public void act(Player player) {

        if (isLockingOn()) {
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
     * <br>
     * Otherwise, creates a new {@link Bullet} on the unit's {@code position} and sets its {@link Direction} to the
     * unit's. Afterwards, the weapon enters its cooldown state, rendering it unusable for 2 seconds, which returns to
     * normal after the {@code delay} has ended. If the unit is {@code slowed} at the moment of shooting, the
     * {@code delay} is 4 seconds.
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
                }, delayInMilliseconds * getSlowSpeed());

    }

    /**
     * If the unit is targeting the player, this method does nothing.
     * <br>
     * Otherwise, informs the AI to verify the current behaviour and make any changes, if necessary. Afterwards,
     * updates the {@link Image} of the unit and performs the actual moving.
     *
     * @see SentryAI#verifyBehaviour()
     */
    private void move() {

        if (isLockingOn()) {
            return;
        }

        ai.verifyBehaviour();
        updateImage();

        int deltaX = (getX() + getVelocity() * walkingDirection.getPolarityX()) / getSlowSpeed();
        int deltaY = (getY() + getVelocity() * walkingDirection.getPolarityY()) / getSlowSpeed();
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

        Image newImage = AssetManager.loadSentryImage(facingDirection, getSlowSpeed());
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

        setSlowSpeed(2);
        int delayInMilliseconds = 6000;

        new java.util.Timer().schedule(
                new java.util.TimerTask() {

                    @Override
                    public void run() { setSlowSpeed(1); }
                }, delayInMilliseconds);
    }

    /**
     * Gets the {@link SentryAI} of this unit.
     *
     * @return the AI
     */
    public SentryAI getAi() {
        return ai;
    }



    /**
     * Contains all the methods for the {@link Sentry}'s AI behaviour.
     * <br>
     * This unit must know where to move at all times. The list of {@code behaviours} is actually a list of
     * {@link Direction}, which contains the pattern that the unit follows. There is also a list of {@link Point}
     * called {@code changingPoints}, which tells the unit when to change into the next direction of movement.
     */
    public class SentryAI {

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
         * <br>
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
         * <br>
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