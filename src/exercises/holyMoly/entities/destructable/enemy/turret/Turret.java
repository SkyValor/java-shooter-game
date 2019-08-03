package exercises.holyMoly.entities.destructable.enemy.turret;

import exercises.holyMoly.Game;
import exercises.holyMoly.Direction;
import exercises.holyMoly.entities.Alignment;
import exercises.holyMoly.entities.Bullet;
import exercises.holyMoly.entities.destructable.enemy.AbstractEnemy;
import exercises.holyMoly.entities.destructable.enemy.Enemy;
import exercises.holyMoly.entities.destructable.Crate;
import exercises.holyMoly.entities.player.Player;
import exercises.holyMoly.Wall;
import exercises.holyMoly.utils.managers.AssetManager;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * The Turret is the static enemy unit of this {@link Game}, which means that this unit never moves aways from its place.
 * <br>
 * The turret is also capable of locking-on to the {@link Player}, if it's nearby and on-sight (not behind a
 * {@link Wall} or a {@link Crate}) and attempt to shoot at the {@link Direction} that is closest to the {@code player}. 
 * This unit's bullets will not be destroyed on collision with {@code walls}.
 * <br>
 * Another particularity of this unit is that, instead of shooting one bullet each time, it shoots two or three
 * bullets at once, depending on the {@link Alignment} and {@code shootingDirection}.
 */
public class Turret extends AbstractEnemy {

    private Alignment alignment;
    private Direction shootingDirection;

    private List<Bullet> bullets;
    private boolean isOnCooldown;

    /**
     * Constructor for this turret. Takes in the arguments for starting position and the alignment.
     *
     * @param positionX the x
     * @param positionY the y
     * @param alignment the alignment
     * @see Enemy#setPosition(int, int)
     * @see Enemy#setBounds(int, int)
     * @see Turret#updateImage()
     */
    public Turret(int positionX, int positionY, Alignment alignment) {

        this.alignment = alignment;
        setInitialHealth(200);

        setSlowSpeed(1);

        setPosition(positionX, positionY);

        int turretLength = 57;
        int turretWidth = 45;

        if (alignment.equals(Alignment.VERTICAL)) {
            setBounds(turretWidth, turretLength);
        }

        if (alignment.equals(Alignment.HORIZONTAL)) {
            setBounds(turretLength, turretWidth);
        }

        bullets = new LinkedList<>();
        isOnCooldown = false;
        setLockingOn(false);
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
     * Gets the direction that this unit is shooting at.
     *
     * @return the direction
     */
    private Direction getShootingDirection() {
        return shootingDirection;
    }

    /**
     * Performs the optimal action for this unit at the moment.
     * <br>
     * If the unit is locking on the {@link Player}, it will attack.
     *
     * @param player the player
     * @see Turret#attackThePlayer(Player)
     */
    @Override
    public void act(Player player) {

        if (isLockingOn()) {
            attackThePlayer(player);
        }
    }

    /**
     * Performs the rotation to the {@link Player} and shoots a new {@link TurretBullet} to its
     * {@code shootingDirection}.
     *
     * @param player the player
     * @see Turret#updateShootingDirection(Player)
     * @see Turret#shoot()
     */
    private void attackThePlayer(Player player) {

        updateShootingDirection(player);
        shoot();
    }

    /**
     * Updates the {@code shootingDirection} of this unit so that it shoots the closest to the {@link Player}.
     *
     * @param player the player to face
     * @see Turret#getAngleToPlayer(Player)
     */
    private void updateShootingDirection(Player player) {

        double angle = getAngleToPlayer(player);

        if (angle >= 45 & angle < 135) {
            shootingDirection = Direction.DOWN;
        }

        else if (angle >= 135 && angle < 225) {
            shootingDirection = Direction.LEFT;
        }

        else if (angle >= 225 && angle < 315) {
            shootingDirection = Direction.UP;
        }

        else {
            shootingDirection = Direction.RIGHT;
        }
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

        double angle = Math.toDegrees(Math.atan2( player.getY() - (getY() + 35), player.getX() - (getX() + 35)));

        if (angle < 0)
            angle += 360;

        else if (angle > 360)
            angle -= 360;

        return angle;
    }

    /**
     * If the weapon is on cooldown, this method does nothing.
     * <br>
     * Otherwise, the unit shoots a row of {@link TurretBullet}s on the direction of {@code shootingDirection}. The
     * exact starting position of the bullets will depend on the {@code alignment} and the {@code shootingDirection}.
     * Some adjustments are necessary.
     * <br>
     * Afterwards, the weapon enters its cooldown state, rendering it unusable for 2 seconds, which returns to normal
     * after the delay has ended. If the unit is slowed at the moment of shooting, the delay is 4 seconds.
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

        if (alignment.equals(Alignment.VERTICAL)) {

            switch (shootingDirection) {

                case UP:    // shoots 2 bullets

                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, alignment, 1));
                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, alignment, 2));
                    break;

                case RIGHT: // shoots 3 bullets

                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, alignment, 1));
                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, alignment, 2));
                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, alignment, 3));
                    break;

                case DOWN:  // shoots 2 bullets

                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, alignment, 1));
                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, alignment, 2));
                    break;

                case LEFT:  // shoots 3 bullets
                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, alignment, 1));
                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, alignment, 2));
                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, alignment, 3));
            }
        }

        if (alignment.equals(Alignment.HORIZONTAL)) {

            switch (shootingDirection) {

                case UP:  // shoots 3 bullets

                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, alignment, 1));
                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, alignment, 2));
                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, alignment, 3));

                    break;

                case RIGHT:   // shoots 2 bullets

                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, alignment, 1));
                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, alignment, 2));
                    break;

                case DOWN:    // shoots 3 bullets

                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, alignment, 1));
                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, alignment, 2));
                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, alignment, 3));

                    break;

                case LEFT:    // shoots 2 bullets
                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, alignment, 1));
                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, alignment, 2));
            }
        }

        isOnCooldown = true;

        int delayInMilliseconds = 2000;

        new java.util.Timer().schedule(
                new TimerTask() {

                    @Override
                    public void run() {
                        isOnCooldown = false;}
                }, delayInMilliseconds * getSlowSpeed());

    }

    /**
     * Fetches the new {@link Image} to set from the {@link AssetManager}, while passing the necessary arguments of
     * {@code shootingDirection} and {@code slowSpeed}, and assigns the image to the unit's property.
     *
     * @see Enemy#updateImage()
     * @see AssetManager#loadTurretImage(Alignment, int)
     */
    @Override
    public void updateImage() {

        Image newImage = AssetManager.loadTurretImage(alignment, getSlowSpeed());
        setImage(newImage);
    }

    /**
     * Sets the speed of this unit to half for 6 seconds. Afterwards, the speed of the unit returns to normal after the
     * delay has ended.
     *
     * @see java.util.Timer#schedule(TimerTask, Date)
     * @see TimerTask#run()
     */
    private void setSlowDebuff() {

        setSlowSpeed(2);

        int delayInMilliseconds = 6000;

        new java.util.Timer().schedule(
                new TimerTask() {

                    @Override
                    public void run() { setSlowSpeed(1); }
                }, delayInMilliseconds);
    }
}