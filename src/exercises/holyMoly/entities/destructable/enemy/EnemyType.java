package exercises.holyMoly.entities.destructable.enemy;

import exercises.holyMoly.entities.BulletType;

/**
 * Represents the type of {@link Enemy}(ies) in this {@link exercises.holyMoly.Game}.
 */
public enum EnemyType {

    SENTRY(25, BulletType.SENTRY),
    TURRET(50, BulletType.TURRET),
    BOSS(  25, BulletType.BOSS);

    private int bulletDamage;
    private BulletType bulletType;

    /**
     * Constructor method. Takes in the arguments for property initialization.
     *
     * @param bulletDamage the damage of this enemy's bullet damage
     * @param bulletType the type of this enemy's bullet
     */
    EnemyType(int bulletDamage, BulletType bulletType) {

        this.bulletDamage = bulletDamage;
        this.bulletType = bulletType;
    }

    /**
     * Gets the damage of this bullet.
     *
     * @return the damage
     */
    public int getBulletDamage() {
        return bulletDamage;
    }

    /**
     * Gets the type of this bullet.
     *
     * @return the type
     */
    public BulletType getBulletType() {
        return bulletType;
    }
}
