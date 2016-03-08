package models.entities.npc.actions;

import models.entities.Avatar;
import models.entities.npc.NPC;
import sun.security.x509.AVA;
import views.Display;

/**
 * Created by dyeung on 2/28/16.
 */
public abstract class Action {

    //Actions occur between two people
    protected NPC npc; //Thing being interacted
    public Action(NPC npc){
        this.npc = npc;
    }
    public abstract void activate();
    public abstract String getName();
}
