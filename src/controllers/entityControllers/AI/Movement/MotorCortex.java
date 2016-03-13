package controllers.entityControllers.AI.Movement;

import controllers.entityControllers.AI.Memory.Memory;
import controllers.entityControllers.AI.Memory.MotorInterface;
import controllers.entityControllers.AI.Personality.Interests.Interest;
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

    public MotorCortex(NPC npc, Memory memory){
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

            System.err.println("Error! NULL decision in " + npc.getType());
            return;

        }

        moveTowardsInterest();

    }

    // Move towards our interest
    private void moveTowardsInterest() {

        updateInterest();

        Map.Direction directionToMove = NavigationUtilities.getDirectionToMove(npc, npc.getLocation(), memory.getDecision().getPointOfInterest());

        if(memory.getDecision().getInterestType().equals(Interest.Type.ENTITY.toString())){
            npc.move(directionToMove);
        }else if(memory.getDecision().getInterestType().equals(Interest.Type.ITEM.toString())){
            System.out.println("I should be stealing items");
        }else if(memory.getDecision().getInterestType().equals(Interest.Type.NONE.toString())){
            System.out.println("I should be idling");
        }

    }

    // and see if it is close enough to interact with?
    private void updateInterest() {

        //TODO: just me fucking around
        memory.getDecision().updateInterest();

    }





}
