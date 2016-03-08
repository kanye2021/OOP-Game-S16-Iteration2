package controllers;

import models.entities.npc.Talk;
import utilities.GameState;
import utilities.StateManager;
import utilities.SubState;
import utilities.Task;
import views.GameView;
import views.TalkView;
import views.View;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by david on 3/4/16.
 */
public class TalkViewController extends ViewController{

    //The dialogue to pass to the new views
    private Talk dialogue;
    private int dialogueProgress;

    //Tasks
    private Task continueText;

    public TalkViewController(View view, StateManager stateManager, Talk dialogue) {
        super(view, stateManager);
        this.dialogue = dialogue;
        dialogueProgress = 0;
    }

    @Override
    public void initKeyPressMapping(){
        continueText = new Task(){
            @Override
            public void run(){
                GameView gameView = (GameView) stateManager.top().getView();
                if(dialogueProgress < dialogue.getDialogue().size()){
                    dialogueProgress++;
                    TalkView talkView = new TalkView(view.getScreenWidth(), view.getScreenHeight(), view.getDisplay(), dialogue.getDialogue().get(dialogueProgress));
                    view = talkView;
                    SubState talkState = new SubState(getTalkViewController(), talkView);
                    stateManager.getActiveTalkState().dismiss();
                    stateManager.setActiveTalkState(talkState);
                    gameView.addSubState(talkState);
                }
                else{
                    stateManager.getActiveTalkState().dismiss();
                    stateManager.setActiveTalkState(null);
                    dialogueProgress = 0;
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
