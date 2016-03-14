package controllers;

import models.entities.Avatar;
import utilities.State;
import utilities.StateManager;
import utilities.SubState;
import utilities.Task;
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

    // Needs handle to current avatar due to getting to other views from the Pause Menu.
    // e.g. Save Game and Options.
    private Avatar avatar;

    // Also necessary to add more substates n shit
    private GameViewController gameViewController;

    public PauseViewController(View view, StateManager stateManager, Avatar avatar, GameViewController gameVC){
        super(view, stateManager);
        this.avatar = avatar;
        this.gameViewController = gameVC;
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
                        OptionsView optionsView = new OptionsView(view.getScreenWidth(), view.getScreenHeight(), view.getDisplay(), avatar.getSkills(), gameViewController.getKeyPressMappings());
                        OptionsViewController optionsViewController = new OptionsViewController(optionsView, stateManager, gameViewController);
                        SubState sub = new SubState(optionsViewController, optionsView);
                        // Add closing task.
                        optionsViewController.setClose(new Task() {
                            @Override
                            public void run() {
                                sub.dismiss();
                            }

                            @Override
                            public void stop() {}
                        });
                        // Add the substate
                        gameViewController.addSubState(sub);
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

    public void setClosePause(Task task) {
        closePause = task;
        addKeyPressMapping(new TaskWrapper(closePause, "Close"), KeyEvent.VK_ESCAPE);
        addKeyPressMapping(new TaskWrapper(closePause, "Close"), KeyEvent.VK_M);
        addKeyPressMapping(new TaskWrapper(closePause, "Close"), KeyEvent.VK_P);
    }

}

