package models.entities;

import models.map.Map;
import models.occupation.Occupation;
import models.occupation.Sneak;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by aseber on 2/22/16.
 */
public class SneakAvatar extends Avatar {

    public SneakAvatar(Point location, Map map) {

        super(location, map);

    }

    @Override
    protected HashMap<Map.Direction, String> initSprites() {

        HashMap<Map.Direction, String> imagePaths = new HashMap<>();

        imagePaths.put(Map.Direction.NORTH, "");
        imagePaths.put(Map.Direction.NORTH_EAST, "");
        imagePaths.put(Map.Direction.SOUTH_EAST, "");
        imagePaths.put(Map.Direction.SOUTH, "");
        imagePaths.put(Map.Direction.SOUTH_WEST, "");
        imagePaths.put(Map.Direction.NORTH_WEST, "");

        return imagePaths;

    }

    @Override
    protected Occupation initOccupation() {

        return new Sneak();

    }

}
