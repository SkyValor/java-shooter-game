package exercises.holyMoly.entities.destructable.enemy.boss;

import exercises.holyMoly.Direction;
import exercises.holyMoly.entities.destructable.enemy.AbstractEnemyBullet;
import exercises.holyMoly.entities.destructable.enemy.EnemyBullet;
import exercises.holyMoly.entities.destructable.enemy.EnemyType;
import exercises.holyMoly.utils.managers.AssetManager;

import javax.swing.*;
import java.awt.*;

/**
 * The specification of an {@link EnemyBullet} for the {@link Boss}.
 */
class BossBullet extends AbstractEnemyBullet {

    BossBullet(int positionX, int positionY, Direction direction, int hp, int numb) {
        super(positionX, positionY, direction, EnemyType.BOSS);


        ImageIcon imgIcon;

        switch (direction) {

            case "Up":
                if (hp > 1000)
                    imgIcon = new ImageIcon("src//Img//BossWeak_Up.png");
                else
                    imgIcon = new ImageIcon("src//Img//BossStrong_Up.png");


                if (numb == 1) {
                    this.x = positionX - 1;
                    this.y = positionY - (imgIcon.getIconHeight() / 2);
                }

                else if (numb == 2) {
                    this.x = positionX + 22;
                    this.y = positionY - (imgIcon.getIconHeight() / 2);
                }

                else if (numb == 3) {
                    this.x = positionX + 45;
                    this.y = positionY - (imgIcon.getIconHeight() / 2);
                }

                else {
                    this.x = positionX + 68;
                    this.y = positionY - (imgIcon.getIconHeight() / 2);
                }
                break;


            case "Right":
                if (hp > 1000)
                    imgIcon = new ImageIcon("src//Img//BossWeak_Right.png");
                else
                    imgIcon = new ImageIcon("src//Img//BossStrong_Right.png");


                if (numb == 1) {
                    this.x = positionX + ((imgIcon.getIconWidth() / 2)+50);
                    this.y = positionY - 1;
                }

                else if (numb == 2) {
                    this.x = positionX + ((imgIcon.getIconWidth() / 2)+50);
                    this.y = positionY + 22;
                }

                else if (numb == 3) {
                    this.x = positionX + ((imgIcon.getIconWidth() / 2)+50);
                    this.y = positionY + 45;
                }

                else {
                    this.x = positionX + ((imgIcon.getIconWidth() / 2)+50);
                    this.y = positionY + 68;
                }
                break;


            case "Down":
                if (hp > 1000)
                    imgIcon = new ImageIcon("src//Img//BossWeak_Down.png");
                else
                    imgIcon = new ImageIcon("src//Img//BossStrong_Down.png");


                if (numb == 1) {
                    this.x = positionX - 1;
                    this.y = positionY + ((imgIcon.getIconWidth() / 2)+50);
                }

                else if (numb == 2) {
                    this.x = positionX + 22;
                    this.y = positionY + ((imgIcon.getIconWidth() / 2)+50);
                }

                else if (numb == 3) {
                    this.x = positionX + 45;
                    this.y = positionY + ((imgIcon.getIconWidth() / 2)+50);
                }

                else {
                    this.x = positionX + 68;
                    this.y = positionY + ((imgIcon.getIconWidth() / 2)+50);
                }
                break;


            default:
                if (hp > 1000)
                    imgIcon = new ImageIcon("src//Img//BossWeak_Left.png");
                else
                    imgIcon = new ImageIcon("src//Img//BossStrong_Left.png");


                if (numb == 1) {
                    this.x = positionX - (imgIcon.getIconWidth() / 2);
                    this.y = positionY - 1;
                }

                else if (numb == 2) {
                    this.x = positionX - (imgIcon.getIconWidth() / 2);
                    this.y = positionY + 22;
                }

                else if (numb == 3) {
                    this.x = positionX - (imgIcon.getIconWidth() / 2);
                    this.y = positionY + 45;
                }

                else {
                    this.x = positionX - (imgIcon.getIconWidth() / 2);
                    this.y = positionY + 68;
                }

                break;
        }


        image = imgIcon.getImage();
        this.direction = direction;
        collider = new Rectangle(positionX, positionY, imgIcon.getIconWidth(), imgIcon.getIconHeight());
    }

    /**
     * Sets the {@link Image} of this bullet to be the {@code image} received by calling the {@link AssetManager},
     * passing the current {@link BossHealth}, received as argument.
     *
     * @param health the boss health state
     */
    void setImage(BossHealth health) {
        setImage(AssetManager.loadBossImage(health));
    }
}
