package models.entities.npc;

import controllers.entityControllers.AI.Brain;
import controllers.entityControllers.AI.Thought.Personalities;
import models.entities.Entity;
import models.entities.npc.actions.Action;
import models.map.Map;
import models.stats.StatModificationList;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by aseber on 2/22/16.
 */
public abstract class NPC extends Entity {
    protected ArrayList<Action> actionList;

    protected Brain brain;

    protected String dialogue;
    public NPC(Point location, Map map) {
        super(location, map);
        actionList = new ArrayList<>();
        brain = new Brain(this, Personalities.AGNOSTIC); // Agnostic is the default personailty.
        dialogue = "..."; //Default dialogue for each npc
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
    //Starts the interaction between entities (For now it is also showcasing the view list
    public void startInteraction(){
        System.out.println("Starts interaction with npc");
    }
    public ArrayList<Action> getActionList(){
        return actionList;
    }




    public void update(){
        super.update();
        brain.think();
    }

    public String getType() {

        return "NPC" + "-" + super.getType();

    }

}
