package exercises.holyMoly.entities.destructable.enemy;

import exercises.holyMoly.Wall;
import exercises.holyMoly.entities.AbstractEntity;
import exercises.holyMoly.entities.player.Player;

import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

public abstract class AbstractEnemy extends AbstractEntity implements Enemy {

    private int velocity;
    private int slowSpeed;  // is 2 if unit is slowed; is 1 otherwise
    private boolean lockingOn;

    /**
     * @see Enemy#setVelocity(int)
     */
    @Override
    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    /**
     * @see Enemy#getVelocity()
     */
    @Override
    public int getVelocity() {
        return velocity;
    }

    /**
     * @see Enemy#setSlowSpeed(int)
     */
    @Override
    public void setSlowSpeed(int slowSpeed) {
        this.slowSpeed = slowSpeed;
    }

    /**
     * @see Enemy#getSlowSpeed()
     */
    @Override
    public int getSlowSpeed() {
        return slowSpeed;
    }

    /**
     * @see Enemy#setLockingOn(boolean)
     */
    @Override
    public void setLockingOn(boolean lockingOn) {
        this.lockingOn = lockingOn;
    }

    /**
     * @see Enemy#isLockingOn()
     */
    @Override
    public boolean isLockingOn() {
        return lockingOn;
    }

    /**
     * @see Enemy#act(Player)
     */
    @Override
    public abstract void act(Player player);

    /**
     * Makes the necessary verifications to determine the if this unit can lock-on the {@link Player}.
     * <br>
     * Firstly, the distance between this unit and the {@code player} is calculated, in {@code double} precision.
     * If this distance is less than 300, the unit and the {@code player} are not close enough for combat. This unit's
     * {@code lockingOn} is set to {@code false}.
     * <br>
     * Otherwise, a {@link Line2D} is drawn between the unit and the {@code player}. If this {@code line} collides with
     * any {@link Wall}, from the list of walls received as an argument, the {@code player} is not in the field of
     * view of this unit. Therefore, the {@code lockingOn} is set to {@code false}. Otherwise, it's set to {@code true}.
     *
     * @param player the player
     * @param walls the list of walls
     * @see AbstractEnemy#getInBetweenLine(int, int, int, int)
     * @see AbstractEnemy#checkPlayerEnemyWallObstruction(Line2D, List)
     */
    public void checkPlayerDistance(Player player, List<Wall> walls) {

        int playerCenterX = (int) player.getCollider().getCenterX();
        int playerCenterY = (int) player.getCollider().getCenterY();

        int enemyCenterX = (int) getCollider().getCenterX();
        int enemyCenterY = (int) getCollider().getCenterY();

        double distance = Math.sqrt(Math.pow(playerCenterX - enemyCenterX, 2)
                                  + Math.pow(playerCenterY - enemyCenterY, 2));

        if (distance >= 300) {

            lockingOn = false;
            return;
        }

        Line2D line = getInBetweenLine(playerCenterX, playerCenterY, enemyCenterX, enemyCenterY);

        boolean collisionWithWall = checkPlayerEnemyWallObstruction(line, walls);

        lockingOn = !collisionWithWall;
    }

    /**
     * Creates and returns a {@link Line2D.Float}, from the {@code position of the player} and the {@code position of
     * the enemy}, both received as arguments.
     *
     * @param playerCenterX central x of the player
     * @param playerCenterY central y of the player
     * @param enemyCenterX central x of the enemy
     * @param enemyCenterY central y of the enemy
     * @return the line
     * @see Line2D.Float()
     */
    private Line2D getInBetweenLine(int playerCenterX, int playerCenterY, int enemyCenterX, int enemyCenterY) {

        // a line is drawn between the ENEMY and the PLAYER
        return new Line2D.Float(playerCenterX, playerCenterY, enemyCenterX,  enemyCenterY);
    }

    /**
     * Verifies if the {@link Line2D} intersects with any {@link Wall} from the list of walls, both received as
     * arguments.
     *
     * @param line the line
     * @param walls the list of walls
     * @return true if there is an intersection; false otherwise
     * @see Line2D#intersects(Rectangle2D)
     */
    private boolean checkPlayerEnemyWallObstruction(Line2D line, List<Wall> walls) {

        for (Wall wall : walls) {

            if (line.intersects(wall.getCollider()))
                return true;
        }

        return false;
    }
}
