package models.entities.npc;

import controllers.entityControllers.MountController;
import models.map.Map;
import models.occupation.Occupation;
import models.occupation.Sneak;
import utilities.IOUtilities;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by denzel on 3/1/16.
 */
public class Horse extends Mount {

    //movement and terrain
    private int movement;
    private String terrain;


    public Horse(Point location,Map map){
        super(location,map);
        setTerrain("grass");
        setMovement(30);
    }

    @Override
    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    @Override
    public void setMovement(int movement) {
        this.movement = movement;
    }

    @Override
    public void startInteraction(NPC npc) {

    }

    //Horse has no occuptation
    @Override
    protected Occupation initOccupation() {
        return new Sneak();
    }

    @Override
    protected HashMap<Map.Direction, String> initSprites() {
        System.out.println("YOOOOOO");
        String imageBasePath = IOUtilities.getFileSystemDependentPath("src/res/entitys/pet-samples/raichu/");


        HashMap<Map.Direction, String> imagePaths = new HashMap<>();

        imagePaths.put(Map.Direction.NORTH, imageBasePath + "N.png");
        imagePaths.put(Map.Direction.NORTH_EAST, imageBasePath + "NE.png");
        imagePaths.put(Map.Direction.SOUTH_EAST, imageBasePath + "SE.png");
        imagePaths.put(Map.Direction.SOUTH, imageBasePath + "S.png");
        imagePaths.put(Map.Direction.SOUTH_WEST, imageBasePath + "SW.png");
        imagePaths.put(Map.Direction.NORTH_WEST, imageBasePath + "NW.png");
        return imagePaths;
    }


}
