package exercises.holyMoly.entities.player.bullets;


import exercises.holyMoly.Direction;
import exercises.holyMoly.entities.BulletType;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MegaphoneBeam extends AbstractPlayerBullet {

    public MegaphoneBeam(int positionX, int positionY, Direction direction) {
        super(positionX, positionY, direction, BulletType.MEGAPHONE);
    }
}