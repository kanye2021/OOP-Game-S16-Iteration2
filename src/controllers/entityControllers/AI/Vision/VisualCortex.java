package controllers.entityControllers.AI.Vision;

import controllers.entityControllers.AI.Memory.VisualInterface;
import models.entities.Entity;
import models.entities.npc.NPC;
import models.items.Item;
import models.map.Map;

import java.awt.*;

/**
 * Created by Bradley on 3/5/16.
 */
public class VisualCortex {

    int cycles;
    private NPC npc;
    private Map map;
    private VisualInterface memory;

    public VisualCortex(NPC npc, VisualInterface memory){
        this.npc = npc;
        this.map = npc.getMap();
        this.memory = memory;
    }

    // Process what is going on around you and add all of that stuff to a VisualInfo objec
    // For now, all visual info is processed 120 degrees in the direction of the orientation.
    public void process() {

        Map.Direction orientation = npc.getOrientation();
        Point startLocation = orientation.neighbor(npc.getLocation());

        // Determine which directions the entity can see.
        switch(orientation){
            case NORTH:
                processRecursively(Map.Direction.NORTH, npc.getRadiusOfVisiblility(), startLocation);
                processRecursively(Map.Direction.NORTH_EAST, npc.getRadiusOfVisiblility(), startLocation);
                processRecursively(Map.Direction.NORTH_WEST, npc.getRadiusOfVisiblility(), startLocation);
                break;
            case NORTH_EAST:
                processRecursively(Map.Direction.NORTH, npc.getRadiusOfVisiblility(), startLocation);
                processRecursively(Map.Direction.NORTH_EAST, npc.getRadiusOfVisiblility(), startLocation);
                processRecursively(Map.Direction.SOUTH_EAST, npc.getRadiusOfVisiblility(), startLocation);
                break;
            case SOUTH_EAST:
                processRecursively(Map.Direction.SOUTH_EAST, npc.getRadiusOfVisiblility(), startLocation);
                processRecursively(Map.Direction.NORTH_EAST, npc.getRadiusOfVisiblility(), startLocation);
                processRecursively(Map.Direction.SOUTH, npc.getRadiusOfVisiblility(), startLocation);
                break;
            case SOUTH:
                processRecursively(Map.Direction.SOUTH_EAST, npc.getRadiusOfVisiblility(), startLocation);
                processRecursively(Map.Direction.SOUTH, npc.getRadiusOfVisiblility(), startLocation);
                processRecursively(Map.Direction.SOUTH_WEST, npc.getRadiusOfVisiblility(), startLocation);
                break;
            case SOUTH_WEST:
                processRecursively(Map.Direction.SOUTH_WEST, npc.getRadiusOfVisiblility(), startLocation);
                processRecursively(Map.Direction.SOUTH, npc.getRadiusOfVisiblility(), startLocation);
                processRecursively(Map.Direction.NORTH_WEST, npc.getRadiusOfVisiblility(), startLocation);
                break;
            case NORTH_WEST:
                processRecursively(Map.Direction.NORTH_WEST, npc.getRadiusOfVisiblility(), startLocation);
                processRecursively(Map.Direction.SOUTH_WEST, npc.getRadiusOfVisiblility(), startLocation);
                processRecursively(Map.Direction.NORTH, npc.getRadiusOfVisiblility(), startLocation);
                break;
        }

    }

    private void processRecursively(Map.Direction orientation, int visualRange, Point currentLocation){
        cycles++;
        if(visualRange == 0){
            return;
        }

        if (!map.isTileValid(currentLocation)) {
            return;
        }

        // Get entities here.
        Entity entity = map.getEntityAt(currentLocation);
        if(entity!=null){
            memory.addVisualInput(entity, currentLocation);
            cycles = 0;
        }

        // Get items here.
        Item item = map.getItemAt(currentLocation);
        if(item!=null){
            memory.addVisualInput(item, currentLocation);
        }

        // update the currentLocation and visual range;
        visualRange--;
        currentLocation = orientation.neighbor(currentLocation);

        processRecursively(orientation, visualRange, currentLocation);
    }

}
