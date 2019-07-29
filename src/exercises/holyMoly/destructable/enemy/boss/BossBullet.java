package exercises.holyMoly.destructable.enemy.boss;

import exercises.holyMoly.destructable.enemy.AbstractEnemyBullet;

import javax.swing.*;
import java.awt.*;

public class BossBullet extends AbstractEnemyBullet {

    // Constructor for Boss-type
    AbstractEnemyBullet(int enemy_x, int enemy_y, String _direction, int hp, int numb) {
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


        image = imgIcon.getImage();
        this.direction = _direction;
        collider = new Rectangle(enemy_x, enemy_y, imgIcon.getIconWidth(), imgIcon.getIconHeight());
    }
}
