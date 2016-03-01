package models.entities.npc;

import models.entities.Avatar;

/**
 * Created by dyeung on 2/28/16.
 */
public class Attack extends Action {
    public Attack(NPC npc){
        super(npc);
    }
    public void activate(){

    }

    @Override
    public String getName() {
        return "Attack";
    }

}
