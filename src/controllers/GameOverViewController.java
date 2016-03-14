package controllers;

import utilities.State;
import utilities.StateManager;
import utilities.Task;
import utilities.TaskWrapper;
import views.GameOverView;
import views.StartMenuView;
import views.View;

import java.awt.event.KeyEvent;

/**
 * Created by Bradley on 2/17/16.
 */
public class GameOverViewController extends ViewController {

    private Task previousOption;
    private Task nextOption;
    private Task selectOption;

    public GameOverViewController(View view, StateManager stateManager) {
        super(view, stateManager);
    }

    @Override
    protected final void initKeyPressMapping() {

        previousOption = new Task() {
            @Override
            public void run() {
                ((GameOverView) view).previousOption();
            }

            @Override
            public void stop() {
            }
        };

        nextOption = new Task() {
            @Override
            public void run() {
                ((GameOverView) view).nextOption();
            }

            @Override
            public void stop() {
            }
        };

        selectOption = new Task() {
            @Override
            public void run() {
                State nextState;

                switch (((GameOverView) view).getSelected()) {
                    case MAIN_MENU:
                        StartMenuView startMenuView = new StartMenuView(view.getScreenWidth(), view.getScreenHeight(), view.getDisplay());
                        StartMenuViewController startMenuViewController = new StartMenuViewController(startMenuView, stateManager);
                        nextState = new State(startMenuViewController, startMenuView);
                        stateManager.setActiveState(nextState);
                        break;
                    case EXIT_GAME:
                        System.exit(0);
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
}

