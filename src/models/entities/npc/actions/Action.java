package models.entities.npc.actions;

import controllers.ViewController;
import models.entities.Avatar;
import models.entities.npc.NPC;
import views.View;


/**
 * Created by dyeung on 2/28/16.
 */
public abstract class Action {

    //Actions occur between two people
    protected NPC npc; //Thing being interacted
    protected Avatar avatar; //Thing that is doing the interaction

    public Action(NPC npc) {
        this.npc = npc;
    }

    public abstract void activate(View view, ViewController viewController);

    public abstract String getName();

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
}
