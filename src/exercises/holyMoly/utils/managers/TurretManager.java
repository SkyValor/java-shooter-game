package exercises.holyMoly.utils.managers;

import exercises.holyMoly.Game;
import exercises.holyMoly.entities.Alignment;
import exercises.holyMoly.entities.destructable.enemy.turret.Turret;

import java.util.LinkedList;
import java.util.List;

/**
 * Responsible for the management of all the {@link Turret} units of this game, the {@code TurretManager} will
 * initialize all the units of this type and perform the transactions between the inner database and the
 * {@link Game} class.
 */
public class TurretManager {

    private static List<Turret> roomOne = new LinkedList<>();
    private static List<Turret> roomTwo = new LinkedList<>();
    private static List<Turret> roomThree = new LinkedList<>();

    /**
     * Initializes the lists of {@link Turret} units for each of the rooms, by calling its respective populate methods.
     */
    public static void init() {

        populateRoomOne();
        populateRoomTwo();
        populateRoomThree();
    }

    /**
     * Populates the list of {@code turrets} units in the room 1, with new instances of {@link Turret}.
     */
    private static void populateRoomOne() {

        /*
        LIST_of_SENTRIES.add(new Sentry(335, 460, 1, "Vertical"));
        LIST_of_SENTRIES.add(new Sentry(775, 463, 1, "Vertical"));
         */

        Turret turret1 = new Turret(335, 460, Alignment.VERTICAL);
        Turret turret2 = new Turret(775, 463, Alignment.VERTICAL);

        roomOne.add(turret1);
        roomOne.add(turret2);
    }

    /**
     * Populates the list of {@code turrets} units in the room 2, with new instances of {@link Turret}.
     */
    private static void populateRoomTwo() {

        /*
        LIST_of_SENTRIES.add(new Sentry(335, 463, 2, "Vertical"));
        LIST_of_SENTRIES.add(new Sentry(760, 30,  2, "Horizontal"));
        LIST_of_SENTRIES.add(new Sentry(760, 190, 2, "Horizontal"));
        LIST_of_SENTRIES.add(new Sentry(630, 300, 2, "Vertical"));
         */

        Turret turret1 = new Turret(335, 463, Alignment.VERTICAL);
        Turret turret2 = new Turret(760, 30, Alignment.HORIZONTAL);
        Turret turret3 = new Turret(760, 190, Alignment.HORIZONTAL);
        Turret turret4 = new Turret(630, 300, Alignment.VERTICAL);

        roomTwo.add(turret1);
        roomTwo.add(turret2);
        roomTwo.add(turret3);
        roomTwo.add(turret4);
    }

    /**
     * Populates the list of {@code turrets} units in the room 3, with new instances of {@link Turret}.
     */
    private static void populateRoomThree() {

        /*
        LIST_of_SENTRIES.add(new Sentry(310, 50,  3, "Vertical"));
        LIST_of_SENTRIES.add(new Sentry(200, 295, 3, "Horizontal"));
        LIST_of_SENTRIES.add(new Sentry(760, 445, 3, "Vertical"));
         */

        Turret turret1 = new Turret(310, 50, Alignment.VERTICAL);
        Turret turret2 = new Turret(200, 295, Alignment.HORIZONTAL);
        Turret turret3 = new Turret(760, 445, Alignment.VERTICAL);

        roomThree.add(turret1);
        roomThree.add(turret2);
        roomThree.add(turret3);
    }

    /**
     * Gets the list of {@link Turret}s for {@code room} one.
     *
     * @return the list of turrets
     */
    public List<Turret> getRoomOneTurrets() {
        return roomOne;
    }

    /**
     * Gets the list of {@link Turret}s for {@code room} two.
     *
     * @return the list of turrets
     */
    public List<Turret> getRoomTwoTurrets() {
        return roomTwo;
    }

    /**
     * Gets the list of {@link Turret}s for {@code room} three.
     *
     * @return the list of turrets
     */
    public List<Turret> getRoomThreeTurrets() {
        return roomThree;
    }

    /**
     * Clears the list of {@link Turret} units in the room 1. Afterwards, populates it with the list received as
     * argument.
     *
     * @param turrets the list of turrets to set
     */
    public void setRoomOneTurrets(List<Turret> turrets) {

        roomOne.clear();
        roomOne.addAll(turrets);
    }

    /**
     * Clears the list of {@link Turret} units in the room 2. Afterwards, populates it with the list received as
     * argument.
     *
     * @param turrets the list of turrets to set
     */
    public void setRoomTwoTurrets(List<Turret> turrets) {

        roomTwo.clear();
        roomTwo.addAll(turrets);
    }

    /**
     * Clears the list of {@link Turret} units in the room 3. Afterwards, populates it with the list received as
     * argument.
     *
     * @param turrets the list of turrets to set
     */
    public void setRoomThreeTurrets(List<Turret> turrets) {

        roomThree.clear();
        roomThree.addAll(turrets);
    }

}
