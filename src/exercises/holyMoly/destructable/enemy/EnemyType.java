package exercises.holyMoly.destructable.enemy;

public enum EnemyType {

    SENTRY(25),
    TURRET(50),
    BOSS(25);

    int bulletDamage;

    EnemyType(int bulletDamage) {
        this.bulletDamage = bulletDamage;
    }

    public int getBulletDamage() {
        return bulletDamage;
    }
}
