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

    //overiding function for skills mapping

    @Override
    protected final EntityController initController() {

        return null; // Keyboard controller!

    }

    //TODO need avatar to fix the interaction with other entities.
    @Override
    public void startInteraction(NPC npc){
        /**
         * if the avatar is already at this point
         * then the tileDetection has verified
         * that there is an entity there.
         *
         * now the avatar needs to (or the npc needs to relay) the
         * purpose of the interaction
         *
         *
         * npc has a set of actionlists
         * so if villager has talk and attack
         * then maybe
         *
         *
         * Well the npcInteractionController facilitates the action performed.
         * so pass maybe the avatar as a parameter?
         */

        //get the list of actions??



        return;
    }
}
