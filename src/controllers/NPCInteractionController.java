package controllers;

import controllers.ViewController;
import models.entities.npc.Action;
import models.entities.npc.NPC;
import models.entities.npc.Talk;
import utilities.*;
import views.*;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by dyeung on 2/29/16.
 */
public class NPCInteractionController extends ViewController {
    private int myOption;
    private Task previousOption;
    private Task nextOption;
    private Task selectOption;
    private NPC npc;
    private ArrayList<Action> actionList;

    public NPCInteractionController(View view, StateManager stateManager, NPC npc) {
        super(view, stateManager);
        myOption = 0;
        this.npc = npc;
        this.actionList = npc.getActionList();
    }

    @Override
    protected void initKeyPressMapping() {

        previousOption = new Task() {
            @Override
            public void run() {
                if (myOption > 0) {
                    myOption--;
                    ((NPCActionView) view).updateSelectedOption(myOption);

                }
            }

            @Override
            public void stop() {}
        };

        nextOption = new Task() {
            @Override
            public void run() {
                if (myOption < actionList.size() - 1){
                    myOption++;

                    ((NPCActionView) view).updateSelectedOption(myOption);

                }
            }
            @Override
            public void stop() {}
        };

        selectOption = new Task() {
            @Override
            public void run() {
                System.out.println("in action");
                if(actionList.get(myOption).getName() == "Talk"){
                    TalkView talkView = new TalkView(view.getScreenWidth(), view.getScreenHeight(),view.getDisplay(), ((Talk) (actionList.get(myOption))).getDialogue().get(0));
                    TalkViewController talkViewController = new TalkViewController(talkView, stateManager, (Talk) (actionList.get(myOption)));
                    SubState talkState = new SubState(talkViewController, talkView);
                    GameViewController gameViewController = (GameViewController) (getStateManager().top().getViewController());
                    GameView gameView = (GameView) (getStateManager().top().getView());
                    stateManager.setActiveTalkState(talkState);
                    //gameView.clearSubStates();
                    gameViewController.addSubState(talkState);
                    gameViewController.setSubController(talkState.getViewController());
                    gameView.renderNPCAction(false);
                    return;
                }
                actionList.get(myOption).activate();
            }

            @Override
            public void stop() {}

        };

        addKeyPressMapping(previousOption, KeyEvent.VK_UP);
        addKeyPressMapping(nextOption, KeyEvent.VK_DOWN);
        addKeyPressMapping(selectOption, KeyEvent.VK_ENTER);

    }
}
