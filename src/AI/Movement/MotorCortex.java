package AI.Movement;

import AI.Memory.Memory;
import models.entities.npc.NPC;
import models.map.Map;
import utilities.NavigationUtilities;

import java.util.Random;

/**
 * Created by Bradley on 3/5/16.
 */
public class MotorCortex {
    double rand;
    //Needed so the toast doesn't constantly pop up
    private Random rng = new Random();
    private NPC npc;
    private Memory memory;

    public MotorCortex(NPC npc, Memory memory) {
        this.npc = npc;
        this.memory = memory;
    }

    public void process() {

        rand = rng.nextDouble();
        //TODO Need to figure out the laziness factor
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

        Map.Direction directionToMove = NavigationUtilities.getDirectionToMove(npc, npc.getLocation(), memory.getDecision().getPointOfInterest());
        npc.move(directionToMove);

    }

    // and see if it is close enough to interact with?
    private void updateInterest() {

        memory.getDecision().updateInterest(memory);

    }


}
