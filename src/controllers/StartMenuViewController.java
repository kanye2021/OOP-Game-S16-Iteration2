package controllers;

import utilities.State;
import utilities.StateManager;
import utilities.Task;
import utilities.TaskWrapper;
import views.*;

import java.awt.event.KeyEvent;

/**
 * Created by Bradley on 2/17/16.
 */
public class StartMenuViewController extends ViewController {

    private Task previousOption;
    private Task nextOption;
    private Task selectOption;

    public StartMenuViewController(View view, StateManager stateManager){
        super(view, stateManager);
    }

    @Override
    protected final void initKeyPressMapping() {

        previousOption = new Task() {
            @Override
            public void run() {
                ((StartMenuView)view).previousOption();
            }

            @Override
            public void stop() {}
        };

        nextOption = new Task() {
            @Override
            public void run() {
                ((StartMenuView)view).nextOption();
            }

            @Override
            public void stop() {}
        };

        selectOption = new Task() {
            @Override
            public void run() {
                State nextState;

                switch (((StartMenuView)view).getSelected()) {
                    case CREATE_GAME:
                        AvatarCreationView avatarCreationView = new AvatarCreationView(view.getScreenWidth(), view.getScreenHeight(), view.getDisplay());
                        AvatarCreationViewController avatarCreationViewController = new AvatarCreationViewController(avatarCreationView, stateManager);
                        nextState = new State(avatarCreationViewController, avatarCreationView);
                        stateManager.setActiveState(nextState);
                        break;
                    case LOAD_GAME:
                        LoadGameView loadGameView = new LoadGameView(view.getScreenWidth(), view.getScreenHeight(), view.getDisplay());
                        LoadGameViewController loadGameViewController = new LoadGameViewController(loadGameView, stateManager);
                        nextState = new State(loadGameViewController, loadGameView);
                        stateManager.setActiveState(nextState);
                        break;
                    case EXIT_GAME:
                        System.exit(0);

                }
            }

            @Override
            public void stop() {}
        };

        addKeyPressMapping(new TaskWrapper(previousOption, "Previous"), KeyEvent.VK_UP);
        addKeyPressMapping(new TaskWrapper(nextOption, "Next"), KeyEvent.VK_DOWN);
        addKeyPressMapping(new TaskWrapper(selectOption, "Select"), KeyEvent.VK_ENTER);

    }
}

