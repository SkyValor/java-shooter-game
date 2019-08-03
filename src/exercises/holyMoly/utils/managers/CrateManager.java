package exercises.holyMoly.utils.managers;

import exercises.holyMoly.Game;
import exercises.holyMoly.entities.destructable.Crate;

import java.util.LinkedList;
import java.util.List;

/**
 * Responsible for the management of all the {@link Crate} units of this game, the {@code CrateManager} will
 * initialize all the units of this type and perform the transactions between the inner database and the
 * {@link Game} class.
 */
public class CrateManager {

    private static List<Crate> roomOne = new LinkedList<>();
    private static List<Crate> roomTwo = new LinkedList<>();
    private static List<Crate> roomThree = new LinkedList<>();

    /**
     * Initializes the lists of {@link Crate} units for each of the rooms, by calling its respective populate methods.
     */
    public static void init() {

        populateRoomOne();
        populateRoomTwo();
        populateRoomThree();
    }

    /**
     * Populates the list of {@code crates} units in the room 1, with new instances of {@link Crate}.
     */
    private static void populateRoomOne() {

        roomOne.add(new Crate(91,  417, 1));
        roomOne.add(new Crate(152, 490, 1));
        roomOne.add(new Crate(494, 309, 1));
        roomOne.add(new Crate(539, 311, 1));
        roomOne.add(new Crate(494, 349, 1));
        roomOne.add(new Crate(539, 349, 1));
    }

    /**
     * Populates the list of {@code crates} units in the room 2, with new instances of {@link Crate}.
     */
    private static void populateRoomTwo() {

        roomTwo.add(new Crate(171, 489, 2));
        roomTwo.add(new Crate(400, 133, 2));
        roomTwo.add(new Crate(447, 133, 2));
        roomTwo.add(new Crate(494, 156, 2));
        roomTwo.add(new Crate(584, 156, 2));
        roomTwo.add(new Crate(538, 227, 2));
        roomTwo.add(new Crate(699, 108, 2));
    }

    /**
     * Populates the list of {@code crates} units in the room 3, with new instances of {@link Crate}.
     */
    private static void populateRoomThree() {

        roomThree.add(new Crate(27,  24,  3));
        roomThree.add(new Crate(30,  309, 3));
        roomThree.add(new Crate(66,  309, 3));
        roomThree.add(new Crate(157, 454, 3));
        roomThree.add(new Crate(158, 491, 3));
        roomThree.add(new Crate(381, 366, 3));
    }

    /**
     * Gets the list of {@link Crate} for {@code room} 1.
     *
     * @return the list of crates
     */
    public List<Crate> getRoomOneCrates() {
        return roomOne;
    }

    /**
     * Gets the list of {@link Crate} for {@code room} 2.
     *
     * @return the list of crates
     */
    public List<Crate> getRoomTwoCrates() {
        return roomTwo;
    }

    /**
     * Gets the list of {@link Crate} for {@code room} 3.
     *
     * @return the list of crates
     */
    public List<Crate> getRoomThreeCrates() {
        return roomThree;
    }

    /**
     * Clears the list of {@link Crate} units in the room 1. Afterwards, populates it with the list received as
     * argument.
     *
     * @param crates the list of crates to set
     */
    public void setRoomOneCrates(List<Crate> crates) {

        roomOne.clear();
        roomOne.addAll(crates);
    }

    /**
     * Clears the list of {@link Crate} units in the room 2. Afterwards, populates it with the list received as
     * argument.
     *
     * @param crates the list of crates to set
     */

    public void setRoomTwoCrates(List<Crate> crates) {

        roomTwo.clear();
        roomTwo.addAll(crates);
    }
    /**
     * Clears the list of {@link Crate} units in the room 3. Afterwards, populates it with the list received as
     * argument.
     *
     * @param crates the list of crates to set
     */
    public void setRoomThreeCrates(List<Crate> crates) {

        roomThree.clear();
        roomThree.addAll(crates);
    }

    public static int numberOfRemainingCrates() {
        return roomOne.size() + roomTwo.size() + roomThree.size();
    }
}
