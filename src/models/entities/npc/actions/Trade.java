package models.entities.npc.actions;

import models.entities.npc.NPC;


/**
 * Created by dyeung on 3/2/16.
 */
public class Trade extends Action {
    public Trade(NPC npc){
        super(npc);
    }
    @Override
    public void activate() {

    }

    @Override
    public String getName() {
        return "Trade";
    }
}
