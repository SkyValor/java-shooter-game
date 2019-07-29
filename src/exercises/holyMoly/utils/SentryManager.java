package exercises.holyMoly.utils;

import exercises.holyMoly.Direction;
import exercises.holyMoly.game.Sentry;

import java.util.LinkedList;
import java.util.List;

public class SentryManager {

    List<Sentry> roomOne = new LinkedList<>();
    List<Sentry> roomTwo = new LinkedList<>();
    List<Sentry> roomThree = new LinkedList<>();

    private void populateLists() {

        Sentry sentry1 = new Sentry(Direction.DOWN, 30, 150, 1, 1);

    }

    /**
     * Loads the {@code sentries} for the first {@code room} of the game.
     *
     * @return the sentries
     * @see Sentry
     */
    public static List<Sentry> loadsentriesRoomOne() {

        List<Sentry> sentries = new LinkedList<>();

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

        return sentries;
    }

    /**
     * Loads the {@code sentries} for the second {@code room} of the game.
     *
     * @return the sentries
     * @see Sentry
     */
    public static List<Sentry> loadsentriesRoomTwo() {

        List<Sentry> sentries = new LinkedList<>();

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

        return sentries;
    }

    /**
     * Loads the {@code sentries} for the third {@code room} of the game.
     *
     * @return the sentries
     * @see Sentry
     */
    public static List<Sentry> loadsentriesRoomThree() {

        List<Sentry> sentries = new LinkedList<>();

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

        return sentries;
    }
}
