package models.entities.npc;

import AI.Brain;
import AI.Personality.Personality;

import models.entities.Entity;
import models.map.Map;

import java.awt.*;
import java.util.ArrayList;


/**
 * Created by denzel on 3/1/16.
 */
public abstract class Mount extends NPC {

    //Constructor for the mount with location on a map
    public Mount(Point location, Map map){
        super(location,map);
        brain = new Brain(this, Personality.DEFAULT);
    }


    //set Terrain and speed for the mount
    public abstract ArrayList<String> getTerrain();
    public abstract int getMovement();

    public String getType() {

        return "Mount" + "-" + super.getType();

    }


}
