package models.entities.npc;

import models.entities.Entity;
import models.map.Map;
import utilities.Task;

import java.awt.*;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by denzel on 3/3/16.
 */

//The Npc is what youre INTERACTING WITH

public class Mount extends Action {

    //I think I need this
    Task moveNorth;
    Task moveNorthWest;
    Task moveSouthWest;
    Task moveSouth;
    Task moveSouthEast;
    Task moveNorthEast;
    private HashMap<Task, Point> taskMovementVector = new HashMap<>();


    public Mount(NPC npc){
        super(npc);

    }

    @Override
    public String getName() {
        return "Mount";
    }

    @Override
    public void activate() {
       mount();
    }


    public void mount(){

    }



}
