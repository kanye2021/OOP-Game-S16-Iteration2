package models.entities.npc;

import controllers.entityControllers.EntityController;
import controllers.entityControllers.NPCController;
import models.map.Map;
import models.occupation.Occupation;
import models.occupation.Smasher;
import models.skills.SkillList;
import models.stats.StatModificationList;
import utilities.IOUtilities;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by dyeung on 2/28/16.
 */
public class Villager extends NPC{

    public Villager(Point location, Map map) {

        super(location, map);
        passableTerrain.add("grass");
        initActions();
    }
    //---------NPC Stuff------------
    public void initActions(){
        actionList.add(new Talk(this, "Blah blah"));
        actionList.add (new Attack(this));
    }

    ///------------Entity Stuff--------------
    //TODO: For now smashers are villagers
    protected Occupation initOccupation(){
        return new Smasher();
    }

    protected HashMap<Map.Direction, String> initSprites(){
        String imageBasePath = IOUtilities.getFileSystemDependentPath("./src/res/entitys/entity-shopkeeper-");

        HashMap<Map.Direction, String> imagePaths = new HashMap<>();
        imagePaths.put(Map.Direction.NORTH, imageBasePath + "N.png");
        imagePaths.put(Map.Direction.NORTH_EAST, imageBasePath + "NE.png");
        imagePaths.put(Map.Direction.SOUTH_EAST, imageBasePath + "SE.png");
        imagePaths.put(Map.Direction.SOUTH, imageBasePath + "S.png");
        imagePaths.put(Map.Direction.SOUTH_WEST, imageBasePath + "W.png");
        imagePaths.put(Map.Direction.NORTH_WEST, imageBasePath + "NW.png");

        return imagePaths;
    }


}
