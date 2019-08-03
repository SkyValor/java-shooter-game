package exercises.holyMoly.utils;

import exercises.holyMoly.Wall;
import exercises.holyMoly.entities.Bullet;
import exercises.holyMoly.entities.destructable.Crate;
import exercises.holyMoly.entities.destructable.enemy.Enemy;
import exercises.holyMoly.entities.destructable.enemy.sentry.Sentry;
import exercises.holyMoly.entities.player.Player;
import exercises.holyMoly.entities.player.bullets.MachineGunBeam;

import java.util.ArrayList;
import java.util.List;

public class CollisionDetection {

    // TODO: CollisionDetection does not return boolean.
    /*
    | Instead, we call another utils class to handle the events.
    | We save the bullet and item, for example, and call the methods to perform
    | the events on said objects.
    */


    /**
     * Verifies if there is a collision between a {@link Player}'s bullet and a {@link Wall}.
     *
     * @param playerBullets the list of bullets
     * @param walls the list of walls
     * @return true if there is a collision; false otherwise
     */
    public static boolean playerBulletCollisionWithWalls(List<Bullet> playerBullets, List<Wall> walls) {

        for (Bullet bullet : playerBullets) {

            for (Wall wall : walls) {

                if (wall.getCollider().intersects(bullet.getCollider())) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Verifies if there is a collision between a {@link Player}'s bullet and a {@link Crate}.
     *
     * @param playerBullets the list of bullets
     * @param crates the list of crates
     * @return true if there is a collision; false otherwise
     */
    public static boolean playerBulletCollisionWithCrates(List<Bullet> playerBullets, List<Crate> crates) {

        for (Bullet bullet : playerBullets) {

            for (Crate crate : crates) {

                if (crate.getRectangle().intersects(bullet.getCollider())) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Verifies if there is a collision between a {@link Player}'s bullet and an {@link Enemy}.
     *
     * @param playerBullets the list of bullets
     * @param enemies the list of enemies
     * @return true if there is a collision; false otherwise
     */
    public static boolean playerBulletCollisionWithEnemy(List<Bullet> playerBullets, List<Enemy> enemies) {

        for (Bullet bullet : playerBullets) {

            for (Enemy enemy : enemies) {

                if (enemy.getCollider().intersects(bullet.getCollider())) {
                    return true;
                }
            }
        }

        return false;
    }

    // method for collision between bullets and characters
    private void checkBulletCollision() {

        // check all PLAYER's Machine Gun Bullets -- Machine Gun deals 25 damage
        for (MachineGunBeam mg_beam : player.getList_MG()) {

            // against all WALLS
            for (Wall wall : list_of_walls) {
                if (mg_beam.getRectangle().intersects(wall.getRectangle())) {
                    player.getList_MG().remove(mg_beam);                        // eliminate access to bullet
                    return;
                }
            }


            // against all ENEMIES
            Enemy toRemoveEnemy = null;
            for (Enemy enemy : list_of_enemies) {
                if (mg_beam.getRectangle().intersects(enemy.getRectangle())) {
                    enemy.setHP(-25);

                    if (enemy.getHP() <=0)
                        toRemoveEnemy = enemy;

                    player.getList_MG().remove(mg_beam);        // eliminate access to bullet
                    LIST_of_ENEMIES.remove(toRemoveEnemy);
                    list_of_enemies.remove(toRemoveEnemy);
                    return;
                }
            }


            // against all SENTRIES
            Sentry toRemoveSentry = null;            // removes the sentry from list to kill it
            for (Sentry sentry : list_of_sentries) {
                if (mg_beam.getRectangle().intersects(sentry.getRectangle())) {
                    sentry.setHP(-25);

                    // destroy the sentry if HP is 0 or less
                    if (sentry.getHP() <= 0)
                        toRemoveSentry = sentry;

                    player.getList_MG().remove(mg_beam);        // eliminate access to bullet
                    LIST_of_SENTRIES.remove(toRemoveSentry);    // eliminate access to LIST
                    list_of_sentries.remove(toRemoveSentry);    // eliminate access to list
                    return;
                }
            }

            // against the BOSS
            if (mg_beam.getRectangle().intersects(boss.getRectangle())) {
                boss.setHP(-25);

                // destroy the BOSS if HP is 0 or less
                if (boss.getHP() <= 0)
                    boss.eraseBoss();                           // garanties the erasure of Image and Rectangle

                player.getList_MG().remove(mg_beam);            // eliminate access to bullet
                return;
            }

            // against all CRATES
            Crate toRemoveCrate = null;            // removes the crate from list to destroy it
            for (Crate crate : list_of_crates) {
                if (mg_beam.getRectangle().intersects(crate.getRectangle())) {
                    crate.setHP(-25);

                    // destroy the crate if HP is 0 or less
                    if (crate.getHP() <= 0) {
                        toRemoveCrate = crate;

                        spawnRandomPickable(crate.getX(), crate.getY());    // spawn a random Pickable in this position
                    }

                    player.getList_MG().remove(mg_beam);      // eliminate access to bullet
                    LIST_of_CRATES.remove(toRemoveCrate);     // eliminate access to LIST
                    list_of_crates.remove(toRemoveCrate);     // eliminate access to list
                    return;
                }
            }
        }

        // check all PLAYER's Megaphone Bullets -- Megaphone doesn't do damage. Instead, deals slow-debuff
        for (MegaphoneBeam megaBeam : player.getList_Beams()) {

            // against all WALLS
            for (Wall wall : list_of_walls) {
                if (megaBeam.getRectangle().intersects(wall.getRectangle())) {
                    player.getList_Beams().remove(megaBeam);                        // eliminate access to bullet
                    return;
                }
            }

            // against all ENEMIES
            for (Enemy enemy : list_of_enemies) {
                if (megaBeam.getRectangle().intersects(enemy.getRectangle())) {
                    enemy.setSlowDebuff();  // ENEMY gets slowed

                    player.getList_Beams().remove(megaBeam);    // eliminate access to bullet
                    return;
                }
            }

            // against all SENTRIES
            for (Sentry sentry : list_of_sentries) {
                if (megaBeam.getRectangle().intersects(sentry.getRectangle())) {
                    sentry.setSlowDebuff(); // SENTRY gets slowed

                    player.getList_Beams().remove(megaBeam);    // eliminate access to bullet
                    return;
                }
            }

            // against all CRATES
            for (Crate crate : list_of_crates) {
                if (megaBeam.getRectangle().intersects(crate.getRectangle())) {
                    // nothing happens here

                    player.getList_Beams().remove(megaBeam);    // eliminate access to bullet
                    return;
                }
            }
        }

        // checks all PLAYER's Bazooka Bullets -- Bazooka deals 250 damage
        for (BazookaBeam missile : player.getList_Missiles()) {

            // against all WALLS
            for (Wall wall : list_of_walls) {
                if (missile.getRectangle().intersects(wall.getRectangle())) {
                    player.getList_Missiles().remove(missile);                        // eliminate access to bullet
                    return;
                }
            }

            // against all ENEMIES
            Enemy toRemoveMinion = null;            // removes the ENEMY from list to kill it
            for (Enemy enemy : list_of_enemies) {
                if (missile.getRectangle().intersects(enemy.getRectangle())) {
                    enemy.setHP(-250);

                    // destroy the ENEMY if HP is 0 or less
                    if (enemy.getHP() <= 0)
                        toRemoveMinion = enemy;

                    player.getList_Missiles().remove(missile);  // eliminate access to bullet
                    LIST_of_ENEMIES.remove(toRemoveMinion);     // eliminate access to LIST
                    list_of_enemies.remove(toRemoveMinion);     // eliminate access to list
                    return;
                }
            }

            // against all SENTRIES
            Sentry toRemoveSentry = null;            // removes the SENTRY from list to kill it
            for (Sentry sentry : list_of_sentries) {
                if (missile.getRectangle().intersects(sentry.getRectangle())) {
                    sentry.setHP(-250);

                    // destroy the SENTRY if HP is 0 or less
                    if (sentry.getHP() <= 0)
                        toRemoveSentry = sentry;

                    player.getList_Missiles().remove(missile);  // eliminate access to bullet
                    LIST_of_SENTRIES.remove(toRemoveSentry);    // eliminate access to LIST
                    list_of_sentries.remove(toRemoveSentry);    // eliminate access to list
                    return;
                }
            }

            // against the BOSS
            if (missile.getRectangle().intersects(boss.getRectangle())) {
                boss.setHP(-250);

                // destroy the BOSS if HP is 0 or less
                if (boss.getHP() <= 0)
                    boss.eraseBoss();                           // garanties the erasure of Image and Rectangle

                player.getList_Missiles().remove(missile);            // eliminate access to bullet
                return;
            }

            // against all CRATES
            Crate toRemoveCrate = null;
            for (Crate crate : list_of_crates) {
                if (missile.getRectangle().intersects(crate.getRectangle())) {
                    crate.setHP(-250);

                    // destroy the crate if HP is 0 or less
                    if (crate.getHP() <= 0) {
                        toRemoveCrate = crate;

                        spawnRandomPickable(crate.getX(), crate.getY());    // spawn a random Pickable in this position
                    }

                    player.getList_Missiles().remove(missile);
                    LIST_of_CRATES.remove(toRemoveCrate);
                    list_of_crates.remove(toRemoveCrate);
                    return;
                }
            }
        }

        // check all ENEMIES' EnemyBullets
        for (Enemy enemy : list_of_enemies) {
            for (EnemyBullet bullet : enemy.getList_bullets()) {

                // against the PLAYER
                if (bullet.getRectangle().intersects(player.getRectangle())) {
                    player.setHP(-50);
                    enemy.getList_bullets().remove(bullet);     // eliminate access to bullet
                    return;
                }

                // against all WALLS
                for (Wall wall : list_of_walls) {

                    if (bullet.getRectangle().intersects(wall.getRectangle())) {
                        enemy.getList_bullets().remove(bullet); // eliminate access to bullet
                        return;
                    }
                }
            }
        }

        // check all SENTRIES' EnemyBullets
        ArrayList<EnemyBullet> toRemoveSentryBullets = new ArrayList<>();
        for (Sentry sentry : list_of_sentries) {
            for (EnemyBullet bullet : sentry.getList_bullets()) {

                // against the PLAYER
                if (bullet.getRectangle().intersects(player.getRectangle())) {
                    player.setHP(-25);
                    toRemoveSentryBullets.add(bullet);
                }
            }

            // remove bullets , if there are any
            if (toRemoveSentryBullets.size() > 0) {

                sentry.getList_bullets().removeAll(toRemoveSentryBullets);  // eliminate access to bullets
                return;
            }
        }

        // check all BOSS EnemyBullets
        ArrayList<EnemyBullet> toRemoveBossBullets = new ArrayList<>();
        for (EnemyBullet bullet : boss.getListBullets()) {

            // against the PLAYER
            if (bullet.getRectangle().intersects(player.getRectangle())) {
                player.setHP(-25);
                toRemoveBossBullets.add(bullet);
            }
        }
        boss.getListBullets().removeAll(toRemoveBossBullets);

    }

    // method for collision with Pickables
    private void checkPlayerPickableCollision() {

        for (Pickable pickable : list_of_pickables) {

            Pickable toRemovePickable = null;       // is used to destroy the Pickable , if it's case
            if (pickable.getRectangle().intersects(player.getRectangle())) {

                switch (pickable.getType()) {

                    case "Cross":
                        if (player.getHp() != 100) {
                            player.maxHP();                 // maximizes PLAYER's HP
                            toRemovePickable = pickable;
                            score += 100;
                        }
                        break;

                    case "Megaphone":
                        player.activateMegaphone();      // PLAYER can now swap weapon to Megaphone
                        toRemovePickable = pickable;
                        score += 100;
                        break;

                    case "Battery":
                        if (player.getMegaphone()) {
                            player.setMegaAmmo(2);      // Battery gives 2 Megaphone ammo
                            toRemovePickable = pickable;
                            score += 100;
                        }
                        break;

                    case "Bazooka":
                        player.activateBazooka();        // PLAYER can now swap weapon to Bazooka
                        toRemovePickable = pickable;
                        score += 100;
                        break;

                    default:
                        if (player.getBazooka()) {
                            player.setBazookaAmmo(2);       // Rocket gives 2 Bazooka ammo
                            toRemovePickable = pickable;
                            score += 100;
                        }
                }

                LIST_of_PICKABLES.remove(toRemovePickable); // eliminate access to LIST
                list_of_pickables.remove(toRemovePickable); // eliminate access to list


                // end method early if collision was found
                if (toRemovePickable != null)
                    return;
            }
        }
    }

    // method for collision with Spikes
    private void checkPlayerSpikesCollision() {

        // checking all SPIKES
        for (Spikes spikes : list_of_spikes) {

            if (!spikes.getVisible()) {     // only verifies collision if it's not visible

                if (spikes.getRectangle().intersects(player.getRectangle())) {

                    player.setHP(-50);
                    spikes.setVisible();    // SPIKES become visible and obsolete
                }
            }
        }
    }
}
