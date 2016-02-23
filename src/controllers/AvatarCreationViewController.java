package controllers;

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
        };

        nextOption = new Task() {
            @Override
            public void run() {
                ((AvatarCreationView) view).nextOption();
            }
        };

        selectOption = new Task() {
            @Override
            public void run() {
                State nextState;
                switch (((AvatarCreationView)view).getSelected()) {
                    case SMASHER:
                        TestView testView = new TestView(view.getScreenWidth(), view.getScreenHeight());
                        TestViewController testController = new TestViewController(testView, stateManager);
                        nextState = new State(testController, testView);
                        stateManager.setActiveState(nextState);
                        break;
                    case SUMMONER:
                        LoadGameView loadGameView = new LoadGameView(view.getScreenWidth(), view.getScreenHeight());
                        LoadGameViewController loadGameViewController = new LoadGameViewController(loadGameView, stateManager);
                        nextState = new State(loadGameViewController, loadGameView);
                        stateManager.setActiveState(nextState);
                        break;
                    case SNEAK:
                        System.exit(0);
                }
            }
        };

        addKeyPressMapping(previousOption, KeyEvent.VK_UP);
        addKeyPressMapping(nextOption, KeyEvent.VK_DOWN);
        addKeyPressMapping(selectOption, KeyEvent.VK_ENTER);

    }

}
