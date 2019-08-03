package exercises.holyMoly.entities.destructable;

import javax.swing.*;
import java.awt.*;

public class Crate implements Destructable {

    private int tag;
    private int x, y;
    private Image image;
    private Rectangle rectangle;
    private int hp;

    public Crate(int _x, int _y, int _tag) {

        this.tag = _tag;

        this.x = _x;
        this.y = _y;
        hp = 75;

        ImageIcon imgIcon = new ImageIcon("src//Img//box.png");
        this.image = imgIcon.getImage();

        rectangle = new Rectangle(x, y, 37, 39);
    }

    public int getTag() {return this.tag;}

    public int getX() {return this.x;}
    public int getY() {return this.y;}
    public Image getImage() {return this.image;}

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setHP(int sumDif) {
        this.hp += sumDif;
    }

    public int getHP() {return hp;}

    @Override
    public void destroy() {

    }

    @Override
    public boolean isDestroyed() {
        return false;
    }
}
