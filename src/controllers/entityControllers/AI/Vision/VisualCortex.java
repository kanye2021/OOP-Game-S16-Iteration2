package controllers.entityControllers.AI.Vision;

import models.entities.Entity;
import models.entities.npc.NPC;
import models.items.Item;
import models.map.Map;

import java.awt.*;

/**
 * Created by Bradley on 3/5/16.
 */
public class VisualCortex {

    private NPC npc;
    private Map map;
    private int visualRange;

    public VisualCortex(NPC npc){
        this.npc = npc;
        this.map = npc.getMap();
        this.visualRange = npc.getRadiusOfVisiblility();
    }

    // Process what is going on around you and add all of that stuff to a VisualInfo objec
    // For now, all visual info is processed 120 degrees in the direction of the orientation.
    public VisualInformation process(){

        VisualInformation visualInfo = new VisualInformation(); // Create an empty visualInfoObject;

        Map.Direction orientation = npc.getOrientation();
        int distanceToProcess = visualRange;
        Point startLocation = orientation.neighbor(npc.getLocation());

        // Determine which directions the entity can see.
        switch(orientation){
            case NORTH:
                processRecursively(visualInfo, Map.Direction.NORTH, visualRange, startLocation);
                processRecursively(visualInfo, Map.Direction.NORTH_EAST, visualRange, startLocation);
                processRecursively(visualInfo, Map.Direction.NORTH_WEST, visualRange, startLocation);
                break;
            case NORTH_EAST:
                processRecursively(visualInfo, Map.Direction.NORTH, visualRange, startLocation);
                processRecursively(visualInfo, Map.Direction.NORTH_EAST, visualRange, startLocation);
                processRecursively(visualInfo, Map.Direction.SOUTH_EAST, visualRange, startLocation);
                break;
            case SOUTH_EAST:
                processRecursively(visualInfo, Map.Direction.SOUTH_EAST, visualRange, startLocation);
                processRecursively(visualInfo, Map.Direction.NORTH_EAST, visualRange, startLocation);
                processRecursively(visualInfo, Map.Direction.SOUTH, visualRange, startLocation);
                break;
            case SOUTH:
                processRecursively(visualInfo, Map.Direction.SOUTH_EAST, visualRange, startLocation);
                processRecursively(visualInfo, Map.Direction.SOUTH, visualRange, startLocation);
                processRecursively(visualInfo, Map.Direction.SOUTH_WEST, visualRange, startLocation);
                break;
            case SOUTH_WEST:
                processRecursively(visualInfo, Map.Direction.SOUTH_WEST, visualRange, startLocation);
                processRecursively(visualInfo, Map.Direction.SOUTH, visualRange, startLocation);
                processRecursively(visualInfo, Map.Direction.NORTH_WEST, visualRange, startLocation);
                break;
            case NORTH_WEST:
                processRecursively(visualInfo, Map.Direction.NORTH_WEST, visualRange, startLocation);
                processRecursively(visualInfo, Map.Direction.SOUTH_WEST, visualRange, startLocation);
                processRecursively(visualInfo, Map.Direction.NORTH, visualRange, startLocation);
                break;
        }

        return visualInfo;
    }

    // For now, the visual cortex only cares about seeing other entities or items. TODO: Make it so the npcs are aware of area effects and terrain.
    private void processRecursively(VisualInformation visualInfo, Map.Direction orientation, int visualRange, Point currentLocation){

        if(visualRange == 0){
            return;
        }

        // Get entities here.
        Entity entity = map.getEntityAt(currentLocation);
        if(entity!=null){
            visualInfo.addEntity(entity);
        }

        // Get items here.
        Item item = map.getItemAt(currentLocation);
        if(item!=null){
            visualInfo.addItem(new Point(currentLocation));
        }

        // Update the currentLocation and visual range;
        visualRange--;
        currentLocation = orientation.neighbor(currentLocation);

        processRecursively(visualInfo, orientation, visualRange, currentLocation);
    }
}
