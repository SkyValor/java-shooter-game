package exercises.holyMoly.utils;

import exercises.holyMoly.game.Wall;

import java.util.LinkedList;
import java.util.List;

/**
 * Has methods for when the {@link exercises.holyMoly.game.Game} needs to load the {@code walls} of one specific room.
 */
public class WallManager {

    /**
     * Loads the {@code walls} for the first {@code room} of the game.
     *
     * @return the walls
     * @see Wall
     */
    public static List<Wall> loadWallsRoomOne() {

        List<Wall> walls = new LinkedList<>();

        walls.add(new Wall(23, 297, 300, 20, 1));
        walls.add(new Wall(91, 396, 57, 20, 1));
        walls.add(new Wall(128, 416, 20, 111, 1));
        walls.add(new Wall(136, 131, 277, 20, 1));
        walls.add(new Wall(393, 151, 20, 376, 1));
        walls.add(new Wall(472, 289, 120, 20, 1));
        walls.add(new Wall(472, 309, 20, 112, 1));
        walls.add(new Wall(572, 318, 20, 103, 1));
        walls.add(new Wall(683, 22, 20, 270, 1));
        walls.add(new Wall(683, 272, 87, 20, 1));
        walls.add(new Wall(0, -23, 726, 45, 1));
        walls.add(new Wall(0, -23, 23, 573, 1));
        walls.add(new Wall(0, 527, 828, 23, 1));
        walls.add(new Wall(804, -23, 24, 45, 1));
        walls.add(new Wall(828, -23, 22, 573, 1));

        return walls;
    }

    /**
     * Loads the {@code walls} for the second {@code room} of the game.
     *
     * @return the walls
     * @see Wall
     */
    public static List<Wall> loadWallsRoomTwo() {

        List<Wall> walls = new LinkedList<>();

        walls.add(new Wall(658, 370, 20,  157, 2));
        walls.add(new Wall(351, 284, 20,  161, 2));
        walls.add(new Wall(104, 264, 724, 20,  2));
        walls.add(new Wall(377, 112, 20,  152, 2));
        walls.add(new Wall(377, 112, 262, 20,  2));
        walls.add(new Wall(619, 112, 20,  99,  2));
        walls.add(new Wall(448, 191, 191, 20,  2));
        walls.add(new Wall(694, 78,  134, 20,  2));
        walls.add(new Wall(694, 152, 134, 20,  2));
        walls.add(new Wall(0,   0,   850, 22,  2));
        walls.add(new Wall(0,   22,  23,  528, 2));
        walls.add(new Wall(0,   527, 726, 45,  2));
        walls.add(new Wall(804, 527, 24,  45,  2));
        walls.add(new Wall(828, 22,  45,  76,  2));
        walls.add(new Wall(828, 152, 22,  398, 2));

        return walls;
    }

    /**
     * Loads the {@code walls} for the third {@code room} of the game.
     *
     * @return the walls
     * @see Wall
     */
    public static List<Wall> loadWallsRoomThree() {

        List<Wall> walls = new LinkedList<>();

        walls.add(new Wall(22,  187, 246, 19,  3));
        walls.add(new Wall(22,  345, 491, 19,  3));
        walls.add(new Wall(272, 21,  19,  115, 3));
        walls.add(new Wall(291, 116, 160, 20,  3));
        walls.add(new Wall(494, 117, 19,  247, 3));
        walls.add(new Wall(341, 201, 76,  90,  3));
        walls.add(new Wall(601, 150, 148, 271, 3));
        walls.add(new Wall(101, 259, 19,  86,  3));
        walls.add(new Wall(0,   0,   22,  97,  3));
        walls.add(new Wall(0,   0,   850, 21,  3));
        walls.add(new Wall(0,   152, 22,  419, 3));
        walls.add(new Wall(22,  527, 28,  44,  3));
        walls.add(new Wall(132, 527, 718, 44,  3));
        walls.add(new Wall(827, 0,   23,  517, 3));

        return walls;
    }

    /**
     * Loads the {@code walls} for the fourth {@code room} of the game.
     *
     * @return the walls
     * @see Wall
     */
    public static List<Wall> loadWallsRoomFour() {

        List<Wall> walls = new LinkedList<>();

        walls.add(new Wall(90,  130, 28,  15,  4));
        walls.add(new Wall(240, 336, 28,  33,  4));
        walls.add(new Wall(90,  425, 28,  33,  4));
        walls.add(new Wall(240, 177, 28,  33,  4));
        walls.add(new Wall(394, 101, 28,  33,  4));
        walls.add(new Wall(394, 421, 28,  33,  4));
        walls.add(new Wall(563, 177, 28,  33,  4));
        walls.add(new Wall(563, 328, 28,  33,  4));
        walls.add(new Wall(697, 113, 28,  33,  4));
        walls.add(new Wall(697, 430, 28,  33,  4));
        walls.add(new Wall(0,   0,   850, 22,  4));
        walls.add(new Wall(0,   0,   22,  550, 4));
        walls.add(new Wall(0,   528, 850, 22,  4));
        walls.add(new Wall(827, 22,  23,  528, 4));

        return walls;
    }

}
