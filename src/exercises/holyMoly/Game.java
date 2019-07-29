package exercises.holyMoly.game;

import exercises.holyMoly.destructable.enemy.Enemy;
import exercises.holyMoly.destructable.enemy.sentry.Sentry;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.util.*;

public class Game extends JPanel implements ActionListener {

    private Background background;
    private int score;
    private Player player;
    private boolean isPlayerLastRoom;
    private boolean playerController;
    private Boss boss;
    private int bossShootingCount;

    private Image beginningScreen;
    private Image winningScreen;
    private Image gameOverScreen;
    private boolean gameStart;


    private ArrayList<Enemy>    LIST_of_ENEMIES;
    private ArrayList<Sentry>   LIST_of_SENTRIES;
    private ArrayList<Wall>     LIST_of_WALLS;
    private ArrayList<Crate>    LIST_of_CRATES;
    private ArrayList<Pickable> LIST_of_PICKABLES;
    private ArrayList<String>   LIST_of_possible_Pickables;   // this List contains Strings , which represent the possible outcomes
    private ArrayList<Spikes>   LIST_of_SPIKES;


    private ArrayList<Enemy>    list_of_enemies;
    private ArrayList<Wall>     list_of_walls;
    private ArrayList<Sentry>   list_of_sentries;
    private ArrayList<Crate>    list_of_crates;
    private ArrayList<Pickable> list_of_pickables;
    private ArrayList<Spikes>   list_of_spikes;

    private Timer timer;
    private boolean pause;
    private int currentTag, nextTag;


    public Game(){
        super.setFocusable(true);
        super.setDoubleBuffered(true);
        super.addKeyListener(new KeyboardAdapter());

        background = new Background();
        score = 0;
        player = new Player (35,470);
        playerController = false;
        boss = new Boss();
        isPlayerLastRoom = false;
        bossShootingCount = 0;

        ImageIcon WinIcon = new ImageIcon("src//Img//screens//vict2.png");
        winningScreen = WinIcon.getImage();

        ImageIcon LoseIcon = new ImageIcon("src//Img//screens//fail2.png");
        gameOverScreen = LoseIcon.getImage();

        ImageIcon StartIcon = new ImageIcon("src//Img//screens//start2.png");
        beginningScreen = StartIcon.getImage();

        gameStart = false;
        player.setPlayerController(false);



        LIST_of_ENEMIES = new ArrayList<>();
        LIST_of_SENTRIES = new ArrayList<>();
        LIST_of_WALLS = new ArrayList<>();
        LIST_of_CRATES = new ArrayList<>();
        LIST_of_PICKABLES = new ArrayList<>();
        LIST_of_possible_Pickables = new ArrayList<>();
        LIST_of_SPIKES = new ArrayList<>();


        //sentries

        // nivel 1
        LIST_of_SENTRIES.add(new Sentry(335, 460, 1, "Vertical"));
        LIST_of_SENTRIES.add(new Sentry(775, 463, 1, "Vertical"));

        // nivel 2
        LIST_of_SENTRIES.add(new Sentry(335, 463, 2, "Vertical"));
        LIST_of_SENTRIES.add(new Sentry(760, 30,  2, "Horizontal"));
        LIST_of_SENTRIES.add(new Sentry(760, 190, 2, "Horizontal"));
        LIST_of_SENTRIES.add(new Sentry(630, 300, 2, "Vertical"));

        // nivel 3
        LIST_of_SENTRIES.add(new Sentry(310, 50,  3, "Vertical"));
        LIST_of_SENTRIES.add(new Sentry(200, 295, 3, "Horizontal"));
        LIST_of_SENTRIES.add(new Sentry(760, 445, 3, "Vertical"));


        //crates

        // nivel 1
        LIST_of_CRATES.add(new Crate(91,  417, 1));
        LIST_of_CRATES.add(new Crate(152, 490, 1));
        LIST_of_CRATES.add(new Crate(494, 309, 1));
        LIST_of_CRATES.add(new Crate(539, 311, 1));
        LIST_of_CRATES.add(new Crate(494, 349, 1));
        LIST_of_CRATES.add(new Crate(539, 349, 1));

        // nivel 2
        LIST_of_CRATES.add(new Crate(171, 489, 2));
        LIST_of_CRATES.add(new exercises.holyMoly.game.Crate(400, 133, 2));
        LIST_of_CRATES.add(new Crate(447, 133, 2));
        LIST_of_CRATES.add(new Crate(494, 156, 2));
        LIST_of_CRATES.add(new Crate(584, 156, 2));
        LIST_of_CRATES.add(new Crate(538, 227, 2));
        LIST_of_CRATES.add(new Crate(699, 108, 2));

        // nivel 3
        LIST_of_CRATES.add(new Crate(27,  24,  3));
        LIST_of_CRATES.add(new Crate(30,  309, 3));
        LIST_of_CRATES.add(new Crate(66,  309, 3));
        LIST_of_CRATES.add(new Crate(157, 454, 3));
        LIST_of_CRATES.add(new Crate(158, 491, 3));
        LIST_of_CRATES.add(new Crate(381, 366, 3));


        // spikes

        // nivel 1
        LIST_of_SPIKES.add(new Spikes(524, 149, 1));

        // nivel 2
        LIST_of_SPIKES.add(new Spikes(224, 352, 2));
        LIST_of_SPIKES.add(new Spikes(406, 183, 2));

        // nivel 3
        LIST_of_SPIKES.add(new Spikes(178, 262, 3));
        LIST_of_SPIKES.add(new Spikes(452, 368, 3));


        // pickables
        LIST_of_possible_Pickables.add("Rocket");
        LIST_of_possible_Pickables.add("Rocket");
        LIST_of_possible_Pickables.add("Battery");
        LIST_of_possible_Pickables.add("Battery");
        LIST_of_possible_Pickables.add("Megaphone");
        LIST_of_possible_Pickables.add("Bazooka");
        LIST_of_possible_Pickables.add("Cross");
        LIST_of_possible_Pickables.add("Cross");
        LIST_of_possible_Pickables.add("Cross");
        LIST_of_possible_Pickables.add("Cross");


        list_of_enemies = new ArrayList<>();
        list_of_sentries = new ArrayList<>();
        list_of_walls = new ArrayList<>();
        list_of_crates = new ArrayList<>();
        list_of_pickables = new ArrayList<>();
        list_of_spikes = new ArrayList<>();


        timer = new Timer(5, this);
        timer.start();

        pause = false;
        currentTag = 1;
        nextTag = 0;
        updateObjectsByTag(currentTag);
    }


