package exercises.holyMoly.destructable.enemy.turret;

import exercises.holyMoly.Direction;
import exercises.holyMoly.destructable.enemy.AbstractEnemy;
import exercises.holyMoly.destructable.enemy.Enemy;
import exercises.holyMoly.destructable.enemy.EnemyBullet;
import exercises.holyMoly.game.Player;
import exercises.holyMoly.utils.AssetManager;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * The Turret is the static enemy unit of this game, which means that this unit never moves aways from its place.
 * <br>
 * The turret is also capable of detecting if the {@link Player} is nearby and on-sight (not behind a
 * {@link exercises.holyMoly.game.Wall} or a {@link exercises.holyMoly.game.Crate}) and attempt to shoot at the
 * shootingDirection that is closest to the {@code player}. This unit's bullets will not be destroyed on collision with
 * {@code walls}.
 * <br>
 * Another particularity of this unit is that, instead of shooting one bullet each time, it shoots two or three
 * bullets at once, depending on the {@code turretType} and {@code shootingDirection}.
 */
public class Turret extends AbstractEnemy {

    private int slowSpeed;

    private boolean lockingOnPlayer;

    private Image image;
    private TurretType turretType;

    private Direction shootingDirection;

    private List<EnemyBullet> bullets;
    private boolean isOnCooldown;

    /**
     * Constructor for this turret. Takes in the arguments for starting position and the type of turret.
     *
     * @param positionX the position in the X axis
     * @param positionY the position in the Y axis
     * @see Enemy#setPosition(int, int)
     * @see Enemy#setBounds(int, int)
     * @see Turret#updateImage()
     */
    public Turret(int positionX, int positionY, TurretType turretType) {

        // TODO: the health needs to be set

        slowSpeed = 1;

        setPosition(positionX, positionY);

        int turretLength = 57;
        int turretWidth = 45;

        if (turretType.equals(TurretType.VERTICAL)) {
            setBounds(turretWidth, turretLength);
        }

        if (turretType.equals(TurretType.HORIZONTAL)) {
            setBounds(turretLength, turretWidth);
        }

        bullets = new LinkedList<>();
        isOnCooldown = false;
        lockingOnPlayer = false;
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

        if (lockingOnPlayer) {
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
     * exact starting position of the bullets will depend on the {@code turretType} and the {@code shootingDirection}.
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

        if (turretType.equals(TurretType.VERTICAL)) {

            switch (shootingDirection) {

                case UP:    // shoots 2 bullets

                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, turretType, 1));
                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, turretType, 2));
                    break;

                case RIGHT: // shoots 3 bullets

                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, turretType, 1));
                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, turretType, 2));
                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, turretType, 3));
                    break;

                case DOWN:  // shoots 2 bullets

                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, turretType, 1));
                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, turretType, 2));
                    break;

                case LEFT:  // shoots 3 bullets
                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, turretType, 1));
                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, turretType, 2));
                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, turretType, 3));
            }
        }

        if (turretType.equals(TurretType.HORIZONTAL)) {

            switch (shootingDirection) {

                case UP:  // shoots 3 bullets

                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, turretType, 1));
                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, turretType, 2));
                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, turretType, 3));

                    break;

                case RIGHT:   // shoots 2 bullets

                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, turretType, 1));
                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, turretType, 2));
                    break;

                case DOWN:    // shoots 3 bullets

                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, turretType, 1));
                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, turretType, 2));
                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, turretType, 3));

                    break;

                case LEFT:    // shoots 2 bullets
                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, turretType, 1));
                    bullets.add(new TurretBullet(getX(), getY(), shootingDirection, turretType, 2));
            }
        }

        isOnCooldown = true;

        int delayInMilliseconds = 2000;

        new java.util.Timer().schedule(
                new TimerTask() {

                    @Override
                    public void run() {
                        isOnCooldown = false;}
                }, delayInMilliseconds * slowSpeed);

    }

    /**
     * Fetches the new {@link Image} to set from the {@link AssetManager}, while passing the necessary arguments of
     * {@code shootingDirection} and {@code slowSpeed}, and assigns the image to the unit's property.
     *
     * @see Enemy#updateImage()
     * @see AssetManager#loadTurretImage(TurretType, int)
     */
    @Override
    public void updateImage() {

        Image newImage = AssetManager.loadTurretImage(turretType, slowSpeed);
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

        slowSpeed = 2;

        int delayInMilliseconds = 6000;

        // ENEMY's speed is back to normal after 6 seconds
        new java.util.Timer().schedule(
                new TimerTask() {

                    @Override
                    public void run() { slowSpeed = 1; }
                }, delayInMilliseconds);
    }



}