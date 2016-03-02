package models.entities.npc;

import models.entities.Avatar;

/**
 * Created by dyeung on 2/28/16.
 */
public class Talk extends Action{
    private String dialogue;
    public Talk(NPC npc, String text){
        super(npc);
        dialogue = text;
    }
    public void activate(){
        startTalk();
    }
    public void startTalk(){

    }
    @Override
    public String getName() {
        return "Talk";
    }
}