    // colisao com paredes e crates
    private boolean isMovementPossible() {

        for (exercises.holyMoly.game.Wall wall : list_of_walls) {   // checks all WALLS

                if (wall.getRectangle().intersects(player.getTranslatedRectangle()))
                    return false;

        }

        for (Crate crate : list_of_crates) {    // checks all CRATES

                if (crate.getRectangle().intersects(player.getTranslatedRectangle()))
                    return false;

        }

        return true;
    }


    // ver se há paredes entre enemy e player
    private void checkPlayerEnemyDistance(Enemy enemy) {

        // calculate distance between PLAYER and ENEMY
        double distance = Math.sqrt(Math.pow( (player.getX() + 35) - (enemy.getX() + 35), 2)
                                  + Math.pow( (player.getY() + 35) - (enemy.getY() + 35), 2));


        // if PLAYER and ENEMY are closer than 300 units
        if (distance < 300) {
            boolean check = checkPlayerEnemyWallObstruction(enemy); // checks to know if there is at least a wall between the two

            if (check)  // if there is, the ENEMY does not Lock-on the PLAYER
                enemy.setPlayerLockOn(false);

            else        // else, the ENEMY Locks-on the PLAYER
                enemy.setPlayerLockOn(true);
        }

        // if the PLAYER and ENEMY are not closer than 300 units , the ENEMY does not Lock-on the PLAYER
        else
            enemy.setPlayerLockOn(false);
    }


    // method for checking whether or not PLAYER and SENTRY are close by
    private void checkPlayerSentryDistance(Sentry sentry) {

        // calculate distance between PLAYER and ENEMY
        double distance = Math.sqrt(Math.pow( (player.getX() + 35) - (sentry.getX() + 35), 2)
                + Math.pow( (player.getY() + 35) - (sentry.getY() + 35), 2));


        // if PLAYER and ENEMY are closer than 300 units
        if (distance < 300)
            sentry.setPlayerLockOn(true);

            // if the PLAYER and ENEMY are not closer than 300 units , the ENEMY does not Lock-on the PLAYER
        else
            sentry.setPlayerLockOn(false);
    }


