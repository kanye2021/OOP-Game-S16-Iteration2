package models.entities;

import controllers.GameViewController;
import controllers.entityControllers.AvatarController;
import controllers.entityControllers.EntityController;
import models.entities.npc.NPC;
import models.map.Map;
import models.stats.StatModificationList;

import java.awt.*;

/**
 * Created by aseber on 2/22/16.
 */
public abstract class Avatar extends Entity {
    private int radiusOfVisiblility;

    public Avatar(Point location, Map map) {
        super(location, map);
        this.radiusOfVisiblility = 4;
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
    @Override
    public void startInteraction(NPC npc){


        return;
    }
}
