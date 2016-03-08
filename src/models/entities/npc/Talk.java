package models.entities.npc;

import controllers.TalkViewController;
import models.entities.Avatar;
import views.Display;
import views.TalkView;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by dyeung on 2/28/16.
 */
public class Talk extends Action{
    private ArrayList <String>  dialogue = new ArrayList<>();

    //Says how many string you have to display
    private int endDialogue;

    public Talk(NPC npc, String ... dialogue ){
        super(npc);
        for(String text: dialogue){
            this.dialogue.add(text);
        }

        endDialogue = this.dialogue.size();
    }

    public void activate(){ startTalk(); }

    public void startTalk() {
    }

    @Override
    public String getName() {
        return "Talk";
    }

    public ArrayList<String> getDialogue() { return dialogue; }

}
