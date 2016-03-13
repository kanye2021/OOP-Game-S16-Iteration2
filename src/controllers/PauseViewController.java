package controllers;

import utilities.*;
import views.*;

import java.awt.event.KeyEvent;

/**
 * Created by david on 3/1/16.
 */
public class PauseViewController extends ViewController {


    private Task previousOption;
    private Task nextOption;
    private Task selectOption;

    private Task closePause;
    private GameViewController gVC;
    public PauseViewController(View view, StateManager stateManager, GameViewController gs){
        super(view, stateManager);
        gVC = gs;
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
                        SaveGameView saveGameView = new SaveGameView(view.getScreenWidth(), view.getScreenHeight(), view.getDisplay());
                        GameState gS = (GameState)stateManager.getTop();
                        SaveGameViewController sGv = new SaveGameViewController(saveGameView, stateManager, gS);
                        nextState = new State(sGv, saveGameView);
                        stateManager.setActiveState(nextState);
                        break;
                    case OPTIONS:
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

