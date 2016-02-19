package controllers;

import utilities.State;
import utilities.StateManager;
import views.*;

import java.awt.event.KeyEvent;

/**
 * Created by Bradley on 2/17/16.
 */
public class StartMenuViewController extends ViewController {



    public StartMenuViewController(View view, StateManager stateManager){
        super(view, stateManager);
    }

    @Override
    public void handleKeyPress(KeyEvent e){
        // TODO: Implement key handling
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            ((StartMenuView)view).previousOption();
        } else if (key == KeyEvent.VK_DOWN) {
            ((StartMenuView)view).nextOption();
        } else if (key == KeyEvent.VK_ENTER) {
            // Can't use the enum to do this like we previously did, because
            // The enum is static and doesn't have access to this instance's
            // StateManager in order to set the state.
            // TODO: Actually go to the diff states instead of TestView.
            State nextState;

            switch (((StartMenuView)view).getSelected()) {
                case CREATE_GAME:
                    AvatarCreationView avatarCreationView = new AvatarCreationView(view.getScreenWidth(), view.getScreenHeight());
                    AvatarCreationViewController avatarCreationViewController = new AvatarCreationViewController(avatarCreationView, stateManager);
                    nextState = new State(avatarCreationViewController, avatarCreationView);
                    stateManager.setActiveState(nextState);
                    break;
                case LOAD_GAME:
                    LoadGameView loadGameView = new LoadGameView(view.getScreenWidth(), view.getScreenHeight());
                    LoadGameViewController loadGameViewController = new LoadGameViewController(loadGameView, stateManager);
                    nextState = new State(loadGameViewController, loadGameView);
                    stateManager.setActiveState(nextState);
                    break;
                case EXIT_GAME:
                    System.exit(0);

            }
        }

        System.out.println(e.getKeyChar());
        // Tell View to re-render
        stateManager.refreshState();
    }

    @Override
    public void handleKeyRelease(KeyEvent e) {
        // TODO: Implement this.
        System.out.println(e.getKeyChar());
    }
}
