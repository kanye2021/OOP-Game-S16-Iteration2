package models.entities;

import models.Map;
import models.NavigationMediator;
import models.occupation.Occupation;
import models.occupation.Smasher;
import models.occupation.Summoner;
import models.stats.StatModificationList;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by aseber on 2/22/16.
 */
public class SummonerAvatar extends Avatar {

    public SummonerAvatar(Point location, Map map) {

        super(location, map);

    }

    @Override
    protected StatModificationList initInitialStats() {

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

        return new Summoner();

    }

}
