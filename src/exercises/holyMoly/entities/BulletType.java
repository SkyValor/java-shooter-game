package exercises.holyMoly.entities;

public enum BulletType {

    MACHINEGUN(5, 25),
    BAZOOKA(3, 250),
    MEGAPHONE(5, 0),
    SENTRY(5, 50),
    TURRET(5, 25),
    BOSS(5, 25);

    private int velocity;
    private int damage;

    BulletType(int velocity, int damage) {
       this.velocity = velocity;
       this.damage = damage;
    }

    public int getVelocity() {
        return velocity;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
