package exercises.holyMoly.entities.destructable.enemy.boss;

import exercises.holyMoly.entities.Bullet;
import exercises.holyMoly.entities.destructable.enemy.AbstractEnemy;
import exercises.holyMoly.entities.destructable.enemy.AbstractEnemyBullet;
import exercises.holyMoly.entities.player.Player;
import exercises.holyMoly.Direction;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * The Boss is the strongest enemy unit of this game.
 * <br>
 * The movement pattern of this unit consists of teleportation. The boss disappears from one position and reappears on
 * another position. These positions are way-points. A way-point is an exact position in the room of the boss. The
 * {@link BossAI} has a {@link HashMap} containing all the way-points of the unit. The boss always teleports to the
 * way-point closest to the {@link Player}. When the boss is already at the closest to the {@code player}, it will
 * teleport to the next closest way-point to the player.
 * <br>
 * Whenever the boss appears in a new way-point, it unleashes a set of {@link BossBullet}s in all {@link Direction}s,
 * with a certain delay between rows.
 */
public class Boss extends AbstractEnemy {

    private List<Bullet> bullets;
    private int bulletRowsShot;

    private boolean active;
    private boolean waiting;
    private boolean fastTeleport;

    private BossAI ai;
    private BossAction bossAction;

    /*
    private boolean isActive;                               // variable to make BOSS wait for a specific time
    private boolean isStartingActive;                       // BOSS does not initiate combat until this is true
    private boolean pastHalf;                               // is true after BOSS awaits for a half second
    private boolean pastTeleport;                           // is true after BOSS has teleported. Happens when BOSS is 50%- HP
    */

    /**
     * Constructor method. Initializes the unit's basic properties and instantiates its {@link BossAI}.
     */
    public Boss() {

        setInitialHealth(2000);

        int bossBounds = 100;
        setBounds(bossBounds, bossBounds);

        bullets = new LinkedList<>();

        ai = new BossAI();
        bossAction = BossAction.NONE;
    }

