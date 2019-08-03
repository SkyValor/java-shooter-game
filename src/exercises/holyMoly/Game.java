package exercises.holyMoly;

import exercises.holyMoly.entities.destructable.Crate;
import exercises.holyMoly.entities.destructable.enemy.Enemy;
import exercises.holyMoly.entities.destructable.enemy.EnemyBullet;
import exercises.holyMoly.entities.destructable.enemy.boss.Boss;
import exercises.holyMoly.entities.destructable.enemy.sentry.Sentry;
import exercises.holyMoly.entities.player.Player;
import exercises.holyMoly.entities.player.bullets.BazookaBeam;
import exercises.holyMoly.entities.player.bullets.MachineGunBeam;
import exercises.holyMoly.entities.player.bullets.MegaphoneBeam;
import exercises.holyMoly.utils.CollisionDetection;
import exercises.holyMoly.utils.managers.*;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.util.*;
import java.util.List;

public class Game extends JPanel implements ActionListener {

    private Player player;
    private boolean playerController;
    private Background background;

    private Boss boss;
    private List<Enemy> enemies;
    private List<Wall> walls;
    private List<Crate> crates;
    private List<Pickable> pickables;
    private List<Spikes> spikes;

    private int room;

    private boolean isPlayerLastRoom;
    private int bossShootingCount;

    private int score;

    private Image beginningScreen;
    private Image winningScreen;
    private Image gameOverScreen;
    private boolean gameStart;

    private Timer timer;
    private boolean pause;
    private int currentTag, nextTag;


    Game(){
        super.setFocusable(true);
        super.setDoubleBuffered(true);
        super.addKeyListener(new KeyboardAdapter());

        background = new Background();
        player = new Player (35,470, Direction.RIGHT);
        playerController = false;

        //score = 0;
        //isPlayerLastRoom = false;
        //bossShootingCount = 0;

        /*
        ImageIcon WinIcon = new ImageIcon("src//Img//screens//vict2.png");
        winningScreen = WinIcon.getImage();

        ImageIcon LoseIcon = new ImageIcon("src//Img//screens//fail2.png");
        gameOverScreen = LoseIcon.getImage();

        ImageIcon StartIcon = new ImageIcon("src//Img//screens//start2.png");
        beginningScreen = StartIcon.getImage();
        */

        gameStart = false;
        player.setPlayerController(false);


        timer = new Timer(5, this);
        timer.start();

        pause = false;
        currentTag = 1;
        nextTag = 0;
        updateObjectsByTag(currentTag);

        initializeManagers();
    }

    /**
     * Initializes the enemy managers, to populate their lists with all the enemies in the game.
     */
    private void initializeManagers() {

        SentryManager.init();
        TurretManager.init();
        WallManager.init();
        CrateManager.init();
        SpikesManager.init();
        PickableManager.init();
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



    // method for managing the AI for Boss
    private void managerAIBoss() {

        // happens until BOSS starts

        if (!boss.getStartingActive()) {

            // if Player shoots , BOSS starts
            if (player.getList_MG().size() > 0 || player.getList_Beams().size() > 0 || player.getList_Missiles().size() > 0) {
                boss.start();
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
                    boss.teleportToNewWayPoint();           // teleport to new way-point
                    boss.waitHalfSecond();                  // wait 0.5 seconds

                } else {                                      // after waiting 0.5 seconds
                    boss.shoot();                               // BOSS shoots once
                    boss.wait2Seconds();                        // waits 2 seconds
                }

            } else {                                // if HP is 50% or lower

                if (!boss.getPastTeleport()) {          // if Teleport is yet to happen
                    boss.teleportToNewWayPoint();           // teleport to new way-point
                    boss.setPastTeleport(true);             // make sure Teleport does not happen until next cycle

                } else if (bossShootingCount < 3) {       // if 3 shootings haven't happened yet
                    boss.shoot();                           // BOSS shoots
                    bossShootingCount++;                    // counter increments
                    boss.waitHalfSecond();                  // wait 0.5 seconds

                } else {                                  // after the first two waves
                    // BOSS shoots
                    boss.wait2Seconds();                    // wait 2 seconds
                    boss.setPastTeleport(false);            // Teleport can now happen
                    bossShootingCount = 0;                  // reset the counter
                }
            }
        }
    }


    private void gameLoop() {

        if (!player.getBullets().isEmpty()) {

            CollisionDetection.playerBulletCollisionWithWalls(player.getBullets(), walls);
            CollisionDetection.playerBulletCollisionWithCrates(player.getBullets(), crates);
            CollisionDetection.playerBulletCollisionWithEnemy(player.getBullets(), enemies);
        }


        checkPlayerSpikesCollision();
        checkPlayerPickableCollision();
        checkMapTransition();

        if (isPlayerLastRoom && boss.getHP() > 0) {
            managerAIBoss();
        }

        if (!pause) {

            if (playerController) {

                if (isMovementPossible()) {
                    player.move();
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

        // SPIKES
        for (exercises.holyMoly.game.Spikes spikes : list_of_spikes) {

            if (spikes.isVisible()) {
                g2d.drawImage(spikes.getImage(), spikes.getX(), spikes.getY(), this);
            }
        }

        // CRATES
        for (exercises.holyMoly.game.Crate crate : list_of_crates) {
            g2d.drawImage(crate.getImage(), crate.getX(), crate.getY(), this);
        }


        // PICKABLES
        for (exercises.holyMoly.game.Pickable pickable : list_of_pickables) {
            g2d.drawImage(pickable.getImage(), pickable.getX(), pickable.getY(), this);
        }

        // PLAYER
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
        if (boss != null) {
            g2d.drawImage(boss.getImage(), boss.getX(), boss.getY(), this);
        }

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