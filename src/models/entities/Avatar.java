package models.entities;

import models.entityControllers.EntityController;
import models.Map;
import models.stats.StatModificationList;

import java.awt.*;

/**
 * Created by aseber on 2/22/16.
 */
public abstract class Avatar extends Entity {

    public Avatar(Point location, Map map) {

        super(location, map);

    }

    @Override
    protected final StatModificationList initInitialStats() {

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
    protected final EntityController initController() {

        return null; // Keyboard controller!

    }

}
