package exercises.holyMoly.entities.player;

import exercises.holyMoly.Game;
import exercises.holyMoly.Background;
import exercises.holyMoly.Direction;
import exercises.holyMoly.Wall;
import exercises.holyMoly.entities.AbstractEntity;
import exercises.holyMoly.entities.Bullet;
import exercises.holyMoly.entities.Entity;
import exercises.holyMoly.entities.EntityType;
import exercises.holyMoly.entities.player.bullets.BazookaBeam;
import exercises.holyMoly.entities.player.bullets.MachineGunBeam;
import exercises.holyMoly.entities.player.bullets.MegaphoneBeam;
import exercises.holyMoly.utils.managers.AssetManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;

import static java.awt.event.KeyEvent.VK_D;
import static java.awt.event.KeyEvent.VK_W;

public class Player extends AbstractEntity {

    private Direction direction;
    private int velocity;

    private WeaponType weaponType;
    private boolean swapWeaponOnCooldown;

    private List<Bullet> bullets;

    private boolean weaponOnCooldown;

    private boolean playerController;

    /**
     * Constructor for the player. Takes in the arguments for starting position and initial direction.
     *
     * @param positionX the x position
     * @param positionY the y position
     * @param direction the initial direction that the player is facing
     * @see AbstractEntity#setPosition(int, int)
     * @see AbstractEntity#setBounds(int, int)
     * @see Player#initWeaponState()
     * @see Player#updateImage()
     */
    public Player(int positionX, int positionY, Direction direction) {

        setEntityType(EntityType.PLAYER);
        setInitialHealth(100);

        int imageToColliderOffset = 15;
        int colliderBounds = 70 - (2 * imageToColliderOffset);

        setPosition(positionX + imageToColliderOffset, positionY + imageToColliderOffset);
        setBounds(colliderBounds, colliderBounds);

        this.direction = direction;
        this.velocity = 2;

        initWeaponState();
        updateImage();
    }

    /**
     * Sets the {@code weaponType} to {@link WeaponType#MACHINEGUN} and initializes properties related to weapons and
     * bullets.
     */
    private void initWeaponState() {

        weaponType = WeaponType.MACHINEGUN;
        swapWeaponOnCooldown = false;
        weaponOnCooldown = false;

        bullets = new LinkedList<>();
    }

    /**
     * Gets the {@link Direction} of the player.
     *
     * @return the direction
     */
    private Direction getDirection() {
        return direction;
    }

    /**
     * Gets the current {@link WeaponType} of the player.
     *
     * @return the weapon type
     */
    public WeaponType getWeaponType() {
        return weaponType;
    }

    /**
     * Gets the list of {@link Bullet}s.
     *
     * @return the list of bullets
     */
    public List<Bullet> getBullets() {
        return bullets;
    }

    /**
     * Sets the control of the player to the value received as argument.
     *
     * @param control the value to set
     */
    public void setPlayerController(boolean control) {
        playerController = control;
    }

    /**
     * Translates the position of the player, based on the values received as arguments.
     * <br>
     * Should only be called by the {@link Game} when the {@link Background} is being moved.
     *
     * @param positionX the x to translate
     * @param positionY the y to translate
     * @see Rectangle#translate(int, int)
     */
    public void push(int positionX, int positionY) {
        getCollider().translate(positionX, positionY);
    }

    /**
     * Moves the player to the desired {@link Direction}, based on the {@code velocity} value.
     * <br>
     * But before any movement is done, it verifies if said movement is possible, by calling
     * {@link Player#isMovementPossible(List, List)} and passing as arguments the lists of objects that were received
     * as arguments as well.
     *
     * @param walls the list of walls
     * @param cratesAndEnemies the list of crates and enemies
     */
    public void move(List<Wall> walls, List<Entity> cratesAndEnemies) {

        if (!isMovementPossible(walls, cratesAndEnemies)) {
            return;
        }

        getCollider().translate(velocity * direction.getPolarityX(), velocity * direction.getPolarityY());
    }

