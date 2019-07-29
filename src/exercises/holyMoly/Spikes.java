package exercises.holyMoly.game;

import javax.swing.*;
import java.awt.*;

public class Spikes {

    private final int TAG;

    private int x, y;
    private Image image;
    private Rectangle rectangle;
    private boolean isVisible;



    public Spikes(int _x, int _y, int _tag) {

        this.TAG = _tag;

        this.x = _x;
        this.y = _y;
        this.isVisible = false;

        ImageIcon imgIcon = new ImageIcon("src//Img//spickes.png");
        image = imgIcon.getImage();

        rectangle = new Rectangle(_x, _y, imgIcon.getIconWidth(), imgIcon.getIconHeight());
    }


    public int getTAG() {return TAG;}

    public int getX() {return x;}
    public int getY() {return y;}
    public Image getImage() {return image;}

    public Rectangle getRectangle() {return rectangle;}

    public boolean getVisible() {return isVisible;}
    public void setVisible() {this.isVisible = true;}
}
