package models.entities;

import models.map.Map;
import models.occupation.Occupation;
import models.occupation.Summoner;
import utilities.IOUtilities;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by aseber on 2/22/16.
 */
public class SummonerAvatar extends Avatar {

    public SummonerAvatar(Point location, Map map) {

        super(location, map);
        passableTerrain.add("grass");

    }

    @Override
    protected HashMap<Map.Direction, String> initSprites() {

        String imageBasePath = IOUtilities.getFileSystemDependentPath("./src/res/entitys/entity-summoner-");

        HashMap<Map.Direction, String> imagePaths = new HashMap<>();

        imagePaths.put(Map.Direction.NORTH, imageBasePath + "N.png");
        imagePaths.put(Map.Direction.NORTH_EAST, imageBasePath + "NE.png");
        imagePaths.put(Map.Direction.SOUTH_EAST, imageBasePath + "SE.png");
        imagePaths.put(Map.Direction.SOUTH, imageBasePath + "S.png");
        imagePaths.put(Map.Direction.SOUTH_WEST, imageBasePath + "W.png");
        imagePaths.put(Map.Direction.NORTH_WEST, imageBasePath + "NW.png");

        return imagePaths;

    }

    protected Occupation initOccupation() {

        return new Summoner();

    }
    @Override
    public final String getType() {

        return "Summoner" + "-" + super.getType();
        //return "Summoner_avatar";
    }

}
