package exercises.holyMoly.game;


import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MegaphoneBeam extends Thread {

    private int x, y;
    private final int BULLET_SPEED;
    private String direction;
    private Image image;
    private Rectangle rectangle;

    public MegaphoneBeam(String _direction, int player_x, int player_y) {

        this.direction = _direction;

        BULLET_SPEED = 3;

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

        ImageIcon imgIcon;
        if ( (direction.equals("Up")) || (direction.equals("Down")) ) {
             imgIcon = new ImageIcon("src//Img//WaterGunBeam_vertical.png");

        } else {
             imgIcon = new ImageIcon("src//Img//WaterGunBeam_horizontal.png");

        }
        image = imgIcon.getImage();
        rectangle = new Rectangle(this.x, this.y, imgIcon.getIconWidth(), imgIcon.getIconHeight());
    }

    public int getX() {return this.x;}
    public int getY() {return this.y;}
    public Image getImage() {return this.image;}

    public Rectangle getRectangle() {
        return rectangle;
    }

    public boolean move(String direction) {

        switch (direction) {

            case "Up":
                this.y -= BULLET_SPEED;
                this.rectangle.translate(0, -10);
                break;

            case "Down":
                this.y += BULLET_SPEED;
                this.rectangle.translate(0, 1);
                break;

            case "Right":
                this.x += BULLET_SPEED;
                this.rectangle.translate(2, 0);
                break;

            default:
                this.x -= BULLET_SPEED;
                this.rectangle.translate(-3, 0);
        }
        return true;
    }

    @Override
    public void run() {

        switch (direction) {

            case "Up":
                while (y >= 0) {
                    this.move(direction);

                    try {
                        this.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MegaphoneBeam.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

            case "Down":
                while (y <= 1080) {
                    this.move(direction);

                    try {
                        this.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MegaphoneBeam.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

            case "Right":
                while (x <= 1920) {
                    this.move(direction);

                    try {
                        this.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MegaphoneBeam.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

            default:
                while (x >= 0) {
                    this.move(direction);

                    try {
                        this.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MegaphoneBeam.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        }

        System.out.println("Exit Thread.");
    }
}