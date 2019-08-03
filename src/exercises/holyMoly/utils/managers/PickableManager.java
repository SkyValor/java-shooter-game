package exercises.holyMoly.utils.managers;

import exercises.holyMoly.PickableType;

import java.util.LinkedList;
import java.util.List;

public class PickableManager {

    private static List<PickableType> possiblePickables = new LinkedList<>();

    public static void init() {

        possiblePickables.add(PickableType.ROCKET);
        possiblePickables.add(PickableType.ROCKET);
        possiblePickables.add(PickableType.BATTERY);
        possiblePickables.add(PickableType.BATTERY);
        possiblePickables.add(PickableType.MEGAPHONE);
        possiblePickables.add(PickableType.BAZOOKA);
        possiblePickables.add(PickableType.CROSS);
        possiblePickables.add(PickableType.CROSS);
        possiblePickables.add(PickableType.CROSS);
        possiblePickables.add(PickableType.CROSS);
    }

    public static boolean anyPickablesRemaining() {
        return !possiblePickables.isEmpty();
    }

    public static int numberOfRemainingPickables() {
        return possiblePickables.size();
    }

    public static PickableType getPickableTypeAt(int index) {

        PickableType pickableType = possiblePickables.get(index);

        possiblePickables.remove(index);

        return pickableType;
    }
}
