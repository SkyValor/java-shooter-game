package exercises.holyMoly.game;

import javax.swing.*;
import java.awt.*;

public class Background {
    private int x, y;
    private int width, height;
    private Image image;


    public Background() {
        ImageIcon imgIcon = new ImageIcon ("src//Img//Map_HolyMoly.png");
        image = imgIcon.getImage();

        this.width = imgIcon.getIconWidth();
        this.height = imgIcon.getIconHeight();

        this.x = 0;
        this.y = -(height / 2);
    }


    public int getX(){return this.x; }
    public int getY () {return this.y;}
    public int getWidth() {return this.width;}
    public int getHeight() {return this.height;}
    public Image getImg () {return this.image;}

    public void push(int _x, int _y) {this.x += _x; this.y += _y;}
}
