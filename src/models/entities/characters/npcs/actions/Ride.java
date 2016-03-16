package models.entities.characters.npcs.actions;

import controllers.GameViewController;
import controllers.ViewController;
import models.entities.characters.avatars.Avatar;
import models.entities.characters.npcs.NPC;
import views.View;

/**
 * Created by dyeung on 3/9/16.
 */
public class Ride extends Action {
    public Ride(NPC npc) {
        super(npc);
    }

    @Override
    public void activate(View view, ViewController viewController) {
        ((GameViewController) viewController).popTopSubState();
    }

    @Override
    public String getName() {
        return "Ride";
    }

    @Override
    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

}
