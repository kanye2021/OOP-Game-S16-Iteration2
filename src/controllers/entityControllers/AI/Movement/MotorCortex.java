package controllers.entityControllers.AI.Movement;

import controllers.entityControllers.AI.Thought.Decision;
import models.entities.Entity;
import models.entities.npc.NPC;
import models.map.Map;
import utilities.NavigationUtilities;

import java.awt.*;
import java.awt.geom.Arc2D;

/**
 * Created by Bradley on 3/5/16.
 */
public class MotorCortex {

    private NPC npc;

    public MotorCortex(NPC npc){
        this.npc = npc;
    }

    public void process(Decision decision){

        Entity entity;
        Point itemLocation;
        Map.Direction directionToMove = null;

        switch(decision){
            case ATTACK:
                // Get the entity to attack
                entity = (Entity) decision.getAttachment();
                System.out.println(npc.getType() + ": I WILL KILL YOU!!!!");
                // TODO: Implement the attack (How to know if I can do a ranged attack, melee attack ,etc.)

                // Walk towards the entity
                directionToMove = NavigationUtilities.getDirectionToMove(npc, npc.getLocation(), entity.getLocation());
                break;
            case TRADE:
                // Announce (make a toast) that I would like to trade.
                entity = (Entity) decision.getAttachment();
                System.out.println(npc.getType() + ": SEE MY WARES LITTLE BOY");
                directionToMove = NavigationUtilities.getDirectionToMove(npc, npc.getLocation(), entity.getLocation());
                // Make a toast that says "Hey I would like to trade with you!"
                break;
            case GET_ITEM:
                // Walk to the item;
                itemLocation = (Point) decision.getAttachment();
                System.out.println(npc.getType() + ": OOOH SHINY!!!!");

                // Determine which direction to walk.
                directionToMove = NavigationUtilities.getDirectionToMove(npc, npc.getLocation(), itemLocation);
                break;
            case FOLLOW:

                System.out.println(npc.getType() + ": I WANT TO SMELL YOUR BUTT");

                entity = (Entity) decision.getAttachment();
                directionToMove = NavigationUtilities.getDirectionToMove(npc, npc.getLocation(), entity.getLocation());
            case DEFAULT:
                // TODO: Implement default behavior (maybe passing back and forth, spinning around, etc.)
                npc.stopMoving();
                break;
        }

        // Move
        if(directionToMove!= null){

            npc.move(directionToMove);
        }
    }
}
