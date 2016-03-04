package models.entities.npc;

import models.map.Map;
import models.occupation.Occupation;
import models.occupation.Sneak;
import utilities.IOUtilities;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by denzel on 3/3/16.
 */
public class MountEntity extends NPC{
    public MountEntity(Point location, Map map){
        super(location,map);
        passableTerrain.add("grass");
        initActions();
    }


    public void initActions(){
        actionList.add(new Talk(this,"Thuy sucks"));
        actionList.add(new Mount(this));
        actionList.add(new Unmount(this));
    }

    @Override
    public void startInteraction(NPC npc) {

    }

    @Override
    protected HashMap<Map.Direction, String> initSprites() {
        String imageBasePath = IOUtilities.getFileSystemDependentPath("./src/res/entitys/entity-reddragon-");

        HashMap<Map.Direction, String> imagePaths = new HashMap<>();
        imagePaths.put(Map.Direction.NORTH, imageBasePath + "N.png");
        imagePaths.put(Map.Direction.NORTH_EAST, imageBasePath + "NE.png");
        imagePaths.put(Map.Direction.SOUTH_EAST, imageBasePath + "SE.png");
        imagePaths.put(Map.Direction.SOUTH, imageBasePath + "S.png");
        imagePaths.put(Map.Direction.SOUTH_WEST, imageBasePath + "W.png");
        imagePaths.put(Map.Direction.NORTH_WEST, imageBasePath + "NW.png");

        return imagePaths;
    }

    @Override
    protected Occupation initOccupation() {
        return new Sneak();
    }
}
