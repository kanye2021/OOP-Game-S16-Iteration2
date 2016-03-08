package models.entities.npc;

import models.entities.Avatar;
import sun.security.x509.AVA;
import views.Display;

/**
 * Created by dyeung on 2/28/16.
 */
public abstract class Action {


    //Actions occur between two people
    protected NPC npc;

    public Action(NPC npc){
        this.npc = npc;
    }

    //TODO: I dont htink this should be public but it works.
    public abstract void activate();
    public abstract String getName();
}
