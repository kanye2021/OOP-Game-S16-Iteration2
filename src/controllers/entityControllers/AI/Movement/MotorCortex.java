package controllers.entityControllers.AI.Movement;

import controllers.entityControllers.AI.Thought.Decision;
import models.entities.Entity;
import models.entities.npc.NPC;
import models.map.Map;

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
                System.out.println("ATTACK!!!!");
                // TODO: Implement the attack (How to know if I can do a ranged attack, melee attack ,etc.)

                // Walk towards the entity
                directionToMove = directionToMove(npc.getLocation(), entity.getLocation());
                break;
            case TRADE:
                // Announce (make a toast) that I would like to trade.
                entity = (Entity) decision.getAttachment();
                System.out.println("TRADEEEE");
                directionToMove = directionToMove(npc.getLocation(), entity.getLocation());
                // Make a toast that says "Hey I would like to trade with you!"
                break;
            case GET_ITEM:
                // Walk to the item;
                itemLocation = (Point) decision.getAttachment();
                System.out.println("GET ITEM!!!!");

                // Determine which direction to walk.
                directionToMove = directionToMove(npc.getLocation(), itemLocation);
                break;
            case FOLLOW:
                System.out.println("Follow");
                entity = (Entity) decision.getAttachment();
                directionToMove = directionToMove(npc.getLocation(), entity.getLocation());
            case DEFAULT:
                // TODO: Implement default behavior (maybe passing back and forth, spinning around, etc.)
                npc.stopMoving();
                break;
        }

        // Move
        if(directionToMove!= null){
            System.out.println("MOVING: " + directionToMove);
            npc.move(directionToMove);
        }
    }

    // NOTE: We are using axial coordinates, so major axis (x and y) are not quite as intuitive as they would be in cartesian.
    private Map.Direction directionToMove(Point a, Point b){

        double currentDistance;
        double minDistance = Double.MAX_VALUE;
        Map.Direction directionToMove = null;

        for(Map.Direction direction : Map.Direction.values()){

            Point newLocation = direction.neighbor(a);

            currentDistance = newLocation.distance(b);
            if(currentDistance <= minDistance){
                minDistance = currentDistance;
                directionToMove = direction;
            }
        }

        return directionToMove;
    }

//    private Map.Direction directionToMove(Point a, Point b){
//        int dx = (int) (b.getX() - a.getX());
//        int dy = (int) (b.getY() - a.getY());
//
//        // First see if we can move along a major axis;
//        if(dx == 0){
//            if(dy < 0){
//                return Map.Direction.NORTH;
//            }else if(dy > 0){
//                return Map.Direction.SOUTH;
//            }else{
//                return null;
//            }
//        }else if(dy == 0){
//            if(dx < 0){
//                return Map.Direction.NORTH_WEST;
//            }else if(dy > 0){
//                return Map.Direction.SOUTH_EAST;
//            }else{
//                return null;
//            }
//        }else if(dy > dx){
//            return Map.Direction.SOUTH_WEST;
//        }else{
//            return Map.Direction.NORTH_EAST;
//        }
//    }
}
