package exercises.holyMoly.utils.managers;

import exercises.holyMoly.Game;
import exercises.holyMoly.Direction;
import exercises.holyMoly.entities.destructable.enemy.sentry.Sentry;
import exercises.holyMoly.entities.destructable.enemy.sentry.Sentry.SentryAI;

import java.util.LinkedList;
import java.util.List;

/**
 * Responsible for the management of all the {@link Sentry} units of this game, the {@code SentryManager} will
 * initialize all the units of this type and perform the transactions between the inner database and the
 * {@link Game} class.
 */
public class SentryManager {

    private static List<Sentry> roomOne = new LinkedList<>();
    private static List<Sentry> roomTwo = new LinkedList<>();
    private static List<Sentry> roomThree = new LinkedList<>();

    /**
     * Initializes the lists of {@link Sentry} units for each of the rooms, by calling its respective populate methods.
     */
    public static void init() {

        populateRoomOne();
        populateRoomTwo();
        populateRoomThree();
    }

    /**
     * Populates the list of {@code sentries} units in the room 1, with new instances of {@link Sentry}, with the
     * proper {@link SentryAI} behaviours.
     */
    private static void populateRoomOne() {

        /*
        sentries.add(new Sentry("Down", 30, 150, 1, 1));
        sentries.get(0).addBehaviour("UpDown",    50);
        sentries.get(0).addBehaviour("DownRight", 210);
        sentries.get(0).addBehaviour("RightLeft", 245);
        sentries.get(0).addBehaviour("LeftUp",    30);

        sentries.add(new Sentry("Up", 410, 290, 1, 0));
        sentries.get(1).addBehaviour("UpDown",    150);
        sentries.get(1).addBehaviour("DownRight", 460);
        sentries.get(1).addBehaviour("RightUp",   600);
        sentries.get(1).addBehaviour("UpDown",    30);
        sentries.get(1).addBehaviour("DownLeft",  460);
        sentries.get(1).addBehaviour("LeftUp",    410);

        sentries.add(new Sentry("Down", 720, 135, 1, 1));
        sentries.get(2).addBehaviour("UpDown",    40);
        sentries.get(2).addBehaviour("DownUp",    185);
         */

        Sentry sentry1 = new Sentry(30, 150, Direction.DOWN, 1);
        sentry1.getAi().addBehaviour(Direction.UP, -1, 50);
        sentry1.getAi().addBehaviour(Direction.DOWN, -1, 210);
        sentry1.getAi().addBehaviour(Direction.RIGHT, 245, -1);
        sentry1.getAi().addBehaviour(Direction.LEFT, 30, -1);

        Sentry sentry2 = new Sentry(410, 290, Direction.UP, 0);
        sentry2.getAi().addBehaviour(Direction.UP, -1, 150);
        sentry2.getAi().addBehaviour(Direction.DOWN, -1, 460);
        sentry2.getAi().addBehaviour(Direction.RIGHT, 600, -1);
        sentry2.getAi().addBehaviour(Direction.UP, -1, 30);
        sentry2.getAi().addBehaviour(Direction.DOWN, -1, 460);
        sentry2.getAi().addBehaviour(Direction.LEFT, 410, -1);

        Sentry sentry3 = new Sentry(720, 135, Direction.DOWN, 1);
        sentry3.getAi().addBehaviour(Direction.UP, -1, 40);
        sentry3.getAi().addBehaviour(Direction.DOWN, -1, 185);

        roomOne.add(sentry1);
        roomOne.add(sentry2);
        roomOne.add(sentry3);
    }

    /**
     * Populates the list of {@code sentries} units in the room 2, with new instances of {@link Sentry}, with the
     * proper {@link SentryAI} behaviours.
     */
    private static void populateRoomTwo() {

        /*
        sentries.add(new Sentry("Up", 505, 400, 2, 0));
        sentries.get(3).addBehaviour("UpDown",    280);
        sentries.get(3).addBehaviour("DownUp",    450);

        sentries.add(new Sentry("Down", 40, 220, 2, 1));
        sentries.get(4).addBehaviour("UpDown",    35);
        sentries.get(4).addBehaviour("DownUp",    450);

        sentries.add(new Sentry("Right", 255, 35, 2, 1));
        sentries.get(5).addBehaviour("UpRight",   35);
        sentries.get(5).addBehaviour("RightLeft", 630);
        sentries.get(5).addBehaviour("LeftDown",  180);
        sentries.get(5).addBehaviour("DownUp",    190);

        sentries.add(new Sentry("Left", 570, 200, 2, 2));
        sentries.get(6).addBehaviour("UpDown",    105);
        sentries.get(6).addBehaviour("DownLeft",  200);
        sentries.get(6).addBehaviour("LeftRight", 560);
        sentries.get(6).addBehaviour("RightUp",   630);
         */

        Sentry sentry1 = new Sentry(505, 400, Direction.UP, 0);
        sentry1.getAi().addBehaviour(Direction.UP, -1, 280);
        sentry1.getAi().addBehaviour(Direction.DOWN, -1, 450);

        Sentry sentry2 = new Sentry(40, 220, Direction.DOWN, 1);
        sentry2.getAi().addBehaviour(Direction.UP, -1, 35);
        sentry2.getAi().addBehaviour(Direction.DOWN, -1, 450);

        Sentry sentry3 = new Sentry(255, 35, Direction.RIGHT, 1);
        sentry3.getAi().addBehaviour(Direction.UP, -1, 35);
        sentry3.getAi().addBehaviour(Direction.RIGHT, 630, -1);
        sentry3.getAi().addBehaviour(Direction.LEFT, 180, -1);
        sentry3.getAi().addBehaviour(Direction.DOWN, -1, 190);

        Sentry sentry4 = new Sentry(570, 200, Direction.LEFT, 2);
        sentry4.getAi().addBehaviour(Direction.UP, -1, 105);
        sentry4.getAi().addBehaviour(Direction.DOWN, -1, 200);
        sentry4.getAi().addBehaviour(Direction.LEFT, 560, -1);
        sentry4.getAi().addBehaviour(Direction.RIGHT, 630, -1);

        roomTwo.add(sentry1);
        roomTwo.add(sentry2);
        roomTwo.add(sentry3);
        roomTwo.add(sentry4);
    }

