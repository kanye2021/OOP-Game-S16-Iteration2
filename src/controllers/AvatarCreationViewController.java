package controllers;

import utilities.GameState;
import utilities.State;
import utilities.StateManager;
import utilities.Task;
import views.*;

import java.awt.event.KeyEvent;

/**
 * Created by sergiopuleri on 2/18/16.
 */
public class AvatarCreationViewController extends ViewController {

    private Task previousOption;
    private Task nextOption;
    private Task selectOption;

    public AvatarCreationViewController(View view, StateManager stateManager){
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
            public void stop() {}
        };

        nextOption = new Task() {
            @Override
            public void run() {
                ((AvatarCreationView) view).nextOption();
            }

            @Override
            public void stop() {}
        };

        selectOption = new Task() {
            @Override
            public void run() {
                State nextState;
                GameView gameView;
                GameViewController gameViewController;
                switch (((AvatarCreationView)view).getSelected()) {
                    case SMASHER:
                        gameView = new GameView(view.getScreenWidth(), view.getScreenHeight(), view.getDisplay());
                        gameViewController = new GameViewController(gameView, stateManager);
                        nextState = new GameState(gameViewController, gameView, "smasher");
                        stateManager.setActiveState(nextState);
                        break;
                    case SUMMONER:
                        gameView = new GameView(view.getScreenWidth(), view.getScreenHeight(), view.getDisplay());
                        gameViewController = new GameViewController(gameView, stateManager);
                        nextState = new GameState(gameViewController, gameView, "summoner");
                        stateManager.setActiveState(nextState);
                        break;
                    case SNEAK:
                        gameView = new GameView(view.getScreenWidth(), view.getScreenHeight(),view.getDisplay());
                        gameViewController = new GameViewController(gameView, stateManager);
                        nextState = new GameState(gameViewController, gameView, "sneak");
                        stateManager.setActiveState(nextState);
                        break;
                }
            }
            @Override
            public void stop() {}
        };

        addKeyPressMapping(previousOption, KeyEvent.VK_UP);
        addKeyPressMapping(nextOption, KeyEvent.VK_DOWN);
        addKeyPressMapping(selectOption, KeyEvent.VK_ENTER);

    }

}
