package models.entities.npc.actions;

import controllers.GameViewController;
import controllers.NPCInteractions.NPCItemUseController;
import controllers.ViewController;
import models.entities.Avatar;
import models.entities.npc.NPC;
import utilities.SubState;
import utilities.Task;
import views.InventoryView;
import views.View;

/**
 * Created by David on 3/11/2016.
 */
public class UseItemOnNPC extends Action {

    public UseItemOnNPC(NPC npc) {
        super(npc);
    }

    public void activate(View view, ViewController viewController) {
        //Display Dialogue
        InventoryView inventoryView = new InventoryView(view.getScreenWidth(), view.getScreenHeight(), view.getDisplay());
        inventoryView.setInventory(avatar.getInventory());
        NPCItemUseController npcItemUseController = new NPCItemUseController(inventoryView, viewController.getStateManager(), avatar, npc);
        SubState useState = new SubState(npcItemUseController, inventoryView);
        npcItemUseController.setClose(new Task() {
            @Override
            public void run() {
                useState.dismiss();
            }

            @Override
            public void stop() {
            }
        });
        ((GameViewController) viewController).addSubState(useState);
    }

    @Override
    public String getName() {
        return "Use Item";
    }

    @Override
    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
}
