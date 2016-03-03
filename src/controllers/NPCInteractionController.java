package controllers;

import controllers.ViewController;
import models.entities.Avatar;
import models.entities.npc.Action;
import models.entities.npc.NPC;
import utilities.StateManager;
import utilities.Task;
import views.LoadGameView;
import views.NPCActionView;
import views.View;

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

    //testing something out here
    private Avatar avatar;

    public NPCInteractionController(View view, StateManager stateManager, NPC npc, Avatar avatar) {
        super(view, stateManager);
        myOption = 0;
        this.npc = npc;
        this.avatar = avatar;
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
