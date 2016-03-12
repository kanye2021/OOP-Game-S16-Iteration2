package controllers.entityControllers.AI.Movement;

import controllers.entityControllers.AI.Memory.MotorInterface;
import models.entities.npc.NPC;
import models.map.Map;
import utilities.NavigationUtilities;

import java.util.Random;

/**
 * Created by Bradley on 3/5/16.
 */
public class MotorCortex {
    //Needed so the toast doesn't constantly pop up
    private Random rng = new Random();
    double rand;
    private NPC npc;
    private MotorInterface memory;

    public MotorCortex(NPC npc, MotorInterface memory){
        this.npc = npc;
        this.memory = memory;
    }

    public void process() {

        rand = rng.nextDouble();

        // If we're lazy, don't do anything for this update.
        if (memory.getPersonality().getLaziness() < rand) {

            //System.out.println(npc.getType() + ": I'm lazy!");
            return;

        }

        moveTowardsInterest();

    }

    // Move towards our interest
    private void moveTowardsInterest() {

        Map.Direction directionToMove = NavigationUtilities.getDirectionToMove(npc, npc.getLocation(), memory.getDecision().getPointOfInterest());
        System.out.println(npc.getType() + ": I moved: " + directionToMove.toString());
        npc.move(directionToMove);

        UpdateInterest();

    }

    // and see if it is close enough to interact with?
    private void UpdateInterest() {



    }

}
