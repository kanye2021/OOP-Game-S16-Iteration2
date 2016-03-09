package controllers;

import utilities.State;
import utilities.StateManager;
import utilities.Task;
import views.LoadGameView;
import views.PauseView;
import views.StartMenuView;
import views.View;

import java.awt.event.KeyEvent;

/**
 * Created by david on 3/1/16.
 */
public class PauseViewController extends ViewController {


    private Task previousOption;
    private Task nextOption;
    private Task selectOption;

    private Task closePause;

    public PauseViewController(View view, StateManager stateManager){
        super(view, stateManager);
    }

    @Override
    protected final void initKeyPressMapping() {

        previousOption = new Task() {
            @Override
            public void run() {
                ((PauseView)view).previousOption();
            }

            @Override
            public void stop() {}
        };

        nextOption = new Task() {
            @Override
            public void run() {
                ((PauseView)view).nextOption();
            }

            @Override
            public void stop() {}
        };

        selectOption = new Task() {
            @Override
            public void run() {
                State nextState;

                switch (((PauseView)view).getSelected()) {
                    case CONTINUE:
                        closePause.run();
                        break;
                    case SAVE_GAME:
                        break;
                    case OPTIONS:
                        break;
                    case LOAD_GAME:
                        LoadGameView loadGameView = new LoadGameView(view.getScreenWidth()/ 2, view.getScreenHeight()/ 2, view.getDisplay());
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

        addKeyPressMapping(previousOption, KeyEvent.VK_UP);
        addKeyPressMapping(nextOption, KeyEvent.VK_DOWN);
        addKeyPressMapping(selectOption, KeyEvent.VK_ENTER);

    }

    public void setClosePause(Task task) {
        closePause = task;
        addKeyPressMapping(closePause, KeyEvent.VK_ESCAPE);
        addKeyPressMapping(closePause, KeyEvent.VK_M);
        addKeyPressMapping(closePause, KeyEvent.VK_P);
    }
}

