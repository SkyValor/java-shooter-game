package exercises.holyMoly.utils;

import exercises.holyMoly.Game;
import exercises.holyMoly.Pickable;
import exercises.holyMoly.PickableType;
import exercises.holyMoly.utils.managers.CrateManager;
import exercises.holyMoly.utils.managers.PickableManager;
import exercises.holyMoly.entities.destructable.Crate;

import java.util.Random;

/**
 * Returns, when possible, a new instance of a {@link Pickable}. To be called from the {@link Game} class.
 */
public class PickableFactory {

    /**
     * Generates a new {@link Pickable}, if possible, and returns it. Takes in the {@code position} of the
     * {@link Crate} that was destroyed previously, which serves as the {@code position} for the new {@code pickable}
     * that may or may not be instantiated.
     * <br>
     * If the remaining number of {@code pickables} is the same as the remaining number of {@code crates}, then a new
     * instance of {@link Pickable} must be created. Otherwise, call for a {@code random boolean} to make that decision.
     * <br>
     * A {@code random int} is generated, from zero to the number of {@code remaining pickables}. Afterwards, a call to
     * the {@link PickableManager} is made, passing the {@code index} as argument, so that a {@link PickableType} is
     * returned. The {@code position} and the {@code pickableType} are used to instantiate a new {@code Pickable}.
     *
     * @param positionX the x for the initialization of the new Pickable
     * @param positionY the y for the initialization of the new Pickable
     * @return the new instance of Pickable
     * @see Random#nextBoolean()
     * @see Random#nextInt(int)
     * @see PickableManager#getPickableTypeAt(int)
     */
    private Pickable spawnRandomPickable(int positionX, int positionY) {

        if (PickableManager.anyPickablesRemaining()) {

            Random rand = new Random();
            boolean b = rand.nextBoolean();

            // TODO: CrateManager needs to be updated at all times
            // TODO: if we are to query for its size...

            // TODO: when collision with Crate is true , have the Game update the CrateManager before
            // TODO: calling for PickableFactory

            int remainingPickables = PickableManager.numberOfRemainingPickables();
            int remainingCrates = CrateManager.numberOfRemainingCrates();

            if (remainingPickables == remainingCrates || b) {

                // determine the maximum and minimal values for the index of the List
                int maxIndex = remainingPickables - 1;
                int minIndex = 0;
                int randomIndex = rand.nextInt(maxIndex - minIndex + 1) + minIndex;     // nextInt(max - min + 1) + min

                // lastly , remove the said Pickable from its previous List
                PickableType pickableType = PickableManager.getPickableTypeAt(randomIndex);

                // spawn the new Pickable
                // also, add it to the corresponding Lists

                return new Pickable(positionX, positionY, pickableType);
            }
        }

        return null;
    }
}
