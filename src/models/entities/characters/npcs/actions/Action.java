package models.entities.characters.npcs.actions;

import controllers.ViewController;
import models.entities.characters.avatars.Avatar;
import models.entities.characters.npcs.NPC;
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
