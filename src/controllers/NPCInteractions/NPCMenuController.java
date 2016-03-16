package controllers.NPCInteractions;

import controllers.GameViewController;
import controllers.ViewController;
import controllers.entityControllers.AvatarController;
import models.entities.characters.avatars.Avatar;
import models.entities.characters.npcs.NPC;
import models.entities.characters.npcs.actions.Action;
import utilities.StateManager;
import utilities.Task;
import utilities.TaskWrapper;
import views.NPCMenuView;
import views.View;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by dyeung on 2/29/16.
 */
public class NPCMenuController extends ViewController {
    private int myOption;
    private Task previousOption;
    private Task nextOption;
    private Task selectOption;
    private Task closeActions;
    private NPC npc;
    private ArrayList<Action> actionList;
    private GameViewController gvController;
    private Avatar avatar;

    public NPCMenuController(View view, StateManager stateManager, GameViewController gvController, NPC npc, AvatarController avatarController) {
        super(view, stateManager);
        myOption = 0;
        this.npc = npc;
        this.actionList = npc.getActionList();
        this.gvController = gvController;
        this.avatar = avatarController.getAvatar();
    }

    @Override
    protected void initKeyPressMapping() {

        previousOption = new Task() {
            @Override
            public void run() {
                if (myOption > 0) {
                    myOption--;
                    ((NPCMenuView) view).updateSelectedOption(myOption);
                }
            }

            @Override
            public void stop() {
            }
        };

        nextOption = new Task() {
            @Override
            public void run() {
                if (myOption < actionList.size()) {
                    myOption++;
                    ((NPCMenuView) view).updateSelectedOption(myOption);
                }
            }

            @Override
            public void stop() {
            }
        };

        selectOption = new Task() {
            @Override
            public void run() {
                //TODO: Not a good method but can't think of anything else
                if (myOption == actionList.size()) { //Checks for the length (if its greater than it must be exit
                    closeActions.run();
                } else {
                    Action a = actionList.get(myOption);
                    a.setAvatar(avatar);
                    a.activate(view, gvController);

                }
            }

            @Override
            public void stop() {
            }
        };

        addKeyPressMapping(new TaskWrapper(previousOption, "Previous"), KeyEvent.VK_UP);
        addKeyPressMapping(new TaskWrapper(nextOption, "Next"), KeyEvent.VK_DOWN);
        addKeyPressMapping(new TaskWrapper(selectOption, "Select"), KeyEvent.VK_ENTER);

    }

    public void setClose(Task task) {
        closeActions = task;
    }
}
