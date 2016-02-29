package models.entities;


import controllers.entityControllers.EntityController;
import models.map.Map;
import models.occupation.Occupation;
import models.occupation.Sneak;
import models.skills.SkillList;
import models.stats.StatModificationList;
import utilities.IOUtilities;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by sergiopuleri on 2/27/16.
 */
public class Pet extends NPC {

    public Pet(Point location, Map map) {
        super(location, map);
        passableTerrain.add("grass");
    }

    // Controller will be a "PetController" which implements Observer and observes the Avatar's location
    // Whenever Avatar moves Pet will follow, etc

    @Override
    protected Occupation initOccupation() {
        // Pets have no occupation?
        // Pets can be SNeaks i guess for now.
        return new Sneak();
    }

    @Override
    protected StatModificationList initInitialStats() {
        // Pets have no stats ??
        StatModificationList initialStats = new StatModificationList(
            /*new StatModification(Stats.Type.LIVES, 3, StatModification.NumberType.POINT),
            new StatModification(Stats.Type.LEVEL, 1, StatModification.NumberType.POINT),
            new StatModification(Stats.Type.AGILITY, 10, StatModification.NumberType.POINT),
            new StatModification(Stats.Type.STRENGTH, 10, StatModification.NumberType.POINT),
            new StatModification(Stats.Type.INTELLECT, 10, StatModification.NumberType.POINT),
            new StatModification(Stats.Type.HARDINESS, 10, StatModification.NumberType.POINT)*/
        );

        return initialStats;
    }

    @Override
    protected HashMap<Map.Direction, String> initSprites() {
        String imageBasePath = IOUtilities.getFileSystemDependentPath("./src/res/entitys/pet-samples/raichu/");


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