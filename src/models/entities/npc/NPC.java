package models.entities.npc;

import AI.Brain;
import AI.Personality.Personality;
import models.entities.Entity;
import models.entities.npc.actions.Action;
import models.entities.npc.actions.Attack;
import models.entities.npc.actions.Talk;
import models.map.Map;
import models.stats.StatModificationList;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by aseber on 2/22/16.
 */
public abstract class  NPC extends Entity {
    protected ArrayList<Action> actionList;

    protected ArrayList<String> dialogue;
    protected int dialogueLocation;
    protected Brain brain;

    public NPC(Point location, Map map) {
        super(location, map);
        brain = new Brain(this, Personality.DEFAULT); // Agnostic is the default personailty.
        actionList = new ArrayList<>();
        this.dialogue = new ArrayList<>();
        dialogueLocation = 0;
        initNPC();
    }
    protected void initNPC(){
        initActions();
        initDialogue();
    }
    //Override this method if you want it to say something
    protected void initDialogue(){
        this.dialogue.add("....I have nothing to say");
    }
    protected void initActions(){
        actionList.add(new Talk(this));
        actionList.add(new Attack(this));
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
    public void progressDialogue() { dialogueLocation++; }
    public void resetDialogue() { dialogueLocation = 0; }


    public ArrayList<String> getDialogue(){ return dialogue; }
    public int getDialogueLocation(){ return dialogueLocation;}




    public void update(){
        brain.think();
    }

    public String getType() {

        return "NPC" + "-" + super.getType();

    }
}