    /**
     * Populates the list of {@code sentries} units in the room 3, with new instances of {@link Sentry}, with the
     * proper {@link SentryAI} behaviours.
     */
    private static void populateRoomThree() {

        /*
        sentries.add(new Sentry("Left", 250, 465, 3, 1));
        sentries.get(7).addBehaviour("RightLeft", 480 );
        sentries.get(7).addBehaviour("LeftRight", 210);

        sentries.add(new Sentry("Left", 100, 225, 3, 1));
        sentries.get(8).addBehaviour("RightLeft", 280);
        sentries.get(8).addBehaviour("LeftRight", 115);

        sentries.add(new Sentry("Down", 420, 230, 3, 1));
        sentries.get(9).addBehaviour("UpDown",    140);
        sentries.get(9).addBehaviour("DownUp",    280);

        sentries.add(new Sentry("Up", 760, 250, 3, 1));
        sentries.get(10).addBehaviour("DownUp",   370);
        sentries.get(10).addBehaviour("UpLeft",   50);
        sentries.get(10).addBehaviour("LeftDown", 535);
        sentries.get(10).addBehaviour("DownUp",   460);
        sentries.get(10).addBehaviour("UpRight",  50);
        sentries.get(10).addBehaviour("RightDown",760);
         */

        Sentry sentry1 = new Sentry(250, 465, Direction.LEFT, 1);
        sentry1.getAi().addBehaviour(Direction.RIGHT, 480, -1);
        sentry1.getAi().addBehaviour(Direction.LEFT, 210, -1);

        Sentry sentry2 = new Sentry(100, 225, Direction.LEFT, 1);
        sentry2.getAi().addBehaviour(Direction.RIGHT, 280, -1);
        sentry2.getAi().addBehaviour(Direction.LEFT, 115, -1);

        Sentry sentry3 = new Sentry(420, 230, Direction.DOWN, 1);
        sentry3.getAi().addBehaviour(Direction.UP, -1, 140);
        sentry3.getAi().addBehaviour(Direction.DOWN, -1, 280);

        Sentry sentry4 = new Sentry(760, 250, Direction.UP, 1);
        sentry4.getAi().addBehaviour(Direction.DOWN, -1, 370);
        sentry4.getAi().addBehaviour(Direction.UP, 50, -1);
        sentry4.getAi().addBehaviour(Direction.LEFT, 535, -1);
        sentry4.getAi().addBehaviour(Direction.DOWN, -1, 460);
        sentry4.getAi().addBehaviour(Direction.UP, -1, 50);
        sentry4.getAi().addBehaviour(Direction.RIGHT, 760, -1);

        roomThree.add(sentry1);
        roomThree.add(sentry2);
        roomThree.add(sentry3);
        roomThree.add(sentry4);
    }

    /**
     * Gets the list of {@link Sentry} for {@code room} one.
     *
     * @return the list of sentries
     */
    public List<Sentry> getRoomOneSentries() {
        return roomOne;
    }

    /**
     * Gets the list of {@link Sentry} for {@code room} two.
     *
     * @return the list of sentries
     */
    public List<Sentry> getRoomTwoSentries() {
        return roomTwo;
    }

    /**
     * Gets the list of {@link Sentry} for {@code room} three.
     *
     * @return the list of sentries
     */
    public List<Sentry> getRoomThreeSentries() {
        return roomThree;
    }

    /**
     * Clears the list of {@link Sentry} units in the room 1. Afterwards, populates it with the list received as
     * argument.
     *
     * @param sentries the list of sentries to set
     */
    public void setRoomOneSentries(List<Sentry> sentries) {

        roomOne.clear();
        roomOne.addAll(sentries);
    }

    /**
     * Clears the list of {@link Sentry} units in the room 2. Afterwards, populates it with the list received as
     * argument.
     *
     * @param sentries the list of sentries to set
     */

    public void setRoomTwoSentries(List<Sentry> sentries) {

        roomTwo.clear();
        roomTwo.addAll(sentries);
    }
    /**
     * Clears the list of {@link Sentry} units in the room 3. Afterwards, populates it with the list received as
     * argument.
     *
     * @param sentries the list of sentries to set
     */
    public void setRoomThreeSentries(List<Sentry> sentries) {

        roomThree.clear();
        roomThree.addAll(sentries);
    }
}
