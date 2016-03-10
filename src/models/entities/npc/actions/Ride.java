package models.entities.npc.actions;

import controllers.GameViewController;
import controllers.ViewController;
import models.entities.Avatar;
import models.entities.npc.Mount;
import models.entities.npc.NPC;
import views.GameView;
import views.NPCMenuView;
import views.View;

/**
 * Created by dyeung on 3/9/16.
 */
public class Ride extends Action {
    public Ride(NPC npc){
        super(npc);
    }
    @Override
    public void activate(View view, ViewController viewController) {
        startRide();
        ((GameViewController)viewController).popTopSubState();
    }

    @Override
    public String getName() {
        return "Ride";
    }

    @Override
    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
    public void startRide(){
        avatar.setMount((Mount)npc);

    }
}