    // method for drawing a line between ENEMY and PLAYER and seeing if intersects any wall
    private boolean checkPlayerEnemyWallObstruction(Enemy enemy) {

        // a line is drawn between the ENEMY and the PLAYER
        Line2D.Float line = new Line2D.Float(player.getRectX() + 35, player.getRectY() + 35,
                                             enemy.getRectX() + 35,  enemy.getRectY() + 35);

        // check if the line INTERSECTS with any RECTANGLE
        for (Wall wall : list_of_walls) {

            if (line.intersects(wall.getRectangle()))
                return true;
        }
        return false;
    }

//.....
    // method for checking the area that the PLAYER needs to be in order to transition to another map
    private void checkMapTransition() {

        if (!pause) {   // 'pause' garanties that transitioning is happening.
            // Therefore, this does not happen repeatedly
            switch (currentTag) {

                case 1:
                    if ((player.getRectX() > 725 && player.getRectX() < 770) && (player.getRectY() < 30)) {
                        pause = true;
                        nextTag = 2;
                    }
                    break;

                case 2:
                    // transition to TAG = 1
                    if ((player.getRectX() > 725 && player.getRectX() < 805) && (player.getRectY() > 520 - 20)) {
                        pause = true;
                        nextTag = 1;
                    }

                    // transition to TAG = 3
                    if ((player.getRectX() > 850 - 30) && (player.getRectY() > 98 && player.getRectY() < 153)) {
                        pause = true;
                        nextTag = 3;
                    }
                    break;

                case 3:
                    // transition to TAG = 2
                    if ((player.getRectX() < 30) && (player.getRectY() > 98 && player.getRectY() < 115)) {
                        pause = true;
                        nextTag = 2;
                    }

                    // transition to TAG = 4
                    if ((player.getRectX() > 50 && player.getRectX() < 132) && (player.getRectY() > 550 - 20)) {
                        pause = true;
                        nextTag = 4;
                    }
                    break;
            }
        }

        // when 'pause' is TRUE , the PLAYER and BACKGROUND are moved according to the TAGS
        if (pause)
            changeMapTo(currentTag, nextTag);
    }


    // method for updating the TAGS after transition is complete
    private void updateTags(int newCurrent) {

        this.pause = false;
        this.currentTag = newCurrent;
        this.nextTag = 0;
    }


    // method that executes the movement of the BACKGROUND and the PLAYER
    private void changeMapTo(int currentTag, int nextTag) {
        list_of_enemies.clear();
        list_of_sentries.clear();
        list_of_walls.clear();
        list_of_crates.clear();
        list_of_pickables.clear();
        list_of_spikes.clear();


        switch (currentTag) {

            case 1:

                // background is pushed DOWNWARDS -- Player is pushed a little less
                if (pause && (background.getY() != 0)) {
                    background.push(0, 5);
                    player.push(0, 4);
                    player.setPlayerController(false);

                    // when that is complete, this happens
                    if (background.getY() == 0) {
                        updateTags(2);
                        updateObjectsByTag(this.currentTag);
                        player.setPlayerController(true);
                    }
                }
                break;

            case 2:
                if (nextTag == 1) {

                    // background is pushed UPWARDS -- Player is pushed a little less
                    if (pause && (background.getY() != -background.getHeight() / 2)) {
                        background.push(0, -5);
                        player.push(0, -4);
                        player.setPlayerController(false);

                        // when that is complete, this happens
                        if (background.getY() == -background.getHeight() / 2) {
                            updateTags(1);
                            updateObjectsByTag(this.currentTag);
                            player.setPlayerController(true);
                        }
                    }
                }

                if (nextTag == 3) {

                    // background is pushed LEFTWARD -- Player is pushed a little less
                    if (pause && (background.getX() != -(background.getWidth() / 2))) {
                        background.push(-5, 0);
                        player.push(-4, 0);player.setPlayerController(false);

                        // when that is complete, this happens
                        if (background.getX() == -(background.getWidth() / 2)) {
                            updateTags(3);
                            updateObjectsByTag(this.currentTag);
                            player.setPlayerController(true);
                        }
                    }
                }
                break;

            case 3:
                if (nextTag == 2) {

                    // background is pushed RIGHTWARD -- Player is pushed a little less
                    if (pause && (background.getX() != 0)) {
                        background.push(5, 0);
                        player.push(4, 0);
                        player.setPlayerController(false);

                        // when that is complete, this happens
                        if (background.getX() == 0) {
                            updateTags(2);
                            updateObjectsByTag(this.currentTag);
                            player.setPlayerController(true);
                        }
                    }
                }

                if (nextTag == 4) {

                    // background is pushed UPWARDS -- Player is pushed a little less
                    if (pause && (background.getY() != -(background.getHeight() / 2))) {
                        background.push(0, -5);
                        player.push(0, -4);
                        player.setPlayerController(false);

                        // when that is complete, this happens
                        if (background.getY() == -(background.getHeight() / 2)) {
                            updateTags(4);
                            updateObjectsByTag(this.currentTag);

                            player.getList_Missiles().clear();
                            player.getList_MG().clear();
                            player.getList_Beams().clear();

                            isPlayerLastRoom = true;
                            player.setPlayerController(true);
                        }
                    }
                }
                break;
        }
    }


