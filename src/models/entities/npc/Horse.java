package models.entities.npc;


import controllers.entityControllers.EntityController;
import models.entities.Entity;
import models.map.Map;
import models.occupation.Occupation;
import models.occupation.Sneak;
import models.stats.StatModificationList;
import utilities.IOUtilities;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by denzel on 3/1/16.
 */
public class Horse extends MountEntity {

    //movement and terrain
    private int movement;
    private String terrain;


    public Horse(Point location,Map map){
        super(location,map);
        System.out.println("I am in the Horse constructor");
    }


    //TODO I don't think the horse should do any interaction with the Entity from its end. I dont know yet.
    @Override
    public void startInteraction(NPC npc) {

    }


    //Horse has no occuptation
    @Override
    protected Occupation initOccupation() {
        return new Sneak();
    }


    @Override
    public void initActions() {
        System.out.println("WTF");
        
        actionList.add(new Mount(this));
    }

    protected HashMap<Map.Direction, String> initSprites(){
        String imageBasePath = IOUtilities.getFileSystemDependentPath("./src/res/entitys/entity-summoner-");

        HashMap<Map.Direction, String> imagePaths = new HashMap<>();
        imagePaths.put(Map.Direction.NORTH, imageBasePath + "N.png");
        imagePaths.put(Map.Direction.NORTH_EAST, imageBasePath + "NE.png");
        imagePaths.put(Map.Direction.SOUTH_EAST, imageBasePath + "SE.png");
        imagePaths.put(Map.Direction.SOUTH, imageBasePath + "S.png");
        imagePaths.put(Map.Direction.NORTH_WEST, imageBasePath + "NW.png");

        System.out.println("I assigned the sprites");
        return imagePaths;
    }



}
