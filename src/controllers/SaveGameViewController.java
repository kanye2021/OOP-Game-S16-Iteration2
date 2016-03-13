package controllers;

import utilities.State;
import utilities.StateManager;
import utilities.TaskWrapper;
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
                ((SaveGameView)view).previousOption();
            }

            @Override
            public void stop() {}
        };

        nextOption = new Task() {
            @Override
            public void run() {
                ((SaveGameView)view).nextOption();
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
                        //TODO: Add save game
                        System.exit(0);
                        break;
                    case SAVE_GAME:
                        //TODO: Save Game state
//                        LoadGameView loadGameView = new LoadGameView(view.getScreenWidth(), view.getScreenHeight(), view.getDisplay());
//                        LoadGameViewController loadGameViewController = new LoadGameViewController(loadGameView, stateManager);
//                        nextState = new State(loadGameViewController, loadGameView);
//                        stateManager.setActiveState(nextState);
                        break;
                    case EXIT:
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
