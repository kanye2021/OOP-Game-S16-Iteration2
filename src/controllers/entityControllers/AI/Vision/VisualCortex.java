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

    public VisualCortex(NPC npc){
        this.npc = npc;
        this.map = npc.getMap();
    }

    // Process what is going on around you and add all of that stuff to a VisualInfo objec
    // For now, all visual info is processed 120 degrees in the direction of the orientation.
    public VisualInformation process(){

        VisualInformation visualInfo = new VisualInformation(); // Create an empty visualInfoObject;

        Map.Direction orientation = npc.getOrientation();
        Point startLocation = orientation.neighbor(npc.getLocation());

        // Determine which directions the entity can see.
        switch(orientation){
            case NORTH:
                processRecursively(visualInfo, Map.Direction.NORTH, startLocation);
                processRecursively(visualInfo, Map.Direction.NORTH_EAST, startLocation);
                processRecursively(visualInfo, Map.Direction.NORTH_WEST, startLocation);
                break;
            case NORTH_EAST:
                processRecursively(visualInfo, Map.Direction.NORTH, startLocation);
                processRecursively(visualInfo, Map.Direction.NORTH_EAST, startLocation);
                processRecursively(visualInfo, Map.Direction.SOUTH_EAST, startLocation);
                break;
            case SOUTH_EAST:
                processRecursively(visualInfo, Map.Direction.SOUTH_EAST, startLocation);
                processRecursively(visualInfo, Map.Direction.NORTH_EAST, startLocation);
                processRecursively(visualInfo, Map.Direction.SOUTH, startLocation);
                break;
            case SOUTH:
                processRecursively(visualInfo, Map.Direction.SOUTH_EAST, startLocation);
                processRecursively(visualInfo, Map.Direction.SOUTH, startLocation);
                processRecursively(visualInfo, Map.Direction.SOUTH_WEST, startLocation);
                break;
            case SOUTH_WEST:
                processRecursively(visualInfo, Map.Direction.SOUTH_WEST, startLocation);
                processRecursively(visualInfo, Map.Direction.SOUTH, startLocation);
                processRecursively(visualInfo, Map.Direction.NORTH_WEST, startLocation);
                break;
            case NORTH_WEST:
                processRecursively(visualInfo, Map.Direction.NORTH_WEST, startLocation);
                processRecursively(visualInfo, Map.Direction.SOUTH_WEST, startLocation);
                processRecursively(visualInfo, Map.Direction.NORTH, startLocation);
                break;
        }

        return visualInfo;
    }

    // For now, the visual cortex only cares about seeing other entities or items. TODO: Make it so the npcs are aware of area effects and terrain.
    private void processRecursively(VisualInformation visualInfo, Map.Direction orientation, Point currentLocation){

        int visualRange = 1;

        if(visualRange == npc.getRadiusOfVisiblility()){
            return;
        }

        if (!map.isTileValid(currentLocation)) {
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
        visualRange++;
        currentLocation = orientation.neighbor(currentLocation);

        processRecursively(visualInfo, orientation, visualRange, currentLocation);
    }

    private void processRecursively(VisualInformation visualInfo, Map.Direction orientation, int visualRange, Point currentLocation){

        if(visualRange == npc.getRadiusOfVisiblility()){
            return;
        }

        if (!map.isTileValid(currentLocation)) {
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
        visualRange++;
        currentLocation = orientation.neighbor(currentLocation);

        processRecursively(visualInfo, orientation, visualRange, currentLocation);
    }

}
