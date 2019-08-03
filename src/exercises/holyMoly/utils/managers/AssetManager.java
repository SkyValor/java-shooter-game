package exercises.holyMoly.utils.managers;

import exercises.holyMoly.Direction;
import exercises.holyMoly.PickableType;
import exercises.holyMoly.entities.Alignment;
import exercises.holyMoly.entities.destructable.enemy.boss.BossHealth;
import exercises.holyMoly.entities.destructable.enemy.sentry.Sentry;
import exercises.holyMoly.entities.destructable.enemy.turret.Turret;
import exercises.holyMoly.entities.destructable.enemy.sentry.SentryBullet;
import exercises.holyMoly.entities.destructable.enemy.turret.TurretBullet;
import exercises.holyMoly.entities.destructable.enemy.boss.Boss;
import exercises.holyMoly.entities.player.Player;
import exercises.holyMoly.entities.player.WeaponType;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code AssetManager} class is called whenever assets need to be loaded into the game. The files are all
 * stored inside the {@code resources} directory.
 */
public class AssetManager {

    /**
     * Loads and returns the {@link Image} for the {@link Player}, depending on the specified {@link Direction} and
     * {@link WeaponType}, received as arguments.
     *
     * @param direction the direction the unit is facing
     * @param weaponType the weapon type currently selected
     * @return the image
     * @see Direction#toString()
     * @see WeaponType#toString()
     */
    public static Image loadPlayerImage(Direction direction, WeaponType weaponType) {

        String filePath = "player/player_" + direction.toString() + "_" + weaponType.toString() + ".png";
        return fetchImage(filePath);
    }

    public static Image loadPlayerMachineGunBulletImage() {

        String filePath = "player/machinegunBullet.png";
        return fetchImage(filePath);
    }

    public static Image loadPlayerBazookaBulletImage(Direction direction) {

        String filePath = "player/bazookaBullet_" + direction.toString() + ".png";
        return fetchImage(filePath);
    }

    public static Image loadPlayerMegaphoneBulletImage(Alignment alignment) {

        String filePath = "player/megaphoneBullet_" + alignment.toString() + ".png";
        return fetchImage(filePath);
    }

    /**
     * Loads and returns the {@link Image} for the {@link Sentry}, depending on the specified {@link Direction}
     * and {@code slowSpeed}, received as arguments.
     *
     * @param direction the direction the unit is facing
     * @param slowSpeed the slow speed
     * @return the image
     * @see Direction#toString()
     */
    public static Image loadSentryImage(Direction direction, int slowSpeed) {

        String filePath = "enemies/sentry/" + direction.toString() + "_";
        filePath += slowSpeed == 1 ? "normal" : "slow";
        filePath += ".png";

        return fetchImage(filePath);
    }

    /**
     * Loads and returns the {@link Image} for the {@link SentryBullet}, depending on the specified {@link Alignment},
     * received as argument.
     *
     * @param alignment the alignment of the sentry
     * @return the image
     * @see Alignment#toString()
     */
    public static Image loadSentryBulletImage(Alignment alignment) {

        String filePath = "enemies/sentryBullet_/" + alignment.toString() + ".png";
        return fetchImage(filePath);
    }

    /**
     * Loads and returns the {@link Image} for the {@link Turret}, depending on the specified {@link Alignment} and
     * {@code slowSpeed}, received as arguments.
     *
     * @param alignment the alignment of the turret
     * @param slowSpeed the slow state of the turret
     * @return the image
     * @see Alignment#toString()
     */
    public static Image loadTurretImage(Alignment alignment, int slowSpeed) {

        String filePath = "enemies/turret/" + alignment.toString() + "_";
        filePath += slowSpeed == 1 ? "normal" : "slow";
        filePath += ".png";

        return fetchImage(filePath);
    }

    /**
     * Loads and returns the {@link Image} for the {@link TurretBullet}.
     *
     * @return the image
     */
    public static Image loadTurretBulletImage() {

        String filePath = "enemies/sentry/omniBullet.png";
        return fetchImage(filePath);
    }

    /**
     * Loads and returns the {@link Image} for the {@link Boss}, depending on the specified {@link BossHealth}
     * received as argument.
     *
     * @param health the boss health
     * @return the image
     * @see BossHealth#toString()
     */
    public static Image loadBossImage(BossHealth health) {

        String filePath = "enemies/boss/boss_" + health.toString() + ".png";
        return fetchImage(filePath);
    }

    public static Image loadBossBulletImage(Direction direction, BossHealth health) {

        String filePath = "enemies/boss/bossBullet_" +  direction.toString() + "_";
        filePath += (health.equals(BossHealth.HIGH) || health.equals(BossHealth.MEDIUM)) ? "strong" : "weak";
        filePath += ".png";

        return fetchImage(filePath);
    }

    public static Image loadSpikesImage() {

        String filePath = "spikes.png";
        return fetchImage(filePath);
    }

    public static Image loadPickableImage(PickableType pickableType) {

        String filePath = "pickables/" + pickableType.toString() + ".png";
        return fetchImage(filePath);
    }

    private static Image fetchImage(String filePath) {

        ImageIcon imgIcon = new ImageIcon(filePath);
        return imgIcon.getImage();
    }
}