    private void updateObjectsByTag(int tag) {



        // activate ENEMIES
        for (Enemy enemy : LIST_of_ENEMIES) {
            if (enemy.getTag() == tag)
                list_of_enemies.add(enemy);
        }

        // activate SENTRIES
        for (Sentry sentry : LIST_of_SENTRIES) {
            if (sentry.getTAG() == tag)
                list_of_sentries.add(sentry);
        }

        // activate WALLS
        for (Wall wall : LIST_of_WALLS) {
            if (wall.getTAG() == tag)
                list_of_walls.add(wall);
        }

        // activate CRATES
        for (Crate crate : LIST_of_CRATES) {
            if (crate.getTag() == tag)
                list_of_crates.add(crate);
        }

        // activate PICKABLES
        for (Pickable pickable : LIST_of_PICKABLES) {
            if (pickable.getTAG() == tag)
                list_of_pickables.add(pickable);
        }

        // activate SPIKES
        for (Spikes spikes : LIST_of_SPIKES) {
            if (spikes.getTAG() == tag)
                list_of_spikes.add(spikes);
        }
    }
//........

    // method that spawns a random Pickable
    private void spawnRandomPickable(int crateX, int crateY) {
        if (LIST_of_possible_Pickables.size()>0) {

            Random rand = new Random();         // will be used to generate random values
            boolean b = rand.nextBoolean();     // returns a random true or false


        // if number of possible Picks is equal to the current number of crates , a Pick must be spawned
        // or if the random boolean is true

           if ((LIST_of_possible_Pickables.size() == list_of_crates.size()) || b) {

               // determine the maximum and minimal values for the index of the List
               int maxIndex = LIST_of_possible_Pickables.size() - 1;
               int minIndex = 0;
               int randomIndex = rand.nextInt(maxIndex - minIndex + 1) + minIndex;     // nextInt(max - min + 1) + min

               // spawn the new Pickable and attach it to this TAG
               // also, add it to the corresponding Lists
               LIST_of_PICKABLES.add(new Pickable(LIST_of_possible_Pickables.get(randomIndex), crateX, crateY, currentTag));
               list_of_pickables.add(LIST_of_PICKABLES.get(LIST_of_PICKABLES.size() - 1));

               // lastly , remove the said Pickable from its previous List
               LIST_of_possible_Pickables.remove(randomIndex);
           }
       }
    }


