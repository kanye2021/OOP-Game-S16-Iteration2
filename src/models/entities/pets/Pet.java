package models.entities.pets;


import AI.Brain;
import AI.Personality.Personality;
import models.entities.AI_Interface;
import models.entities.Entity;
import models.factions.Faction;
import models.factions.FactionAssociation;
import models.map.Map;
import models.occupation.Occupation;
import models.occupation.Sneak;
import models.stats.StatModification;
import models.stats.StatModificationList;
import models.stats.Stats;
import utilities.IOUtilities;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sergiopuleri on 2/27/16.
 */
public class Pet extends Entity implements AI_Interface {

    Brain brain;

    public Pet(Point location, Map map) {
        super(location, map);
        passableTerrain.add("grass");
        brain = new Brain(this, Personality.DOGE); // Agnostic is the default personailty.
        this.orientation = Map.Direction.NORTH;
    }

    @Override
    protected final StatModificationList initInitialStats() {

        return new StatModificationList(
                new StatModification(Stats.Type.LIVES, 1),
                new StatModification(Stats.Type.LEVEL, 6),
                new StatModification(Stats.Type.AGILITY, 12),
                new StatModification(Stats.Type.STRENGTH, 8),
                new StatModification(Stats.Type.INTELLECT, 3),
                new StatModification(Stats.Type.HARDINESS, 14)
        );

    }

    // Whenever Avatar moves Pet will follow, etc

    @Override
    protected Occupation initOccupation() {
        // Pets have no occupation?
        // Pets can be SNeaks i guess for now.
        return new Sneak();
    }


    protected final FactionAssociation initFaction() {

        return new FactionAssociation(1.0, Faction.BLUE);

    }

    public final void update() {

        System.out.println("ha");
        brain.think();

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

    public void talk() {

    }

    @Override
    public String getType() {

        return "Pet" + "-" + super.getType();

    }

    @Override
    protected ArrayList<Image> getAnimatorImages() {
        String imageBasePath = IOUtilities.getFileSystemDependentPath("./src/res/entitys/pet-samples/raichu/");
        ArrayList<Image> imagePaths = new ArrayList<>();

        imagePaths.add(new ImageIcon(imageBasePath + "N.png").getImage());
        imagePaths.add(new ImageIcon(imageBasePath + "NE.png").getImage());
        imagePaths.add(new ImageIcon(imageBasePath + "NW.png").getImage());
        imagePaths.add(new ImageIcon(imageBasePath + "S.png").getImage());
        imagePaths.add(new ImageIcon(imageBasePath + "SE.png").getImage());
        imagePaths.add(new ImageIcon(imageBasePath + "SW.png").getImage());

        return imagePaths;
    }
}
