package exercises.holyMoly.destructable.enemy.boss;


import exercises.holyMoly.destructable.enemy.AbstractEnemy;
import exercises.holyMoly.destructable.enemy.AbstractEnemyBullet;
import exercises.holyMoly.destructable.enemy.EnemyBullet;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Boss extends AbstractEnemy {

    private HashMap<String, Point> wayPoints;
    private Point curWayPoint;

    private List<EnemyBullet> bullets;

    private boolean isActive;                               // variable to make BOSS wait for a specific time
    private boolean isStartingActive;                       // BOSS does not initiate combat until this is true
    private boolean pastHalf;                               // is true after BOSS awaits for a half second
    private boolean pastTeleport;                           // is true after BOSS has teleported. Happens when BOSS is 50%- HP

    public Boss() {

        this.x = 358;   // position X for WayPoint "Center"
        this.y = 223;   // position Y for WayPoint "Center"
        hp = 2000;

        // create the list and add all the WayPoints
        wayPoints = new HashMap<>();

        wayPoints.put("Northwest", new Point(200, 75));
        wayPoints.put("Northeast", new Point(530, 75));
        wayPoints.put("Southwest", new Point(200, 371));
        wayPoints.put("Southeast", new Point(530, 371));
        wayPoints.put("West",      new Point(105, 223));
        wayPoints.put("Center",    new Point(358, 223));
        wayPoints.put("East",      new Point(645, 223));

        curWayPoint = wayPoints.get("Center");

        bullets = new LinkedList<>();

        isActive = false;
        isStartingActive = false;
        pastHalf = false;
        pastTeleport = false;
    }

    public boolean getStartingActive() {
        return isStartingActive;
    }

    public boolean getActive() {
        return isActive;
    }

    public void setActive() {
        isActive = true;
    }

    public boolean getPastHalf() {
        return pastHalf;
    }

    public boolean getPastTeleport() {
        return pastTeleport;
    }

    public void setPastTeleport(boolean pastTeleport) {
        this.pastTeleport = pastTeleport;
    }

    public void activatePastHalf(boolean trueFalse) {
        this.pastHalf = trueFalse;
    }

    private void setCurrentWayPoint(Point newWayPoint)   {
        curWayPoint = newWayPoint;
    }

    public List<EnemyBullet> getBullets() {
        return bullets;
    }

    // when the BOSS commences battle , activate the boolean and create the real Rectangle
    public void commenceBattle()  {
        isStartingActive = true;

        ImageIcon imgIcon = new ImageIcon("src//Img//enemy//bossFullHP.png");
        image = imgIcon.getImage();

        rectangle = new Rectangle(this.x, this.y, imgIcon.getIconWidth(), imgIcon.getIconHeight());
    }


    // method that makes the BOSS pause all acting for 2 seconds
    public void wait2Seconds() {

        isActive = false;

        // isActive is true after 2 seconds
        new java.util.Timer().schedule(
                new java.util.TimerTask() {

                    @Override
                    public void run() { isActive = true; pastHalf = false; }
                }, 2000);
    }


    // method that makes the BOSS pause all acting for 0.5 seconds
    public void waitHalfSecond() {

        isActive = false;
        pastHalf = false;

        // isActive is true after 0.5 seconds
        new java.util.Timer().schedule(
                new java.util.TimerTask() {

                    @Override
                    public void run() { isActive = true; pastHalf = true; }
                }, 500);
    }


    // method that makes the teleportation to a new Waypoint happen
    public void teleportToNewWaypoint() {

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


    // method for erasing the BOSS , after his defeat
    public void eraseBoss() {

        ImageIcon imgIcon = new ImageIcon();
        this.image = imgIcon.getImage();        // erase the Image

        this.rectangle = new Rectangle();       // erase the Rectangle
    }


    // inner class for Waypoint
    public class Waypoint {

        private int x, y;
        private String name;

        public Waypoint(String _name, int _x, int _y) {

            this.name = _name;
            this.x = _x;
            this.y = _y;
        }


        public String getName() {return name;}
        public int getX() {return x;}
        public int getY() {return y;}
    }
}