    // method for collision between bullets and characters
    private void checkBulletCollision() {

        // check all PLAYER's Machine Gun Bullets -- Machine Gun deals 25 damage
        for (MachineGunBeam mg_beam : player.getList_MG()) {

            // against all WALLS
            for (Wall wall : list_of_walls) {
                if (mg_beam.getRectangle().intersects(wall.getRectangle())) {
                    player.getList_MG().remove(mg_beam);                        // eliminate access to bullet
                    return;
                }
            }


            // against all ENEMIES
            Enemy toRemoveEnemy = null;
            for (Enemy enemy : list_of_enemies) {
                if (mg_beam.getRectangle().intersects(enemy.getRectangle())) {
                    enemy.setHP(-25);

                    if (enemy.getHP() <=0)
                        toRemoveEnemy = enemy;

                    player.getList_MG().remove(mg_beam);        // eliminate access to bullet
                    LIST_of_ENEMIES.remove(toRemoveEnemy);
                    list_of_enemies.remove(toRemoveEnemy);
                    return;
                }
            }


            // against all SENTRIES
            Sentry toRemoveSentry = null;            // removes the sentry from list to kill it
            for (Sentry sentry : list_of_sentries) {
                if (mg_beam.getRectangle().intersects(sentry.getRectangle())) {
                    sentry.setHP(-25);

                    // destroy the sentry if HP is 0 or less
                    if (sentry.getHP() <= 0)
                        toRemoveSentry = sentry;

                    player.getList_MG().remove(mg_beam);        // eliminate access to bullet
                    LIST_of_SENTRIES.remove(toRemoveSentry);    // eliminate access to LIST
                    list_of_sentries.remove(toRemoveSentry);    // eliminate access to list
                    return;
                }
            }

            // against the BOSS
            if (mg_beam.getRectangle().intersects(boss.getRectangle())) {
                boss.setHP(-25);

                // destroy the BOSS if HP is 0 or less
                if (boss.getHP() <= 0)
                    boss.eraseBoss();                           // garanties the erasure of Image and Rectangle

                player.getList_MG().remove(mg_beam);            // eliminate access to bullet
                return;
            }

            // against all CRATES
            Crate toRemoveCrate = null;            // removes the crate from list to destroy it
            for (Crate crate : list_of_crates) {
                if (mg_beam.getRectangle().intersects(crate.getRectangle())) {
                    crate.setHP(-25);

                    // destroy the crate if HP is 0 or less
                    if (crate.getHP() <= 0) {
                        toRemoveCrate = crate;

                        spawnRandomPickable(crate.getX(), crate.getY());    // spawn a random Pickable in this position
                    }

                    player.getList_MG().remove(mg_beam);      // eliminate access to bullet
                    LIST_of_CRATES.remove(toRemoveCrate);     // eliminate access to LIST
                    list_of_crates.remove(toRemoveCrate);     // eliminate access to list
                    return;
                }
            }
        }

        // check all PLAYER's Megaphone Bullets -- Megaphone doesn't do damage. Instead, deals slow-debuff
        for (MegaphoneBeam megaBeam : player.getList_Beams()) {

            // against all WALLS
            for (Wall wall : list_of_walls) {
                if (megaBeam.getRectangle().intersects(wall.getRectangle())) {
                    player.getList_Beams().remove(megaBeam);                        // eliminate access to bullet
                    return;
                }
            }

            // against all ENEMIES
            for (Enemy enemy : list_of_enemies) {
                if (megaBeam.getRectangle().intersects(enemy.getRectangle())) {
                    enemy.setSlowDebuff();  // ENEMY gets slowed

                    player.getList_Beams().remove(megaBeam);    // eliminate access to bullet
                    return;
                }
            }

            // against all SENTRIES
            for (Sentry sentry : list_of_sentries) {
                if (megaBeam.getRectangle().intersects(sentry.getRectangle())) {
                    sentry.setSlowDebuff(); // SENTRY gets slowed

                    player.getList_Beams().remove(megaBeam);    // eliminate access to bullet
                    return;
                }
            }

            // against all CRATES
            for (Crate crate : list_of_crates) {
                if (megaBeam.getRectangle().intersects(crate.getRectangle())) {
                    // nothing happens here

                    player.getList_Beams().remove(megaBeam);    // eliminate access to bullet
                    return;
                }
            }
        }

        // checks all PLAYER's Bazooka Bullets -- Bazooka deals 250 damage
        for (BazookaBeam missile : player.getList_Missiles()) {

            // against all WALLS
            for (Wall wall : list_of_walls) {
                if (missile.getRectangle().intersects(wall.getRectangle())) {
                    player.getList_Missiles().remove(missile);                        // eliminate access to bullet
                    return;
                }
            }

            // against all ENEMIES
            Enemy toRemoveMinion = null;            // removes the ENEMY from list to kill it
            for (Enemy enemy : list_of_enemies) {
                if (missile.getRectangle().intersects(enemy.getRectangle())) {
                    enemy.setHP(-250);

                    // destroy the ENEMY if HP is 0 or less
                    if (enemy.getHP() <= 0)
                        toRemoveMinion = enemy;

                    player.getList_Missiles().remove(missile);  // eliminate access to bullet
                    LIST_of_ENEMIES.remove(toRemoveMinion);     // eliminate access to LIST
                    list_of_enemies.remove(toRemoveMinion);     // eliminate access to list
                    return;
                }
            }

            // against all SENTRIES
            Sentry toRemoveSentry = null;            // removes the SENTRY from list to kill it
            for (Sentry sentry : list_of_sentries) {
                if (missile.getRectangle().intersects(sentry.getRectangle())) {
                    sentry.setHP(-250);

                    // destroy the SENTRY if HP is 0 or less
                    if (sentry.getHP() <= 0)
                        toRemoveSentry = sentry;

                    player.getList_Missiles().remove(missile);  // eliminate access to bullet
                    LIST_of_SENTRIES.remove(toRemoveSentry);    // eliminate access to LIST
                    list_of_sentries.remove(toRemoveSentry);    // eliminate access to list
                    return;
                }
            }

            // against the BOSS
            if (missile.getRectangle().intersects(boss.getRectangle())) {
                boss.setHP(-250);

                // destroy the BOSS if HP is 0 or less
                if (boss.getHP() <= 0)
                    boss.eraseBoss();                           // garanties the erasure of Image and Rectangle

                player.getList_Missiles().remove(missile);            // eliminate access to bullet
                return;
            }

            // against all CRATES
            Crate toRemoveCrate = null;
            for (Crate crate : list_of_crates) {
                if (missile.getRectangle().intersects(crate.getRectangle())) {
                    crate.setHP(-250);

                    // destroy the crate if HP is 0 or less
                    if (crate.getHP() <= 0) {
                        toRemoveCrate = crate;

                        spawnRandomPickable(crate.getX(), crate.getY());    // spawn a random Pickable in this position
                    }

                    player.getList_Missiles().remove(missile);
                    LIST_of_CRATES.remove(toRemoveCrate);
                    list_of_crates.remove(toRemoveCrate);
                    return;
                }
            }
        }

        // check all ENEMIES' EnemyBullets
        for (Enemy enemy : list_of_enemies) {
            for (EnemyBullet bullet : enemy.getList_bullets()) {

                // against the PLAYER
                if (bullet.getRectangle().intersects(player.getRectangle())) {
                    player.setHP(-50);
                    enemy.getList_bullets().remove(bullet);     // eliminate access to bullet
                    return;
                }

                // against all WALLS
                for (Wall wall : list_of_walls) {

                    if (bullet.getRectangle().intersects(wall.getRectangle())) {
                        enemy.getList_bullets().remove(bullet); // eliminate access to bullet
                        return;
                    }
                }
            }
        }

        // check all SENTRIES' EnemyBullets
        ArrayList<EnemyBullet> toRemoveSentryBullets = new ArrayList<>();
        for (Sentry sentry : list_of_sentries) {
            for (EnemyBullet bullet : sentry.getList_bullets()) {

                // against the PLAYER
                if (bullet.getRectangle().intersects(player.getRectangle())) {
                    player.setHP(-25);
                    toRemoveSentryBullets.add(bullet);
                }
            }

            // remove bullets , if there are any
            if (toRemoveSentryBullets.size() > 0) {

                sentry.getList_bullets().removeAll(toRemoveSentryBullets);  // eliminate access to bullets
                return;
            }
        }

        // check all BOSS EnemyBullets
        ArrayList<EnemyBullet> toRemoveBossBullets = new ArrayList<>();
        for (EnemyBullet bullet : boss.getListBullets()) {

            // against the PLAYER
            if (bullet.getRectangle().intersects(player.getRectangle())) {
                player.setHP(-25);
                toRemoveBossBullets.add(bullet);
            }
        }
        boss.getListBullets().removeAll(toRemoveBossBullets);

    }


