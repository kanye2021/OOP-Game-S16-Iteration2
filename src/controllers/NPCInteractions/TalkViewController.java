package controllers.NPCInteractions;

import controllers.GameViewController;
import controllers.ViewController;
import models.entities.npc.NPC;
import utilities.StateManager;
import utilities.Task;
import views.View;

import java.awt.event.KeyEvent;

/**
 * Created by david on 3/4/16.
 */
public class TalkViewController extends ViewController {

    //Tasks
    private Task continueText;


    //Stuff
    private NPC npc;
    private GameViewController gameViewController;

    public TalkViewController(View view, StateManager stateManager, GameViewController gameViewController, NPC npc) {
        super(view, stateManager);
        this.npc = npc;
        this.gameViewController = gameViewController;
    }

    @Override
    public void initKeyPressMapping(){
        continueText = new Task(){
            @Override
            public void run(){
                npc.progressDialogue();
                if(npc.getDialogueLocation() == npc.getDialogue().size()){
                    gameViewController.turnOffSubState();
                    npc.resetDialogue();
                }
            }

            @Override
            public void stop(){}
        };

        addKeyPressMapping(continueText, KeyEvent.VK_ENTER);
    }

    public TalkViewController getTalkViewController(){
        return this;
    }
}
