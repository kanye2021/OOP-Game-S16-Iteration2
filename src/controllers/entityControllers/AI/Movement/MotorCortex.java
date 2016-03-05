package controllers.entityControllers.AI.Movement;

import controllers.entityControllers.AI.Thought.Decision;
import models.entities.Entity;
import models.entities.npc.NPC;
import models.map.Map;

import java.awt.*;

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
                System.out.println("ATTACK!!!!");
                // TODO: Implement the attack (How to know if I can do a ranged attack, melee attack ,etc.)

                // Walk towards the entity
                directionToMove = getDirectionFromAtoB(npc.getLocation(), entity.getLocation());
                break;
            case TRADE:
                // Announce (make a toast) that I would like to trade.
                entity = (Entity) decision.getAttachment();
                System.out.println("TRADEEEE");
                directionToMove = getDirectionFromAtoB(npc.getLocation(), entity.getLocation());
                // Make a toast that says "Hey I would like to trade with you!"
                break;
            case GET_ITEM:
                // Walk to the item;
                itemLocation = (Point) decision.getAttachment();
                System.out.println("GET ITEM!!!!");

                // Determine which direction to walk.
                directionToMove = getDirectionFromAtoB(npc.getLocation(), itemLocation);
                break;
            case DEFAULT:
                // TODO: Implement default behavior (maybe passing back and forth, spinning around, etc.)
//                Map.Direction orientation = nextOption(npc.getOrientation());
//                npc.setOrientation(orientation);
//                System.out.println("SPINNING");
                npc.stopMoving();
                break;
        }

        // Move
        if(directionToMove!= null){
            System.out.println("MOVING: " + directionToMove);
            npc.move(directionToMove);
        }
    }
    // Useful for making an NPC spin around
    public Map.Direction nextOption(Map.Direction orientation) {
        if (orientation.ordinal() == Map.Direction.values().length - 1) {
            return Map.Direction.values()[0];
        } else {
            return Map.Direction.values()[orientation.ordinal() + 1];
        }
    }

    // NOTE: We are using axial coordinates, so major axis (x and y) are not quite as intuitive as they would be in cartesian.
    private Map.Direction getDirectionFromAtoB(Point a, Point b){
        int dx = (int) (b.getX() - a.getX());
        int dy = (int) (b.getY() - a.getY());

        // First see if we can move along a major axis;
        if(dx == 0){
            if(dy < 0){
                return Map.Direction.NORTH;
            }else if(dy > 0){
                return Map.Direction.SOUTH;
            }else{
                return null;
            }
        }else if(dy == 0){
            if(dx < 0){
                return Map.Direction.NORTH_WEST;
            }else if(dy > 0){
                return Map.Direction.SOUTH_EAST;
            }else{
                return null;
            }
        }else if(dy > dx){
            return Map.Direction.SOUTH_WEST;
        }else{
            return Map.Direction.NORTH_EAST;
        }
    }
}