    // method for collision with Pickables
    private void checkPlayerPickableCollision() {

        for (Pickable pickable : list_of_pickables) {

            Pickable toRemovePickable = null;       // is used to destroy the Pickable , if it's case
            if (pickable.getRectangle().intersects(player.getRectangle())) {

                switch (pickable.getType()) {

                    case "Cross":
                        if (player.getHp() != 100) {
                            player.maxHP();                 // maximizes PLAYER's HP
                            toRemovePickable = pickable;
                            score += 100;
                        }
                        break;

                    case "Megaphone":
                        player.activateMegaphone();      // PLAYER can now swap weapon to Megaphone
                        toRemovePickable = pickable;
                        score += 100;
                        break;

                    case "Battery":
                        if (player.getMegaphone()) {
                            player.setMegaAmmo(2);      // Battery gives 2 Megaphone ammo
                            toRemovePickable = pickable;
                            score += 100;
                        }
                        break;

                    case "Bazooka":
                        player.activateBazooka();        // PLAYER can now swap weapon to Bazooka
                        toRemovePickable = pickable;
                        score += 100;
                        break;

                    default:
                        if (player.getBazooka()) {
                            player.setBazookaAmmo(2);       // Rocket gives 2 Bazooka ammo
                            toRemovePickable = pickable;
                            score += 100;
                        }
                }

                LIST_of_PICKABLES.remove(toRemovePickable); // eliminate access to LIST
                list_of_pickables.remove(toRemovePickable); // eliminate access to list


                // end method early if collision was found
                if (toRemovePickable != null)
                    return;
            }
        }
    }


