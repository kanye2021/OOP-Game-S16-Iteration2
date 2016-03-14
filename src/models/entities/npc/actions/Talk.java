package models.entities.npc.actions;

import controllers.GameViewController;
import controllers.NPCInteractions.TalkViewController;
import controllers.ViewController;
import models.entities.Avatar;
import models.entities.npc.NPC;
import utilities.SubState;
import utilities.Task;
import views.TalkView;
import views.View;

/**
 * Created by dyeung on 2/28/16.
 */
public class Talk extends Action {
    //Idea is that NPCS don't talk to each other, only avatar -> npc
    public Talk(NPC npc) {
        super(npc);
    }

    public void activate(View view, ViewController viewController) {
        //Display Dialogue
        TalkView talkView = new TalkView(view.getScreenWidth(), view.getScreenHeight(), view.getDisplay(), npc);
        TalkViewController talkViewController = new TalkViewController(talkView, viewController.getStateManager(), (GameViewController) viewController, npc);
        SubState talkState = new SubState(talkViewController, talkView);
        talkViewController.setClose(new Task() {
            @Override
            public void run() {
                talkState.dismiss();
            }

            @Override
            public void stop() {
            }
        });
        ((GameViewController) viewController).addSubState(talkState);
    }

    public void startTalk() {
        //TODO: Possibly start the view here

    }

    @Override
    public String getName() {
        return "Talk";
    }

    @Override
    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

}
