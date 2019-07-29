package exercises.holyMoly.utils;

import exercises.holyMoly.Direction;
import exercises.holyMoly.destructable.enemy.sentry.Sentry;
import exercises.holyMoly.destructable.enemy.turret.Turret;
import exercises.holyMoly.destructable.enemy.turret.TurretType;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code AssetManager} class is called whenever assets need to be loaded into the game. The files are all
 * stored inside the {@code resources} directory.
 */
public class AssetManager {

    /**
     * Loads and returns the {@link Image} for the {@link Sentry}, depending on the specified {@code direction}
     * and {@code slowSpeed}, received as arguments.
     *
     * @param direction the direction the unit is facing
     * @param slowSpeed the slow speed
     * @return the image
     */
    public static Image loadSentryImage(Direction direction, int slowSpeed) {

        String filePath = "enemies/sentry/" + direction.toString() + "_";
        filePath += slowSpeed == 1 ? "normal" : "slow";
        filePath += ".png";

        ImageIcon imgIcon = new ImageIcon(filePath);

        return imgIcon.getImage();
    }

    /**
     * Loads and returns the {@link Image} for the {@link Turret}, depending on the specified {@code turretType} and
     * {@code slowSpeed}, received as arguments.
     *
     * @param turretType the type of turret
     * @param slowSpeed the slow sleep
     * @return the image
     */
    public static Image loadTurretImage(TurretType turretType, int slowSpeed) {

        String filePath = "enemies/turret/" + turretType.toString() + "_";
        filePath += slowSpeed == 1 ? "normal" : "slow";
        filePath += ".png";

        ImageIcon imgIcon = new ImageIcon(filePath);

        return imgIcon.getImage();
    }

    /**
     * Loads and returns the {@link Image} for the {@link exercises.holyMoly.destructable.enemy.sentry.SentryBullet},
     * depending on the specified {@code direction}, received as argument.
     *
     * @param direction the direction the unit is facing
     * @return the image
     */
    public static Image loadSentryBulletImage(Direction direction) {

        String filePath = "enemies/sentry/";
        filePath += (direction.equals(Direction.UP) || direction.equals(Direction.DOWN)) ? "vertical" : "horizontal";
        filePath += "Bullet.png";

        ImageIcon imgIcon = new ImageIcon(filePath);

        return imgIcon.getImage();
    }

    /**
     * Loads and returns the {@link Image} for the {@link exercises.holyMoly.destructable.enemy.turret.TurretBullet}.
     *
     * @return the image
     */
    public static Image loadTurretBulletImage() {

        String filePath = "enemies/sentry/omniBullet.png";

        ImageIcon imgIcon = new ImageIcon(filePath);

        return imgIcon.getImage();
    }

    // TODO : unfinished
    public static Image loadBossImage(int health) {

        String filePath = "enemies/boss/boss" + health + ".png";
    }
}