    // method for collision with Spikes
    private void checkPlayerSpikesCollision() {

        // checking all SPIKES
        for (Spikes spikes : list_of_spikes) {

            if (!spikes.getVisible()) {     // only verifies collision if it's not visible

                if (spikes.getRectangle().intersects(player.getRectangle())) {

                    player.setHP(-50);
                    spikes.setVisible();    // SPIKES become visible and obsolete
                }
            }
        }
    }


    // method for managing the AI for Boss
    private void managerAIBoss() {

        // happens until BOSS starts

        if (!boss.getStartingActive()) {

            // if Player shoots , BOSS starts
            if (player.getList_MG().size() > 0 || player.getList_Beams().size() > 0 || player.getList_Missiles().size() > 0) {
                boss.commenceBattle();
                boss.setActive();
                return;

            }

            // else , Boss sleeps
            else {
                boss.updateImageByHP();
                return;
            }
        }


    // always have the image updated , based on current HP
        boss.updateImageByHP();


        if (boss.getActive()) {                 // if BOSS is activated
            if (boss.getHP() > 1000) {              // if HP is higher than 50%

            if (!boss.getPastHalf()) {              // if it's pre-waiting the 0.5 seconds
                boss.teleportToNewWaypoint();           // teleport to new Waypoint
                boss.waitHalfSecond();                  // wait 0.5 seconds
            }

            else {                                      // after waiting 0.5 seconds
                boss.shoot();                               // BOSS shoots once
                boss.wait2Seconds();                        // waits 2 seconds
            }

        }

        else {                                // if HP is 50% or lower

            if (!boss.getPastTeleport()) {          // if Teleport is yet to happen
                boss.teleportToNewWaypoint();           // teleport to new Waypoint
                boss.setPastTeleport(true);             // make sure Teleport does not happen until next cycle
            }

            else if (bossShootingCount < 3) {       // if 3 shootings haven't happened yet

                boss.shoot();                           // BOSS shoots
                bossShootingCount++;                    // counter increments
                boss.waitHalfSecond();                  // wait 0.5 seconds
            }

            else {                                  // after the first two waves
                                      // BOSS shoots
                boss.wait2Seconds();                    // wait 2 seconds
                boss.setPastTeleport(false);            // Teleport can now happen
                bossShootingCount = 0;                  // reset the counter
            }
        }
    }
}


    @Override
    public void paint (Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        // BACKGROUND
        g2d.drawImage(background.getImg(), background.getX(), background.getY(), null);


        // HUD LIFE / SCORE
        g2d.drawString("Life points: " + String.valueOf(player.getHp()), 400, 15);
        g2d.drawString("Score: " + String.valueOf(score), 30, 15);
        g2d.drawString("Weapon: " + String.valueOf(player.getWeaponType()), 120, 15);

        checkPlayerSpikesCollision();
        checkPlayerPickableCollision();
        checkBulletCollision();
        checkMapTransition();

        if (isPlayerLastRoom && boss.getHP() > 0)
            managerAIBoss();


        // SPIKES
        for (Spikes spikes : list_of_spikes) {

            if (spikes.getVisible()) {
                g2d.drawImage(spikes.getImage(), spikes.getX(), spikes.getY(), this);
            }
        }


        // CRATES
        for (Crate crate : list_of_crates) {
            g2d.drawImage(crate.getImage(), crate.getX(), crate.getY(), this);
        }


        // PICKABLES
        for (Pickable pickable : list_of_pickables) {
            g2d.drawImage(pickable.getImage(), pickable.getX(), pickable.getY(), this);
        }

        // PLAYER
        if (!pause) {
            if (playerController)
                if (isMovementPossible())
                player.move();
        }
        g2d.drawImage(player.getImage(), player.getX(), player.getY(), null);


        // MEGAPHONE BULLETS

        for (MegaphoneBeam beam : player.getList_Beams()) {

            g2d.drawImage(beam.getImage(), beam.getX(), beam.getY(), this);



        }



        // MACHINE GUN BULLETS

        for (MachineGunBeam beam : player.getList_MG()) {

            g2d.drawImage(beam.getImage(), beam.getX(), beam.getY(), this);

        }


        // MISSILES BULLETS

        for (BazookaBeam missile : player.getList_Missiles()) {

            g2d.drawImage(missile.getImage(), missile.getX(), missile.getY(), this);

            // Destroy the beam if it's out of bounds

        }

        if (gameStart && player.getHp() > 0) {

            // ENEMY
            for (Enemy enemy : list_of_enemies) {

                checkPlayerEnemyDistance(enemy);
                enemy.updateImageByDebuff();
                enemy.moveAccordingToBehaviour();   // this will make the ENEMY move in the right direction , according
                // to its current BEHAVIOUR


                enemy.attackThePlayer(player.getX() + 35, player.getY() + 35);      // PLAYER's central variables

                enemy.move();
                g2d.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), this);
            }

