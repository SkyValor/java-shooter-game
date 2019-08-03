package exercises.holyMoly.utils.managers;

import exercises.holyMoly.Game;
import exercises.holyMoly.Wall;

import java.util.LinkedList;
import java.util.List;

/**
 * Responsible for the management of all the {@link Wall}a of this game, the {@code WallManager} will
 * initialize all the units of this type and perform the transactions between the inner database and the
 * {@link Game} class.
 */
public class WallManager {

    private static List<Wall> roomOne = new LinkedList<>();
    private static List<Wall> roomTwo = new LinkedList<>();
    private static List<Wall> roomThree = new LinkedList<>();
    private static List<Wall> roomFour = new LinkedList<>();

    /**
     * Initializes the lists of {@link Wall}s for each of the rooms, by calling its respective populate methods.
     */
    public static void init() {

        populateRoomOne();
        populateRoomTwo();
        populateRoomThree();
        populateRoomFour();
    }

    /**
     * Populates the list of {@code walls} in the room 1, with new instances of {@link Wall}.
     */
    private static void populateRoomOne() {

        roomOne.add(new Wall(23,  297, 300, 20));
        roomOne.add(new Wall(91,  396, 57,  20));
        roomOne.add(new Wall(128, 416, 20,  111));
        roomOne.add(new Wall(136, 131, 277, 20));
        roomOne.add(new Wall(393, 151, 20,  376));
        roomOne.add(new Wall(472, 289, 120, 20));
        roomOne.add(new Wall(472, 309, 20,  112));
        roomOne.add(new Wall(572, 318, 20,  103));
        roomOne.add(new Wall(683, 22,  20,  270));
        roomOne.add(new Wall(683, 272, 87,  20));
        roomOne.add(new Wall(0,   -23, 726, 45));
        roomOne.add(new Wall(0,   -23, 23,  573));
        roomOne.add(new Wall(0,   527, 828, 23));
        roomOne.add(new Wall(804, -23, 24,  45));
        roomOne.add(new Wall(828, -23, 22,  573));
    }

    /**
     * Populates the list of {@code walls} in the room 2, with new instances of {@link Wall}.
     */
    private static void populateRoomTwo() {

        roomTwo.add(new Wall(658, 370, 20,  157));
        roomTwo.add(new Wall(351, 284, 20,  161));
        roomTwo.add(new Wall(104, 264, 724, 20));
        roomTwo.add(new Wall(377, 112, 20,  152));
        roomTwo.add(new Wall(377, 112, 262, 20));
        roomTwo.add(new Wall(619, 112, 20,  99));
        roomTwo.add(new Wall(448, 191, 191, 20));
        roomTwo.add(new Wall(694, 78,  134, 20));
        roomTwo.add(new Wall(694, 152, 134, 20));
        roomTwo.add(new Wall(0,   0,   850, 22));
        roomTwo.add(new Wall(0,   22,  23,  528));
        roomTwo.add(new Wall(0,   527, 726, 45));
        roomTwo.add(new Wall(804, 527, 24,  45));
        roomTwo.add(new Wall(828, 22,  45,  76));
        roomTwo.add(new Wall(828, 152, 22,  398));
    }

    /**
     * Populates the list of {@code walls} in the room 3, with new instances of {@link Wall}.
     */
    private static void populateRoomThree() {

        roomThree.add(new Wall(22,  187, 246, 19));
        roomThree.add(new Wall(22,  345, 491, 19));
        roomThree.add(new Wall(272, 21,  19,  115));
        roomThree.add(new Wall(291, 116, 160, 20));
        roomThree.add(new Wall(494, 117, 19,  247));
        roomThree.add(new Wall(341, 201, 76,  90));
        roomThree.add(new Wall(601, 150, 148, 271));
        roomThree.add(new Wall(101, 259, 19,  86));
        roomThree.add(new Wall(0,   0,   22,  97));
        roomThree.add(new Wall(0,   0,   850, 21));
        roomThree.add(new Wall(0,   152, 22,  419));
        roomThree.add(new Wall(22,  527, 28,  44));
        roomThree.add(new Wall(132, 527, 718, 44));
        roomThree.add(new Wall(827, 0,   23,  517));
    }

    /**
     * Populates the list of {@code walls} in the room 4, with new instances of {@link Wall}.
     */
    private static void populateRoomFour() {

        roomFour.add(new Wall(90,  130, 28,  15));
        roomFour.add(new Wall(240, 336, 28,  33));
        roomFour.add(new Wall(90,  425, 28,  33));
        roomFour.add(new Wall(240, 177, 28,  33));
        roomFour.add(new Wall(394, 101, 28,  33));
        roomFour.add(new Wall(394, 421, 28,  33));
        roomFour.add(new Wall(563, 177, 28,  33));
        roomFour.add(new Wall(563, 328, 28,  33));
        roomFour.add(new Wall(697, 113, 28,  33));
        roomFour.add(new Wall(697, 430, 28,  33));
        roomFour.add(new Wall(0,   0,   850, 22));
        roomFour.add(new Wall(0,   0,   22,  550));
        roomFour.add(new Wall(0,   528, 850, 22));
        roomFour.add(new Wall(827, 22,  23,  528));
    }

    /**
     * Gets the list of {@link Wall} for {@code room} 1.
     *
     * @return the list of walls
     */
    public List<Wall> getRoomOneWalls() {
        return roomOne;
    }

    /**
     * Gets the list of {@link Wall} for {@code room} 2.
     *
     * @return the list of walls
     */
    public List<Wall> getRoomTwoWalls() {
        return roomTwo;
    }

    /**
     * Gets the list of {@link Wall} for {@code room} 3.
     *
     * @return the list of walls
     */
    public List<Wall> getRoomThreeWalls() {
        return roomThree;
    }

    /**
     * Gets the list of {@link Wall} for {@code room} 4.
     *
     * @return the list of walls
     */
    public List<Wall> getRoomFourWalls() {
        return roomFour;
    }

    /**
     * Clears the list of {@link Wall} in the room 1. Afterwards, populates it with the list received as
     * argument.
     *
     * @param walls the list of walls to set
     */
    public void setRoomOneWalls(List<Wall> walls) {

        roomOne.clear();
        roomOne.addAll(walls);
    }

    /**
     * Clears the list of {@link Wall} in the room 2. Afterwards, populates it with the list received as
     * argument.
     *
     * @param walls the list of walls to set
     */

    public void setRoomTwoWalls(List<Wall> walls) {

        roomTwo.clear();
        roomTwo.addAll(walls);
    }
    /**
     * Clears the list of {@link Wall} in the room 3. Afterwards, populates it with the list received as
     * argument.
     *
     * @param walls the list of walls to set
     */
    public void setRoomThreeWalls(List<Wall> walls) {

        roomThree.clear();
        roomThree.addAll(walls);
    }

    /**
     * Clears the list of {@link Wall} in the room 4. Afterwards, populates it with the list received as
     * argument.
     *
     * @param walls the list of walls to set
     */
    public void setRoomFourWalls(List<Wall> walls) {

        roomFour.clear();
        roomFour.addAll(walls);
    }

}
