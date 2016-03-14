package models.entities.npc.actions;

import controllers.GameViewController;
import controllers.NPCInteractions.NPCShopController;
import controllers.ViewController;
import models.entities.Avatar;
import models.entities.npc.NPC;
import utilities.SubState;
import utilities.Task;
import views.NPCShopView;
import views.View;


/**
 * Created by dyeung on 3/2/16.
 */
public class Trade extends Action {
    public Trade(NPC npc) {
        super(npc);
        avatar = null;
    }

    @Override
    public void activate(View view, ViewController gvController) {
        NPCShopView shopView = new NPCShopView(view.getScreenWidth(), view.getScreenHeight(), view.getDisplay(), npc, avatar);
        NPCShopController shopCtrl = new NPCShopController(shopView, gvController.getStateManager(), (GameViewController) gvController, npc, avatar);
        SubState sub = new SubState(shopCtrl, shopView);
        shopCtrl.setClose(new Task() {
            @Override
            public void run() {
                sub.dismiss();
            }

            @Override
            public void stop() {
            }
        });
        ((GameViewController) gvController).addSubState(sub);

    }

    @Override
    public String getName() {
        return "Trade";
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
}