    /**
     * Gets the active state of the boss.
     *
     * @return the active state
     */
    public boolean isActive() {
        return active;
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
     * Gets the {@link BossAI} of this unit.
     *
     * @return the AI
     */
    public BossAI getAi() {
        return ai;
    }

    @Override
    public void act(Player player) {

        if (waiting) {
            return;
        }

        if (bossAction.equals(BossAction.MOVE)) {
            move();
            return;
        }

        if (bossAction.equals(BossAction.SHOOT)) {
            shoot();
        }
    }

    private void move() {}

    /**
     * Forces the unit to suspend the {@link BossAI#act(Player)}'s behaviour for the amount of time specified in
     * {@code delayInMilliseconds}.
     *
     * @param delayInMilliseconds the delay of actions in milliseconds
     * @param nextAction the next action to perform
     */
    private void wait(long delayInMilliseconds, BossAction nextAction) {

        waiting = true;
        bossAction = nextAction;

        new java.util.Timer().schedule(
                new java.util.TimerTask() {

                    @Override
                    public void run() {
                        waiting = false;
                    }

                }, delayInMilliseconds);
    }


    // method that makes the teleportation to a new way-point happen
    public void teleportToNewWayPoint() {

        Random rand = new Random();
        int randomIndex;

        int maxIndex = waypoints.size() - 1;
        int minIndex = 0;

        do {
            randomIndex = rand.nextInt(maxIndex + 1 - minIndex) + minIndex;   // nextInt(max + 1 - min) + min

        } while (waypoints.get(randomIndex) == currentWaypoint);

        // teleport by updating position variables
        setX(waypoints.get(randomIndex).getX());
        setY(waypoints.get(randomIndex).getY());
        this.rectangle.setLocation(this.x, this.y);

        setCurrentWayPoint(waypoints.get(randomIndex)); // update currentWaypoint
    }


    // method that sets the image of BOSS , based on current HP
    public void updateImageByHP() {

        ImageIcon imgIcon = new ImageIcon("src//Img//enemy//bossFullHP.png");   // when HP is below or equal to 2000

        if (hp <= 1500) {            // if HP is below or equal to 75%
            imgIcon = new ImageIcon("src//Img//enemy//boss75.png");

            if (hp <= 1000) {         // if HP is below or equal to 50%
                imgIcon = new ImageIcon("src//Img//enemy//boss50.png");

                if (hp <= 500)        // if HP is below or equal to 25%
                    imgIcon = new ImageIcon("src//Img//enemy//boss25.png");
            }
        }

        image = imgIcon.getImage();
    }


    // method that instantiates the bullets
    public void shoot() {

        for (String direction : directions) {      // loop for the 4 directions

            for (int i = 1; i <= 4; i++) {       // loop for the 4 bullets to be spawned

                bullets.add(new AbstractEnemyBullet(this.x, this.y, direction, this.hp, i));
                bullets.add(new AbstractEnemyBullet(this.x, this.y, direction, this.hp, i));
                bullets.add(new AbstractEnemyBullet(this.x, this.y, direction, this.hp, i));
                bullets.add(new AbstractEnemyBullet(this.x, this.y, direction, this.hp, i));
            }
        }
    }



    @Override
    public void updateImage() {

    }

    // inner class for BossAI
    private class BossAI {

        private HashMap<String, Point> wayPoints;
        private Point closestWayPoint;
        private Point curWayPoint;

        private BossAI() {

            wayPoints = new HashMap<>();

            wayPoints.put("Northwest", new Point(200, 75));
            wayPoints.put("Northeast", new Point(530, 75));
            wayPoints.put("Southwest", new Point(200, 371));
            wayPoints.put("Southeast", new Point(530, 371));
            wayPoints.put("West",      new Point(105, 223));
            wayPoints.put("Center",    new Point(358, 223));
            wayPoints.put("East",      new Point(645, 223));
        }

        // when the BOSS commences battle , activate the boolean and create the real Rectangle
        public void start()  {

            active = true;

            curWayPoint = wayPoints.get("Center");
            setPosition(curWayPoint.x, curWayPoint.y);

            int bossBounds = 100;
            setBounds(bossBounds, bossBounds);
        }

        /**
         * Finds the closest way-point to the {@link Player} and teleports there, if the boss is not on the target
         * way-point.
         * <br>
         * If the boss is already at the target way-point, then it will find the next closest way-point to the player
         * and teleport to that one.
         *
         * @param player the player
         * @return the target way-point
         */
        private Point teleport(Player player) {

            List<Point> wayPoints = (List<Point>) this.wayPoints.values();

            Point closestWayPoint = getClosestWayPoint(player, wayPoints);

            if (closestWayPoint.equals(curWayPoint)) {
                wayPoints.remove(closestWayPoint);

                closestWayPoint = getClosestWayPoint(player, wayPoints);
            }

            return closestWayPoint;
        }

        /**
         * Calculates and returns the closest way-point to the {@link Player}, out of the list of way-points, received
         * as argument. The distance is calculated with a {@code double} precision.
         *
         * @param player the player
         * @return the closest way-point
         * @see BossAI#getDistance(Point, Point)
         */
        private Point getClosestWayPoint(Player player, List<Point> wayPoints) {

            double distance = 99999.0d;
            closestWayPoint = null;
            Point playerPoint = new Point(player.getX(), player.getY());

            for (Point point : wayPoints) {

                if (getDistance(point, playerPoint) < distance) {
                    closestWayPoint = point;
                }
            }

            return closestWayPoint;
        }

        /**
         * Calculates and returns the distance of the two {@link Point}s received as arguments.
         *
         * @param point1 the first point
         * @param point2 the second point
         * @return the distance between the 2 points
         * @see Math#sqrt(double)
         * @see Math#pow(double, double)
         */
        private double getDistance(Point point1, Point point2) {

            return  Math.sqrt(Math.pow(point2.x - point1.x, 2) + Math.pow(point2.y - point1.y, 2));
        }
    }
}
