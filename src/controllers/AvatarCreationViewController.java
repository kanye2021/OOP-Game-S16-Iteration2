package controllers;

import utilities.State;
import utilities.StateManager;
import views.*;

import java.awt.event.KeyEvent;

/**
 * Created by sergiopuleri on 2/18/16.
 */
public class AvatarCreationViewController {


    /*public AvatarCreationViewController(View view, StateManager stateManager){
        super(view, stateManager);
    }




    @Override
    public void handleKeyPress(KeyEvent e){
        // TODO: Implement key handling
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            ((AvatarCreationView)view).previousOption();
        } else if (key == KeyEvent.VK_DOWN) {
            ((AvatarCreationView)view).nextOption();
        } else if (key == KeyEvent.VK_ENTER) {
            // Can't use the enum to do this like we previously did, because
            // The enum is static and doesn't have access to this instance's
            // StateManager in order to set the state.
            // TODO: Actually go to the diff states instead of TestView.
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

        System.out.println(e.getKeyChar());
        // Tell View to re-render
        stateManager.refreshState();
    }

    @Override
    public void handleKeyRelease(KeyEvent e) {
        // TODO: Implement this.
        System.out.println(e.getKeyChar());
    }*/
}
