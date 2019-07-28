package game;

import javax.swing.*;
import java.awt.*;

public class EnemyBullet {

    private int x, y;                       // BULLET's position variables
    private String direction;               // tells the direction the BULLET is facing
    private final int BULLET_SPEED = 5;     // constant movement speed for BULLET

    private Image bullet_image;             // BULLET's image
    private Rectangle rectangle;            // Rectangle for collision



    // Constructor for Minion-type
    public EnemyBullet(int enemy_x, int enemy_y, String _direction) {

        // choosing the correct Bullet's image
        ImageIcon imgIcon;

        switch (_direction) {    // depending on the direction the ENEMY is facing, the X and Y of the bullet at the start
            // when being shot needs to be tweaked
            case "Up":
                this.x = enemy_x + 35;
                this.y = enemy_y;
                break;

            case "Down":
                this.x = enemy_x + 35;
                this.y = enemy_y + 70;
                break;

            case "Right":
                this.x = enemy_x + 70;
                this.y = enemy_y + 35;
                break;

            default:
                this.x = enemy_x;
                this.y = enemy_y + 35;
        }

        // if direction is 'Up' or 'Down' , the image is vertical
        // if direction is 'Right' or 'Left' , the image is horizontal
        if (_direction.equals("Up") || _direction.equals("Down")) {

            imgIcon = new ImageIcon("src//Img//EnemyVertBullet.png");
            bullet_image = imgIcon.getImage();
        }

        else {

            imgIcon = new ImageIcon("src//Img//EnemyHorizBullet.png");
            bullet_image = imgIcon.getImage();
        }


        rectangle = new Rectangle(x, y, imgIcon.getIconWidth(), imgIcon.getIconHeight());
        this.direction = _direction;
    }


    // Constructor for Sentry-type
    public EnemyBullet(int enemy_x, int enemy_y, String _direction, String vertHoriz, int numb) {

        // the bullets are omnidirectional , so the direction doesn't matter
        ImageIcon imgIcon = new ImageIcon("src//Img//EnemyOmniBullet.png");
        bullet_image = imgIcon.getImage();

        rectangle = new Rectangle(enemy_x, enemy_y, 20, 20);


        if (vertHoriz.equals("Vertical")) {
            switch (_direction) {    // depending on the direction the ENEMY is facing, the X and Y of the bullet at the start
                // when being shot needs to be tweaked

                case "Up":
                    if (numb == 1) {
                        this.x = enemy_x;
                        this.y = enemy_y - 5;
                        this.direction = "Up";
                        break;
                    }

                    else {
                        this.x = enemy_x + 25;
                        this.y = enemy_y - 5;
                        this.direction = "Up";
                        break;
                    }


                case "Right":
                    if (numb == 1) {
                        this.x = enemy_x + imgIcon.getIconWidth() + 5;
                        this.y = enemy_y - 5;
                        this.direction = "Right";
                        break;
                    }

                    else if (numb == 2) {
                        this.x = enemy_x + imgIcon.getIconWidth() + 5;
                        this.y = enemy_y + 19;
                        this.direction = "Right";
                        break;
                    }

                    else {
                        this.x = enemy_x + imgIcon.getIconWidth() + 5;
                        this.y = enemy_y + 42;
                        this.direction = "Right";
                        break;
                    }


                case "Down":
                    if (numb == 1) {
                        this.x = enemy_x;
                        this.y = enemy_y + imgIcon.getIconHeight() + 5;
                        this.direction = "Down";
                        break;
                    }

                    else {
                        this.x = enemy_x + 25;
                        this.y = enemy_y + imgIcon.getIconHeight() + 5;
                        this.direction = "Down";
                        break;
                    }


                default:
                    if (numb == 1) {
                        this.x = enemy_x - 5;
                        this.y = enemy_y - 5;
                        this.direction = "Left";
                        break;
                    }

                    else if (numb == 2) {
                        this.x = enemy_x - 5;
                        this.y = enemy_y + 19;
                        this.direction = "Left";
                        break;
                    }

                    else {
                        this.x = enemy_x - 5;
                        this.y = enemy_y + 42;
                        this.direction = "Left";
                        break;
                    }
            }
        }

        // else , the Sentry is Horizontal
        else {
            switch (_direction) {    // depending on the direction the ENEMY is facing, the X and Y of the bullet at the start
                // when being shot needs to be tweaked

                case "Up":
                    if (numb == 1) {
                        this.x = enemy_x - 5;
                        this.y = enemy_y - 5;
                        this.direction = "Up";
                        break;
                    }

                    else if (numb == 2) {
                        this.x = enemy_x + 19;
                        this.y = enemy_y - 5;
                        this.direction = "Up";
                        break;
                    }

                    else {
                        this.x = enemy_x + 42;
                        this.y = enemy_y - 5;
                        this.direction = "Up";
                        break;
                    }


                case "Right":
                    if (numb == 1) {
                        this.x = enemy_x + imgIcon.getIconWidth() + 5;
                        this.y = enemy_y;
                        this.direction = "Right";
                        break;
                    }

                    else {
                        this.x = enemy_x + imgIcon.getIconWidth() + 5;
                        this.y = enemy_y + 25;
                        this.direction = "Right";
                        break;
                    }


                case "Down":
                    if (numb == 1) {
                        this.x = enemy_x - 5;
                        this.y = enemy_y + imgIcon.getIconHeight() + 5;
                        this.direction = "Down";
                        break;
                    }

                    else if (numb == 2) {
                        this.x = enemy_x + 19;
                        this.y = enemy_y + imgIcon.getIconHeight() + 5;
                        this.direction = "Down";
                        break;
                    }

                    else {
                        this.x = enemy_x + 42;
                        this.y = enemy_y + imgIcon.getIconHeight() + 5;
                        this.direction = "Down";
                        break;
                    }


                default:
                    if (numb == 1) {
                        this.x = enemy_x - 5;
                        this.y = enemy_y;
                        this.direction = "Left";
                        break;
                    }

                    else {
                        this.x = enemy_x - 5;
                        this.y = enemy_y + 25;
                        this.direction = "Left";
                        break;
                    }
            }
        }
    }


