package models.entities.npc.actions;

import models.entities.Avatar;
import models.entities.npc.NPC;
import models.entities.npc.actions.Action;

/**
 * Created by dyeung on 2/28/16.
 */
public class Talk extends Action {
    //Idea is that NPCS don't talk to each other, only avatar -> npc
    public Talk(NPC npc){
        super(npc);
    }
    public void activate(){
        startTalk();
    }
    public void startTalk(){
        //TODO: Possibly start the view here

    }
    @Override
    public String getName() {
        return "Talk";
    }

}
