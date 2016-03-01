package models.entities.npc;

import models.entities.Avatar;

/**
 * Created by dyeung on 2/28/16.
 */
public class Talk extends Action{
    private String dialogue;
    public Talk(NPC npc, Avatar avatar, String text){
        super(npc,avatar);
        dialogue = text;
    }
    public void activate(){
        startTalk();
    }
    public void startTalk(){

    }
}
