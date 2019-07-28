package game;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Boss {

    private int x, y;                                       // position variables
    private int hp;                                         // life points

    private Image image;                                    // BOSS's image to be rendered

    private Rectangle rectangle;                            // Rectangle for collision

    private ArrayList<Waypoint> waypoints;                  // contains the waypoints for the BOSS to teleport to
    private Waypoint currentWaypoint;                       // BOSS's current waypoint

    private ArrayList<String> directions;                   // contains directions for bullet shooting
    private ArrayList<EnemyBullet> List_of_bullets;         // contains the access to all bullets still alive

    private boolean isActive;                               // variable to make BOSS wait for a specific time
    private boolean isStartingActive;                       // BOSS does not initiate combat until this is true
    private boolean pastHalf;                               // is true after BOSS awaits for a half second
    private boolean pastTeleport;                           // is true after BOSS has teleported. Happens when BOSS is 50%- HP



    public Boss() {

        this.x = 358;   // position X for Waypoint "Center"
        this.y = 223;   // position Y for Waypoint "Center"
        hp = 2000;

        rectangle = new Rectangle();


        // create the list and add all the Waypoints
        waypoints = new ArrayList<>();
        waypoints.add(new Waypoint("Northwest", 200, 75));
        waypoints.add(new Waypoint("Northeast", 530, 75));
        waypoints.add(new Waypoint("Southwest", 200, 371));
        waypoints.add(new Waypoint("Southeast", 530, 371));
        waypoints.add(new Waypoint("West",      105, 223));
        waypoints.add(new Waypoint("Center",    358, 223));
        waypoints.add(new Waypoint("East",      645, 223));

        currentWaypoint = waypoints.get(5);     // Waypoint "Center"

        directions = new ArrayList<>();
        directions.add("Up");
        directions.add("Right");
        directions.add("Down");
        directions.add("Left");

        List_of_bullets = new ArrayList<>();
        isActive = false;
        isStartingActive = false;
        pastHalf = false;
        pastTeleport = false;
    }

    public int getX()               {return x;}
    public int getY()               {return y;}
    private void setX(int x)        {this.x = x;}
    private void setY(int y)        {this.y = y;}

    public int getHP()              {return this.hp;}
    public void setHP(int sumDif)   {hp += sumDif;}

    public Image getImage()         {return image;}

    public Rectangle getRectangle() {return rectangle;}

    public boolean getStartingActive()              {return this.isStartingActive;}

    public boolean getActive()                      {return this.isActive;}
    public void setActive()                         {isActive = true;}
    public boolean getPastHalf() {return this.pastHalf;}


    public boolean getPastTeleport() {return pastTeleport;}

    public void setPastTeleport(boolean pastTeleport) {this.pastTeleport = pastTeleport;}

    public void activatePastHalf(boolean trueFalse) {this.pastHalf = trueFalse;}

    private void setCurrentWaypoint(Waypoint newWaypoint)   {this.currentWaypoint = newWaypoint;}

    public ArrayList<EnemyBullet> getListBullets()          {return this.List_of_bullets;}


    // when the BOSS commences battle , activate the boolean and create the real Rectangle
    public void commenceBattle()  {
        this.isStartingActive = true;

        ImageIcon imgIcon = new ImageIcon("src//Img//enemy//bossFullHP.png");
        image = imgIcon.getImage();

        rectangle = new Rectangle(this.x, this.y, imgIcon.getIconWidth(), imgIcon.getIconHeight());
    }


    // method that makes the BOSS pause all acting for 2 seconds
    public void wait2Seconds() {

        this.isActive = false;

        // isActive is true after 2 seconds
        new java.util.Timer().schedule(
                new java.util.TimerTask() {

                    @Override
                    public void run() { isActive = true; pastHalf = false; }
                }, 2000);
    }


    // method that makes the BOSS pause all acting for 0.5 seconds
    public void waitHalfSecond() {

        this.isActive = false;
        this.pastHalf = false;

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

        setCurrentWaypoint(waypoints.get(randomIndex)); // update currentWaypoint
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

                List_of_bullets.add(new EnemyBullet(this.x, this.y, direction, this.hp, i));
                List_of_bullets.add(new EnemyBullet(this.x, this.y, direction, this.hp, i));
                List_of_bullets.add(new EnemyBullet(this.x, this.y, direction, this.hp, i));
                List_of_bullets.add(new EnemyBullet(this.x, this.y, direction, this.hp, i));
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
