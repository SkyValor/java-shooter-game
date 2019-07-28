package game;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Sentry {

    private int x, y;
    private Image image;
    private String direction;
    private String vertHoriz;

    private final int TAG;

    private Rectangle rectangle;
    private int hp;

    private ArrayList<EnemyBullet> List_bullets;
    private boolean bulletInterval;
    private int SLOW_SPEED;
    private boolean playerLockOn;


    public Sentry(int _x, int _y, int _tag, String _vertHoriz) {

        this.x = _x;
        this.y = _y;

        this.SLOW_SPEED = 1;
        hp = 200;

        if (_vertHoriz.equals("Vertical")) {

            ImageIcon imgIcon = new ImageIcon("src//Img//enemy//Sentry_vert.png");
            this.image = imgIcon.getImage();
        }

        else {

            ImageIcon imgIcon = new ImageIcon("src//Img//enemy//Sentry_horiz.png");
            this.image = imgIcon.getImage();
        }

        TAG = _tag;
        vertHoriz = _vertHoriz;

        rectangle = new Rectangle(x, y, 45, 57);
        List_bullets = new ArrayList<>();
        bulletInterval = false;
        playerLockOn = false;
    }

    public int getTAG() {return this.TAG;}
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Image getImage() {
        return image;
    }

    private String getDirection() {return direction;}
    public void setDirection(String direction) {this.direction = direction;}

    private String getVertHoriz() {
        return vertHoriz;
    }

    public Rectangle getRectangle() {return this.rectangle;}
    public ArrayList<EnemyBullet> getList_bullets() {return this.List_bullets;}


    public void setPlayerLockOn(boolean playerLockOn) {
        this.playerLockOn = playerLockOn;
    }

    public int getHP() {return hp;}
    public void setHP(int sumDif) {this.hp += sumDif;}


    // method that gets the PLAYER position and shoots a BULLET to the said position
    public void attackThePlayer(int playerX, int playerY) {

        if (playerLockOn) {

            rotateToPlayer(playerX, playerY);
            shootAtPlayer();
        }
    }


    // method for getting the angle between SENTRY and PLAYER
    private double getAngle(int playerX, int playerY) {

        double angle = Math.toDegrees(Math.atan2( playerY - (this.y + 35), playerX - (this.x + 35)));

        if (angle < 0)
            angle += 360;

        else if (angle > 360)
            angle -= 360;

        return angle;
    }


    // method for rotating direction to face PLAYER
    private void rotateToPlayer(int playerX, int playerY) {

        double angle = getAngle(playerX, playerY);

        if (angle >= 45 && angle < 135)
            setDirection("Down");

        else if (angle >= 135 && angle < 225)
            setDirection("Left");

        else if (angle >= 225 && angle < 315)
            setDirection("Up");

        else
            setDirection("Right");
    }


    // method for updating the SENTRY's image according to its slow-debuff state
    public void updateImageByDebuff() {

        ImageIcon imgIcon;

        if (this.SLOW_SPEED == 1) {
            if (this.vertHoriz.equals("Vertical"))
                imgIcon = new ImageIcon("src//Img//enemy//Sentry_vert.png");

            else
                imgIcon = new ImageIcon("src//Img//enemy//Sentry_horiz.png");
        }

        else {
            if (this.vertHoriz.equals("Vertical"))
                imgIcon = new ImageIcon("src//Img//enemy//Sentry_vert_slow.png");

            else
                imgIcon = new ImageIcon("src//Img//enemy//Sentry_horiz_slow.png");
        }

        this.image = imgIcon.getImage();
    }


    // method for applying Slow-debuff effects
    public void setSlowDebuff() {

        SLOW_SPEED = 2;

        // ENEMY's speed is back to normal after 6 seconds
        new java.util.Timer().schedule(
                new TimerTask() {

                    @Override
                    public void run() { SLOW_SPEED = 1; }
                }, 6000);
    }


    private void shootAtPlayer() {

        if (playerLockOn) {

            // if the weapon in not in cooldown
            if (!bulletInterval) {

                if (this.vertHoriz.equals("Vertical")) {

                    switch (direction) {

                        case "Up":  // shoots 2 bullets

                            List_bullets.add(new EnemyBullet(this.x, this.y, getDirection(), getVertHoriz(), 1));
                            //List_bullets.get(List_bullets.size() - 1).run();

                            List_bullets.add(new EnemyBullet(this.x, this.y, getDirection(), getVertHoriz(), 2));
                            //List_bullets.get(List_bullets.size() - 1).run();

                            break;

                        case "Right":   // shoots 3 bullets

                            List_bullets.add(new EnemyBullet(this.x, this.y, getDirection(), getVertHoriz(), 1));
                            //List_bullets.get(List_bullets.size() - 1).run();

                            List_bullets.add(new EnemyBullet(this.x, this.y, getDirection(), getVertHoriz(), 2));
                            //List_bullets.get(List_bullets.size() - 1).run();

                            List_bullets.add(new EnemyBullet(this.x, this.y, getDirection(), getVertHoriz(), 3));
                            //List_bullets.get(List_bullets.size() - 1).run();

                            break;

                        case "Down":    // shoots 2 bullets

                            List_bullets.add(new EnemyBullet(this.x, this.y, getDirection(), getVertHoriz(), 1));
                            //List_bullets.get(List_bullets.size() - 1).run();

                            List_bullets.add(new EnemyBullet(this.x, this.y, getDirection(), getVertHoriz(), 2));
                            //List_bullets.get(List_bullets.size() - 1).run();

                            break;

                        default:    // shoots 3 bullets
                            List_bullets.add(new EnemyBullet(this.x, this.y, getDirection(), getVertHoriz(), 1));
                            //List_bullets.get(List_bullets.size() - 1).run();

                            List_bullets.add(new EnemyBullet(this.x, this.y, getDirection(), getVertHoriz(), 2));
                            //List_bullets.get(List_bullets.size() - 1).run();

                            List_bullets.add(new EnemyBullet(this.x, this.y, getDirection(), getVertHoriz(), 3));
                            //List_bullets.get(List_bullets.size() - 1).run();
                    }
                }

                // else, it is Horizontal
                else {

                    switch (direction) {

                        case "Up":  // shoots 3 bullets

                            List_bullets.add(new EnemyBullet(this.x, this.y, getDirection(), getVertHoriz(), 1));
                            //List_bullets.get(List_bullets.size() - 1).run();

                            List_bullets.add(new EnemyBullet(this.x, this.y, getDirection(), getVertHoriz(), 2));
                            //List_bullets.get(List_bullets.size() - 1).run();

                            List_bullets.add(new EnemyBullet(this.x, this.y, getDirection(), getVertHoriz(), 3));
                            //List_bullets.get(List_bullets.size() - 1).run();

                            break;

                        case "Right":   // shoots 2 bullets

                            List_bullets.add(new EnemyBullet(this.x, this.y, getDirection(), getVertHoriz(), 1));
                            //List_bullets.get(List_bullets.size() - 1).run();

                            List_bullets.add(new EnemyBullet(this.x, this.y, getDirection(), getVertHoriz(), 2));
                            //List_bullets.get(List_bullets.size() - 1).run();

                            break;

                        case "Down":    // shoots 3 bullets

                            List_bullets.add(new EnemyBullet(this.x, this.y, getDirection(), getVertHoriz(), 1));
                            //List_bullets.get(List_bullets.size() - 1).run();

                            List_bullets.add(new EnemyBullet(this.x, this.y, getDirection(), getVertHoriz(), 2));
                            //List_bullets.get(List_bullets.size() - 1).run();

                            List_bullets.add(new EnemyBullet(this.x, this.y, getDirection(), getVertHoriz(), 3));
                            //List_bullets.get(List_bullets.size() - 1).run();

                            break;

                        default:    // shoots 2 bullets
                            List_bullets.add(new EnemyBullet(this.x, this.y, getDirection(), getVertHoriz(), 1));
                            //List_bullets.get(List_bullets.size() - 1).run();

                            List_bullets.add(new EnemyBullet(this.x, this.y, getDirection(), getVertHoriz(), 2));
                            //List_bullets.get(List_bullets.size() - 1).run();
                    }
                }

                // bulletInterval is false after 2 seconds normally and 4 seconds if slowed
                bulletInterval = !bulletInterval;
                new java.util.Timer().schedule(
                        new TimerTask() {

                            @Override
                            public void run() {bulletInterval = false;}
                        }, 2000 * SLOW_SPEED);

            }
        }
    }
}