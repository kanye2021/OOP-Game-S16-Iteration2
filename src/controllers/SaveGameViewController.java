package controllers;

import utilities.State;
import utilities.StateManager;
import views.LoadGameView;
import views.SaveGameView;
import views.View;
import utilities.Task;
import views.*;

import java.awt.event.KeyEvent;

/**
 * Created by dyeung on 2/18/16.
 */
public class SaveGameViewController extends ViewController{

    private Task previousOption;
    private Task nextOption;
    private Task selectOption;


    public SaveGameViewController(View view, StateManager stateManager){
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

                switch (((SaveGameView)view).getSelected()) {
                    case SAVE_GAME_EXIT:
                        AvatarCreationView avatarCreationView = new AvatarCreationView(view.getScreenWidth(), view.getScreenHeight(), view.getDisplay());
                        AvatarCreationViewController avatarCreationViewController = new AvatarCreationViewController(avatarCreationView, stateManager);
                        nextState = new State(avatarCreationViewController, avatarCreationView);
                        stateManager.setActiveState(nextState);
                        break;
                    case SAVE_GAME:
                        LoadGameView loadGameView = new LoadGameView(view.getScreenWidth(), view.getScreenHeight(), view.getDisplay());
                        LoadGameViewController loadGameViewController = new LoadGameViewController(loadGameView, stateManager);
                        nextState = new State(loadGameViewController, loadGameView);
                        stateManager.setActiveState(nextState);
                        break;
                    case EXIT:
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
}
