package models.entities.npc;

import models.map.Map;

import java.awt.*;


/**
 * Created by denzel on 3/1/16.
 */
public abstract class Mount extends NPC {

    //Constructor for the mount with location on a map
    public Mount(Point location, Map map){
        super(location,map);
    }


    //set Terrain and speed for the mount
    protected abstract void setTerrain(String terrain);
    protected abstract void setMovement(int movement);


}
