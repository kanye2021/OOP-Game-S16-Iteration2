package AI.Movement;

import AI.Memory.Memory;
import models.entities.Entity;
import models.map.Map;
import utilities.EntityAction;
import utilities.NavigationUtilities;

import java.util.Random;

/**
 * Created by Bradley on 3/5/16.
 */
public class MotorCortex {
    double rand;
    //Needed so the toast doesn't constantly pop up
    private Random rng = new Random();
    private Memory memory;

    public MotorCortex(Memory memory) {
        this.memory = memory;
    }

    public void process() {

        rand = rng.nextDouble();

        if (memory.getPersonality().getLaziness() > rand) {
            return;
        }

        if (memory.getDecision() == null) {

            return;

        }

        moveTowardsInterest();

    }

    // Move towards our interest
    private void moveTowardsInterest() {

        updateInterest();

        System.out.println("ha");

        Map.Direction directionToMove = NavigationUtilities.getDirectionToMove((Entity) memory.getNPC(), memory.getDecision().getPointOfInterest());
        memory.getNPC().pushAction((entity) -> entity.move(directionToMove));

    }

    // and see if it is close enough to interact with?
    private void updateInterest() {

        memory.getDecision().updateInterest(memory);

    }


}
