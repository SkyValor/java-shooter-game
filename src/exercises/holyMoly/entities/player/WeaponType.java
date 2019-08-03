package exercises.holyMoly.entities.player;

import exercises.holyMoly.Game;

/**
 * Represents the weapon types that the {@link Player} can hold in this {@link Game}.
 */
public enum WeaponType {

    MACHINEGUN(25,  -1, 0.1f, true),
    MEGAPHONE( 5,   3,  1.0f, false),
    BAZOOKA(   250, 2,  2.0f, false);

    private int damage;
    private int ammo;
    private float weaponDelay;
    private boolean activated;

    /**
     * Constructor method. Takes in the arguments for property initialization.
     *
     * @param damage the damage
     * @param ammo the ammo amount
     * @param weaponDelay the cooldown of this weapon type, in seconds
     * @param activated the activated state
     */
    WeaponType(int damage, int ammo, float weaponDelay, boolean activated) {

        this.damage = damage;
        this.ammo = ammo;
        this.weaponDelay = weaponDelay;
        this.activated = activated;
    }

    /**
     * Gets the damage of this weapon type.
     *
     * @return the damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Gets the ammo amount of this weapon type.
     *
     * @return the ammo amount
     */
    public int getAmmo() {
        return ammo;
    }

    /**
     * Decrements one unit from the ammo amount.
     */
    public void decrementAmmo() {
        ammo -= 1;
    }

    /**
     * Gets the delay of this weapon type in precision.
     *
     * @return the delay
     */
    public float getWeaponDelay() {
        return weaponDelay;
    }

    /**
     * Activates this weapon type by setting the {@code activated} property to {@code true}.
     */
    public void activate() {
        activated = true;
    }

    /**
     * Gets the next {@code activated} weapon type. Follows the order: Machine Gun > Megaphone > Bazooka > Machine Gun.
     *
     * @return the next activated weapon type
     */
    public WeaponType getNextWeapon() {

        switch (this) {

            case MACHINEGUN:

                if (MEGAPHONE.activated) {
                    return MEGAPHONE;
                }

                if (BAZOOKA.activated) {
                    return BAZOOKA;
                }

            case MEGAPHONE:

                if (BAZOOKA.activated) {
                    return BAZOOKA;
                }

                return MACHINEGUN;

            default:

                return MACHINEGUN;
        }
    }

    /**
     * Gets this instance of weapon type's name in lower case.
     *
     * @return the name in lower case
     */
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
