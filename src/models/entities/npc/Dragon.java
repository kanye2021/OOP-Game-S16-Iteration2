package models.entities.npc;

import controllers.entityControllers.AI.MountBrain;
import controllers.entityControllers.AI.Thought.Personalities;
import models.entities.npc.actions.Ride;
import models.entities.npc.actions.Talk;
import models.map.Map;
import models.occupation.Occupation;
import models.occupation.Sneak;
import utilities.IOUtilities;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by denzel on 3/1/16.
 */
public class Dragon extends Mount {

    //movement and terrain
    private int movement;
    private ArrayList<String> terrainTypes;

    public Dragon(Point location,Map map) {
        super(location, map);
        passableTerrain.add("grass");
        terrainTypes = new ArrayList<>();

        movement = 10;
        terrainTypes.add("mountain");

    }

    @Override
    public ArrayList<String> getTerrain() {
        return terrainTypes;
    }

    @Override
    public int getMovement() {
        return movement;
    }

    @Override
    public void startInteraction(NPC npc) {

    }
    @Override
    public void initDialogue(){
        dialogue.add("Yo, I'm a dragon.");
        dialogue.add("Shut up and get on!");
    }
    //Horse has no occuptation
    @Override
    protected Occupation initOccupation() {
        return new Sneak();
    }

    @Override
    protected HashMap<Map.Direction, String> initSprites() {
        String imageBasePath = IOUtilities.getFileSystemDependentPath("src/res/entitys/entity-reddragon-");


        HashMap<Map.Direction, String> imagePaths = new HashMap<>();

        imagePaths.put(Map.Direction.NORTH, imageBasePath + "N.png");
        imagePaths.put(Map.Direction.NORTH_EAST, imageBasePath + "NE.png");
        imagePaths.put(Map.Direction.SOUTH_EAST, imageBasePath + "SE.png");
        imagePaths.put(Map.Direction.SOUTH, imageBasePath + "S.png");
        imagePaths.put(Map.Direction.SOUTH_WEST, imageBasePath + "SW.png");
        imagePaths.put(Map.Direction.NORTH_WEST, imageBasePath + "NW.png");
        return imagePaths;
    }
    @Override
    public String getType() {

        //return "Horse" + "-" + super.getType();
        return "Dragon";
    }
    public void initActions(){
        actionList.add(new Talk(this));
        actionList.add(new Ride(this));
    }
    @Override
    public void updateOrientation(Map.Direction direction){
        orientation = direction;
    }

}