            // ENEMY BULLETS
            ArrayList<EnemyBullet> toRemoveBullets = new ArrayList<>();
            for (Enemy minion : list_of_enemies) {
                for (EnemyBullet bullet : minion.getList_bullets()) {

                    bullet.move();
                    g2d.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), this);

                    // Destroy the beam if it's out of bounds
                    if ((bullet.getY() <= 0) || (bullet.getY() >= 530) || (bullet.getX() <= 0) || (bullet.getX() >= 820))
                        toRemoveBullets.add(bullet);
                }

                minion.getList_bullets().removeAll(toRemoveBullets);
            }


            // SENTRY
            for (Sentry sentry : list_of_sentries) {

                sentry.updateImageByDebuff();
                checkPlayerSentryDistance(sentry);
                sentry.attackThePlayer(player.getX() + 35, player.getY() + 35);     // PLAYER's central variables

                g2d.drawImage(sentry.getImage(), sentry.getX(), sentry.getY(), this);
            }

            // SENTRY BULLETS
            ArrayList<EnemyBullet> toRemoveSentryBullets = new ArrayList<>();
            for (Sentry sentry : list_of_sentries) {
                for (EnemyBullet bullet : sentry.getList_bullets()) {

                    bullet.move();
                    g2d.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), this);

                    // Destroy the beam if it's out of bounds
                    if ((bullet.getY() <= 0) || (bullet.getY() >= 530) || (bullet.getX() <= 0) || (bullet.getX() >= 820))
                        toRemoveSentryBullets.add(bullet);
                }

                sentry.getList_bullets().removeAll(toRemoveSentryBullets);
            }

            // BOSS BULLETS
            ArrayList<EnemyBullet> toRemoveBossBullets = new ArrayList<>();
            for (EnemyBullet bullet : boss.getListBullets()) {

                bullet.move();
                g2d.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), this);

                if ((bullet.getY() <= 0) || (bullet.getY() >= 500) || (bullet.getX() <= 0) || (bullet.getX() >= 790))
                    toRemoveBossBullets.add(bullet);
            }

            boss.getListBullets().removeAll(toRemoveBossBullets);
        }

            // BOSS

                if (boss != null)
                    g2d.drawImage(boss.getImage(), boss.getX(), boss.getY(), this);







    // BEGINNING SCREEN
        if (!gameStart) {
        g2d.drawImage(beginningScreen, 0, 20, this);
    }


    // WINNING SCREEN
        if (player.getHp() > 0 && boss.getHP() <= 0) {
        playerController = false;
        player.setPlayerController(false);
        g2d.drawImage(winningScreen, 200, 250, this);
    }


    // GAME OVER SCREEN
        if (player.getHp() <= 0) {
        playerController = false;
        player.setPlayerController(false);
        g2d.drawImage(gameOverScreen, 0, 0, this);
    }


        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }



    private class KeyboardAdapter extends KeyAdapter {

        @Override
        public void keyPressed (KeyEvent e) {

            try {
                player.keyPressed(e);

                // in the beginning of the exercises.holyMoly.game
                if (!gameStart) {
                    int code = e.getKeyCode();

                    if (code == KeyEvent.VK_ENTER) {        // if the user presses 'Enter'
                        gameStart = true;                   // the exercises.holyMoly.game starts
                        playerController = true;            // and the user can control the character
                        player.setPlayerController(true);
                    }
                }


            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }

        @Override
        public void keyReleased (KeyEvent e) {

            player.keyReleased(e);
        }
    }
}