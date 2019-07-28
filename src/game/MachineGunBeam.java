package game;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MachineGunBeam extends Thread {

    private int x, y;
    private final int BULLET_SPEED;
    private String direction;
    private Image image;
    private Rectangle rectangle;

    public MachineGunBeam(String _direction, int player_x, int player_y) {


         direction = _direction ;

        BULLET_SPEED = 5;

        switch (direction) {

            case "Up":
                this.x = player_x + 41;
                this.y = player_y;
                break;

            case "Down":
                this.x = player_x + 28;
                this.y = player_y + 70;
                break;

            case "Right":
                this.x = player_x + 70;
                this.y = player_y + 41;
                break;

            default:
                this.x = player_x;
                this.y = player_y + 28;
        }

        rectangle = new Rectangle(this.x, this.y, 14, 14);

        ImageIcon imgIcon = new ImageIcon("src//Img//WaterMGunBeam.png");
        image = imgIcon.getImage();


    }


    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public Image getImage() { return this.image; }
    public Rectangle getRectangle() {return this.rectangle;}


    public boolean move() {

        switch (this.direction) {

            case "Up":
                this.y -= BULLET_SPEED;
                this.rectangle.translate(0, -BULLET_SPEED);
                break;

            case "Down":
                this.y += BULLET_SPEED;
                this.rectangle.translate(0, BULLET_SPEED);
                break;

            case "Right":
                this.x += BULLET_SPEED;
                this.rectangle.translate(BULLET_SPEED, 0);
                break;

            default:
                this.x -= BULLET_SPEED;
                this.rectangle.translate(-BULLET_SPEED, 0);

        }
        return true;
    }


    @Override
    public void run() {

        switch (direction) {

            case "Up":
                while (y >= -100) {
                    this.move();

                    try {
                        this.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(BazookaBeam.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

            case "Down":
                while (y <= 1080) {
                    this.move();

                    try {
                        this.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(BazookaBeam.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

            case "Right":
                while (x <= 1920) {
                    this.move();

                    try {
                        this.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(BazookaBeam.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

            default:
                while (x >= -100) {
                    this.move();

                    try {
                        this.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(BazookaBeam.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        }
    }
}