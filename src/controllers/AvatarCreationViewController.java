package controllers;

import utilities.*;
import views.AvatarCreationView;
import views.GameView;
import views.View;

import java.awt.event.KeyEvent;

/**
 * Created by sergiopuleri on 2/18/16.
 */
public class AvatarCreationViewController extends ViewController {

    private Task previousOption;
    private Task nextOption;
    private Task selectOption;

    public AvatarCreationViewController(View view, StateManager stateManager) {
        super(view, stateManager);
    }

    @Override
    protected void initKeyPressMapping() {

        previousOption = new Task() {
            @Override
            public void run() {
                ((AvatarCreationView) view).previousOption();
            }

            @Override
            public void stop() {
            }
        };

        nextOption = new Task() {
            @Override
            public void run() {
                ((AvatarCreationView) view).nextOption();
            }

            @Override
            public void stop() {
            }
        };

        selectOption = new Task() {
            @Override
            public void run() {
                State nextState;
                GameView gameView;
                GameViewController gameViewController;
                switch (((AvatarCreationView) view).getSelected()) {
                    case SMASHER:
                        gameView = new GameView(view.getScreenWidth(), view.getScreenHeight(), view.getDisplay());
                        gameViewController = new GameViewController(gameView, stateManager);
                        nextState = new GameState(gameViewController, gameView, "smasher", null);
                        stateManager.setActiveState(nextState);
                        // Set the gameViewController, initially here.
                        Toast.initWithGameViewController(gameViewController);
                        break;
                    case SUMMONER:
                        gameView = new GameView(view.getScreenWidth(), view.getScreenHeight(), view.getDisplay());
                        gameViewController = new GameViewController(gameView, stateManager);
                        nextState = new GameState(gameViewController, gameView, "summoner", null);
                        stateManager.setActiveState(nextState);
                        // Set the gameViewController, initially here.
                        Toast.initWithGameViewController(gameViewController);
                        break;
                    case SNEAK:
                        gameView = new GameView(view.getScreenWidth(), view.getScreenHeight(), view.getDisplay());
                        gameViewController = new GameViewController(gameView, stateManager);
                        nextState = new GameState(gameViewController, gameView, "sneak", null);
                        stateManager.setActiveState(nextState);
                        // Set the gameViewController, initially here.
                        Toast.initWithGameViewController(gameViewController);
                        break;
                }

            }

            @Override
            public void stop() {
            }
        };


        Task escapeTask = new Task() {
            @Override
            public void run() {
                stateManager.goToPreviousState();
            }

            @Override
            public void stop() {
            }
        };

        addKeyPressMapping(new TaskWrapper(previousOption, "Previous"), KeyEvent.VK_UP);
        addKeyPressMapping(new TaskWrapper(nextOption, "Next"), KeyEvent.VK_DOWN);
        addKeyPressMapping(new TaskWrapper(selectOption, "Select"), KeyEvent.VK_ENTER);
        addKeyPressMapping(new TaskWrapper(escapeTask, "Back"), KeyEvent.VK_ESCAPE);


    }

}
