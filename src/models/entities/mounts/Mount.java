package models.entities.mounts;

import AI.Brain;
import AI.Personality.Personality;
import models.entities.AI_Interface;
import models.entities.Entity;
import models.map.Map;
import models.stats.StatModification;
import models.stats.StatModificationList;
import models.stats.Stats;

import java.awt.*;
import java.util.ArrayList;


/**
 * Created by denzel on 3/1/16.
 */
public abstract class Mount extends Entity  implements AI_Interface {

    Brain brain;

    //Constructor for the mount with location on a map
    public Mount(Point location, Map map) {
        super(location, map);
        brain = new Brain(this, Personality.DEFAULT);
    }

    protected StatModificationList initInitialStats() {

        return new StatModificationList(
                new StatModification(Stats.Type.LIVES, 1),
                new StatModification(Stats.Type.LEVEL, 6),
                new StatModification(Stats.Type.AGILITY, 12),
                new StatModification(Stats.Type.STRENGTH, 8),
                new StatModification(Stats.Type.INTELLECT, 3),
                new StatModification(Stats.Type.HARDINESS, 14)
        );

    }

    public void update() {

        brain.think();

    }

    public String getType() {

        return "Mount" + "-" + super.getType();

    }


}