    /**
     * Creates a collider that is translated to the player's desired movement direction. Afterwards, verifies if this
     * translated collider intersects with any {@link Wall} or any {@link Entity}, both received as arguments, to
     * verify is said movement is possible.
     *
     * @param walls the list of walls
     * @param cratesAndEnemies the list of crates and enemies
     * @return true if movement does not intersect any object; false otherwise
     * @see Player#getTranslatedRectangle()
     * @see Rectangle#intersection(Rectangle)
     */
    private boolean isMovementPossible(List<Wall> walls, List<Entity> cratesAndEnemies) {

        Rectangle translatedCollider = getTranslatedRectangle();

        for (Wall wall : walls) {

            if (wall.getCollider().intersects(translatedCollider)) {
                return false;
            }
        }

        for (Entity entity : cratesAndEnemies) {

            if (entity.getCollider().intersects(translatedCollider)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Creates and returns a mock collider, which is then translated to the {@link Direction} that he player is facing.
     *
     * @return the mock collider
     */
    private Rectangle getTranslatedRectangle() {

        Rectangle translatedRectangle = getCollider();

        translatedRectangle.translate(velocity * direction.getPolarityX(), velocity * direction.getPolarityY());

        return translatedRectangle;
    }

    /**
     * Performs the player's behaviour, depending on the keyboard key that the user is pressing.
     * <br>
     * If the {@code playerController} is {@code true}, the movement is verified and its {@link Direction} is updated,
     * based on the {@link KeyEvent}.
     * <br>
     * Afterwards, if the {@code code} is equal to the {@link KeyEvent#VK_SPACE}, then the player will attempt to
     * {@code shoot} with its current {@code weaponType}. Otherwise, if the {@code code} is equal to the
     * {@link KeyEvent#VK_CONTROL}, then the player will attempt to {@code swap weapon}.
     *
     * @param key the event key
     * @see Player#movementKeyPressed(int)
     * @see Player#shoot()
     * @see Player#swapWeapon()
     */
    public void keyPressed(KeyEvent key) {

        if (playerController) {

            int code = key.getKeyCode();

            movementKeyPressed(code);

            if (code == KeyEvent.VK_SPACE) {
                shoot();
                return;
            }

            if (code == KeyEvent.VK_CONTROL) {
                swapWeapon();
            }
        }
    }

    /**
     * Changes the {@link Direction} of the player, based on the {@code code} of the {@link KeyEvent}, received as
     * argument.
     * <br>
     * Depending on the {@code code}, a verification must be made, in order to assess if the player is not already
     * going on the opposite direction, in which case this method does nothing. Otherwise, sets the {@code direction}
     * of the player to the one that corresponds to {@code code} of the {@link KeyEvent}. If the {@code code} does
     * not match any of the directions implemented, the direction is set to {@link Direction#NONE}.
     * <br>
     * If the new {@code direction} is set to {@link Direction#NONE}, then the method ends. Otherwise, if the new
     * {@code direction} is different from the previous one, then the {@link Image} is updated.
     *
     * @param code the code of the {@link KeyEvent}
     */
    private void movementKeyPressed(int code) {

        Direction beforeDirection = direction;

        switch (code) {

            case VK_W:

                if (direction.equals(Direction.DOWN)) {
                    break;
                }

                direction = Direction.UP;
                break;

            case VK_D:

                if (direction.equals(Direction.LEFT)) {
                    break;
                }

                direction = Direction.RIGHT;
                break;

            case KeyEvent.VK_S:

                if (direction.equals(Direction.UP)) {
                    break;
                }

                direction = Direction.DOWN;
                break;

            case KeyEvent.VK_A:

                if (direction.equals(Direction.RIGHT)) {
                    break;
                }

                direction = Direction.LEFT;
                break;

            default:
                direction = Direction.NONE;
        }

        if (direction.equals(Direction.NONE)) {
            return;
        }

        if (!beforeDirection.equals(direction)) {
            updateImage();
        }
    }

    /**
     * Performs the shooting action of the player.
     * <br>
     * If the current {@code weaponOnCooldown} is {@code true}, this does nothing, as the player cannot shoot.
     * Otherwise, a new {@link Bullet} is instantiated, of the specification of the current {@link WeaponType} of the
     * player. The bullet is then added to the list of bullets. Afterwards, if the {@code weaponType} is either
     * {@link WeaponType#MEGAPHONE} or {@link WeaponType#BAZOOKA}, its {@code ammo} is decremented.
     * <br>
     * Finally, {@code weaponOnCooldown} is set to {@code true} and set back to {@code false} after a certain delay,
     * of which each weapon type has its own delay value.
     *
     * @see WeaponType#decrementAmmo()
     * @see Player#setBulletInterval(float)
     */
    @Override
    public void shoot() {

        if (weaponOnCooldown) {
            return;
        }

        switch (weaponType) {

            case MACHINEGUN:

                MachineGunBeam mgBeam = new MachineGunBeam(getX(), getY(), direction);
                bullets.add(mgBeam);
                break;

            case MEGAPHONE:

                if (weaponType.getAmmo() == 0) {
                    return;
                }

                MegaphoneBeam mpBeam = new MegaphoneBeam(getX(), getY(), direction);
                bullets.add(mpBeam);
                weaponType.decrementAmmo();
                break;

            case BAZOOKA:

                if (weaponType.getAmmo() == 0) {
                    return;
                }

                BazookaBeam bzBeam = new BazookaBeam(getX(), getY(), direction);
                bullets.add(bzBeam);
                weaponType.decrementAmmo();
                break;
        }

        weaponOnCooldown = true;
        setBulletInterval(weaponType.getWeaponDelay());
    }

    /**
     * Sets the {@code weaponOnCooldown} to {@code true}. After a certain {@code delay}, received as argument, sets the
     * {@code weaponOnCooldown} back to {@code false}.
     * <br>
     * The received argument is in {@code float} precision and represents the delay in seconds. That value is then
     * converted to a {@code long} precision for milliseconds.
     *
     * @param delayInSeconds the delay
     * @see java.util.Timer#schedule(TimerTask, Date)
     * @see TimerTask#run()
     */
    private void setBulletInterval(float delayInSeconds) {

        long delayInMilliseconds = (long) delayInSeconds * 1000;

        new java.util.Timer().schedule(
                new TimerTask() {

                    @Override
                    public void run() {
                        weaponOnCooldown = false;
                    }
                }, delayInMilliseconds);
    }

    /**
     * Swaps the current {@link WeaponType} to the next activated one.
     * <br>
     * If the current {@code swapWeaponOnCooldown} is true, this does nothing, as the player cannot swap weapons too
     * fast. Otherwise, the current {@code weaponType} is set to be the next {@code activated} weapon type and the
     * {@link Image} is updated.
     * <br>
     * Finally, the {@code swapWeaponOnCooldown} is set to {@code true} and set back to {@code false} after 2 seconds.
     *
     * @see WeaponType#getNextWeapon()
     * @see Player#updateImage()
     * @see java.util.Timer#schedule(TimerTask, Date)
     * @see TimerTask#run()
     */
    private void swapWeapon() {

        if (swapWeaponOnCooldown) {
            return;
        }

        weaponType = weaponType.getNextWeapon();
        updateImage();

        swapWeaponOnCooldown = true;
        int delayInMilliseconds = 2000;

        new java.util.Timer().schedule(
                new java.util.TimerTask() {

                    @Override
                    public void run() {
                        swapWeaponOnCooldown = false;
                    }
                }, delayInMilliseconds);
    }

    /**
     * Updates the player's {@link Image}. Calls the {@link AssetManager} and asks it to fetch the new image from the
     * resources.
     *
     * @see AssetManager#loadPlayerImage(Direction, WeaponType)
     */
    @Override
    public void updateImage() {
        setImage(AssetManager.loadPlayerImage(direction, weaponType));
    }
}