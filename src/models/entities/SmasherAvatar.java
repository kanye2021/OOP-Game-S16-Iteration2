package models.entities;

import models.Map;
import models.NavigationMediator;
import models.occupation.Occupation;
import models.occupation.Smasher;
import models.stats.StatModification;
import models.stats.StatModificationList;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * Created by aseber on 2/22/16.
 */

public class SmasherAvatar extends Avatar {

    public SmasherAvatar(Point location, Map map) {

        super(location, map);

    }

    @Override
    protected HashMap<NavigationMediator.Direction, String> initSprites() {

        HashMap<NavigationMediator.Direction, String> imagePaths = new HashMap<>();

        imagePaths.put(NavigationMediator.Direction.NORTH, "");
        imagePaths.put(NavigationMediator.Direction.NORTH_EAST, "");
        imagePaths.put(NavigationMediator.Direction.SOUTH_EAST, "");
        imagePaths.put(NavigationMediator.Direction.SOUTH, "");
        imagePaths.put(NavigationMediator.Direction.SOUTH_WEST, "");
        imagePaths.put(NavigationMediator.Direction.NORTH_WEST, "");

        return imagePaths;

    }

    protected Occupation initOccupation() {

        return new Smasher();

    }
}
