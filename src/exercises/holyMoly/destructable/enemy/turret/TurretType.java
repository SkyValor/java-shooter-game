package exercises.holyMoly.destructable.enemy.turret;

public enum TurretType {

    HORIZONTAL,
    VERTICAL;

    TurretType() {}

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
