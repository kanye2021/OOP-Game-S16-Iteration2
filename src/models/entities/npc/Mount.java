package models.entities.npc;

import models.map.Map;

import java.awt.*;
import java.util.ArrayList;


/**
 * Created by denzel on 3/1/16.
 */
public abstract class Mount extends NPC {

    //Constructor for the mount with location on a map
    public Mount(Point location, Map map, String ... dialogue){
        super(location,map,dialogue);
    }


    //set Terrain and speed for the mount
    public abstract ArrayList<String> getTerrain();
    public abstract int getMovement();
    public abstract void updateOrientation(Map.Direction direction );

    public String getType() {

        return "Mount" + "-" + super.getType();

    }

}
