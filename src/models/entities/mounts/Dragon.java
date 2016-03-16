package models.entities.mounts;

import models.factions.Faction;
import models.factions.FactionAssociation;
import models.map.Map;
import models.occupation.Occupation;
import models.occupation.Sneak;
import utilities.IOUtilities;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by denzel on 3/1/16.
 */
public class Dragon extends Mount {

    public Dragon(Point location, Map map) {

        super(location, map);
        passableTerrain.add("grass");

    }

    protected final FactionAssociation initFaction() {

        return new FactionAssociation(0.35, Faction.ANIMAL);

    }

    /*@Override
    public void initDialogue() {
        dialogue.add("Yo, I'm a dragon.");
        dialogue.add("Shut up and get on!");
    }*/

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

    /*public void initActions() {
        actionList.add(new Talk(this));
        actionList.add(new Ride(this));
    }*/

    @Override
    protected ArrayList<Image> getAnimatorImages() {


        String imageBasePath = IOUtilities.getFileSystemDependentPath("./src/res/entitys/entity-reddragon-");


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
