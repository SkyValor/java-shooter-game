package exercises.holyMoly.game;


import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Wall {

    private  int TAG;

    private int x, y;


    private Rectangle2D rectangle;

    public Wall(int _x, int _y, int _width, int _height, int _tag) {

        TAG = _tag;

        this.x = _x;
        this.y = _y;

        rectangle = new Rectangle(_x, _y, _width, _height);
    }

    public int getTAG() {return this.TAG;}
    public int getX() {return this.x;}
    public int getY() {return this.y;}
    public Rectangle2D getRectangle() {return this.rectangle;}
}
