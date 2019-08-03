package exercises.holyMoly.utils.managers;

import exercises.holyMoly.Game;
import exercises.holyMoly.Spikes;

import java.util.LinkedList;
import java.util.List;

/**
 * Responsible for the management of all the {@link Spikes} units of this game, the {@code SpikesManager} will
 * initialize all the units of this type and perform the transactions between the inner database and the
 * {@link Game} class.
 */
public class SpikesManager {

    private static List<Spikes> roomOne = new LinkedList<>();
    private static List<Spikes> roomTwo = new LinkedList<>();
    private static List<Spikes> roomThree = new LinkedList<>();

    /**
     * Initializes the lists of {@link Spikes} units for each of the rooms, by calling its respective populate methods.
     */
    public static void init() {

        populateRoomOne();
        populateRoomTwo();
        populateRoomThree();
    }

    /**
     * Populates the list of {@code spikes} units in the room 1, with new instances of {@link Spikes}.
     */
    private static void populateRoomOne() {

        roomOne.add(new Spikes(524, 149));
    }

    /**
     * Populates the list of {@code spikes} units in the room 2, with new instances of {@link Spikes}.
     */
    private static void populateRoomTwo() {

        roomTwo.add(new Spikes(224, 352));
        roomTwo.add(new Spikes(406, 183));
    }

    /**
     * Populates the list of {@code spikes} units in the room 3, with new instances of {@link Spikes}.
     */
    private static void populateRoomThree() {

        roomThree.add(new Spikes(178, 262));
        roomThree.add(new Spikes(452, 368));
    }

    /**
     * Gets the list of {@link Spikes} for {@code room} 1.
     *
     * @return the list of spikes
     */
    public List<Spikes> getRoomOneSpikes() {
        return roomOne;
    }

    /**
     * Gets the list of {@link Spikes} for {@code room} 2.
     *
     * @return the list of spikes
     */
    public List<Spikes> getRoomTwoSpikes() {
        return roomTwo;
    }

    /**
     * Gets the list of {@link Spikes} for {@code room} 3.
     *
     * @return the list of spikes
     */
    public List<Spikes> getRoomThreeSpikes() {
        return roomThree;
    }

    /**
     * Clears the list of {@link Spikes} units in the room 1. Afterwards, populates it with the list received as
     * argument.
     *
     * @param spikes the list of spikes to set
     */
    public void setRoomOneSpikes(List<Spikes> spikes) {

        roomOne.clear();
        roomOne.addAll(spikes);
    }

    /**
     * Clears the list of {@link Spikes} units in the room 2. Afterwards, populates it with the list received as
     * argument.
     *
     * @param spikes the list of spikes to set
     */
    public void setRoomTwoSpikes(List<Spikes> spikes) {

        roomTwo.clear();
        roomTwo.addAll(spikes);
    }

    /**
     * Clears the list of {@link Spikes} units in the room 3. Afterwards, populates it with the list received as
     * argument.
     *
     * @param spikes the list of spikes to set
     */
    public void setRoomThreeSpikes(List<Spikes> spikes) {

        roomThree.clear();
        roomThree.addAll(spikes);
    }
}