    // Constructor for Boss-type
    public EnemyBullet(int enemy_x, int enemy_y, String _direction, int hp, int numb) {
        ImageIcon imgIcon;

        switch (_direction) {

            case "Up":
                if (hp > 1000)
                    imgIcon = new ImageIcon("src//Img//BossWeak_Up.png");
                else
                    imgIcon = new ImageIcon("src//Img//BossStrong_Up.png");


                if (numb == 1) {
                    this.x = enemy_x - 1;
                    this.y = enemy_y - (imgIcon.getIconHeight() / 2);
                }

                else if (numb == 2) {
                    this.x = enemy_x + 22;
                    this.y = enemy_y - (imgIcon.getIconHeight() / 2);
                }

                else if (numb == 3) {
                    this.x = enemy_x + 45;
                    this.y = enemy_y - (imgIcon.getIconHeight() / 2);
                }

                else {
                    this.x = enemy_x + 68;
                    this.y = enemy_y - (imgIcon.getIconHeight() / 2);
                }
                break;


            case "Right":
                if (hp > 1000)
                    imgIcon = new ImageIcon("src//Img//BossWeak_Right.png");
                else
                    imgIcon = new ImageIcon("src//Img//BossStrong_Right.png");


                if (numb == 1) {
                    this.x = enemy_x + ((imgIcon.getIconWidth() / 2)+50);
                    this.y = enemy_y - 1;
                }

                else if (numb == 2) {
                    this.x = enemy_x + ((imgIcon.getIconWidth() / 2)+50);
                    this.y = enemy_y + 22;
                }

                else if (numb == 3) {
                    this.x = enemy_x + ((imgIcon.getIconWidth() / 2)+50);
                    this.y = enemy_y + 45;
                }

                else {
                    this.x = enemy_x + ((imgIcon.getIconWidth() / 2)+50);
                    this.y = enemy_y + 68;
                }
                break;


            case "Down":
                if (hp > 1000)
                    imgIcon = new ImageIcon("src//Img//BossWeak_Down.png");
                else
                    imgIcon = new ImageIcon("src//Img//BossStrong_Down.png");


                if (numb == 1) {
                    this.x = enemy_x - 1;
                    this.y = enemy_y + ((imgIcon.getIconWidth() / 2)+50);
                }

                else if (numb == 2) {
                    this.x = enemy_x + 22;
                    this.y = enemy_y + ((imgIcon.getIconWidth() / 2)+50);
                }

                else if (numb == 3) {
                    this.x = enemy_x + 45;
                    this.y = enemy_y + ((imgIcon.getIconWidth() / 2)+50);
                }

                else {
                    this.x = enemy_x + 68;
                    this.y = enemy_y + ((imgIcon.getIconWidth() / 2)+50);
                }
                break;


            default:
                if (hp > 1000)
                    imgIcon = new ImageIcon("src//Img//BossWeak_Left.png");
                else
                    imgIcon = new ImageIcon("src//Img//BossStrong_Left.png");


                if (numb == 1) {
                    this.x = enemy_x - (imgIcon.getIconWidth() / 2);
                    this.y = enemy_y - 1;
                }

                else if (numb == 2) {
                    this.x = enemy_x - (imgIcon.getIconWidth() / 2);
                    this.y = enemy_y + 22;
                }

                else if (numb == 3) {
                    this.x = enemy_x - (imgIcon.getIconWidth() / 2);
                    this.y = enemy_y + 45;
                }

                else {
                    this.x = enemy_x - (imgIcon.getIconWidth() / 2);
                    this.y = enemy_y + 68;
                }

                break;
        }


        bullet_image = imgIcon.getImage();
        this.direction = _direction;
        rectangle = new Rectangle(enemy_x, enemy_y, imgIcon.getIconWidth(), imgIcon.getIconHeight());
    }


    public int getX() {return x;}
    public int getY() {return y;}
    public Image getImage()     {return this.bullet_image;}

    public Rectangle getRectangle() {return rectangle;}

    public void move() {

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
    }
}
