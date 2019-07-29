package exercises.holyMoly.game;

import javax.swing.*;

import java.awt.*;

public class Pickable {

    private final int TAG;

    private int x, y;
    private Image image;
    private Rectangle rectangle;
    private String type;



    public Pickable(String _type, int _x, int _y, int _tag) {

        TAG = _tag;

        this.x = _x;
        this.y = _y;
        this.type = _type;

        ImageIcon imgIcon;
        switch (_type) {

            case "Cross":
                imgIcon = new ImageIcon("src//Img//cross.png");
                break;

            case "Megaphone":
                imgIcon = new ImageIcon("src//Img//mega.png");
                break;

            case "Battery":
                imgIcon = new ImageIcon("src//Img//battery.png");
                break;

            case "Bazooka":
                imgIcon = new ImageIcon("src//Img//bazooka.png");
                break;

            default:
                imgIcon = new ImageIcon("src//Img//rocket.png");
        }

        image = imgIcon.getImage();
        rectangle = new Rectangle(_x, _y, imgIcon.getIconWidth(), imgIcon.getIconHeight());
    }


    public int getTAG() {return this.TAG;}
    public int getX() {return x;}
    public int getY() {return y;}
    public Image getImage() {return image;}

    public Rectangle getRectangle() {return rectangle;}
    public String getType() {return type;}
}

