package exercises.holyMoly.game;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BazookaBeam extends Thread{

    private int x, y;
    private final int BULLET_SPEED;
    private String direction;
    private Image image;
    private Rectangle rectangle;

    public BazookaBeam(String _direction, int player_x, int player_y) {

        this.direction = _direction;

        BULLET_SPEED = 3;

        ImageIcon imgIcon;
        switch (direction) {

            case "Up":
                this.x = player_x + 41;
                this.y = player_y;

                imgIcon = new ImageIcon("src//Img//WaterBazookaBeam_Up.png");

                break;

            case "Down":
                this.x = player_x + 28;
                this.y = player_y + 70;

                imgIcon = new ImageIcon("src//Img//WaterBazookaBeam_Down.png");
                break;

            case "Right":
                this.x = player_x + 70;
                this.y = player_y + 41;

                imgIcon = new ImageIcon("src//Img//WaterBazookaBeam_Right.png");
                break;

            default:
                this.x = player_x;
                this.y = player_y + 28;

                imgIcon = new ImageIcon("src//Img//WaterBazookaBeam_Left.png");
        }
        image = imgIcon.getImage();
        rectangle = new Rectangle(this.x, this.y, imgIcon.getIconWidth(), imgIcon.getIconHeight());
    }


    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public Image getImage() { return this.image; }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public boolean move() {

        switch (direction) {

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
                while (y >= 0) {
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
                while (x >= 0) {
                    this.move();

                    try {
                        this.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(BazookaBeam.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        }

        System.out.println("Exit Thread.");
    }
}