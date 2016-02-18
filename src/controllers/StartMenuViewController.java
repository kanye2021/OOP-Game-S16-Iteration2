package controllers;

import utilities.State;
import utilities.StateManager;
import views.TestView;
import views.View;

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
        switch(key){
            case KeyEvent.VK_Q:
                TestView testView = new TestView(view.getScreenWidth(), view.getScreenHeight());
                TestViewController testController = new TestViewController(testView, stateManager);
                State nextState = new State(testController, testView);
                stateManager.setActiveState(nextState);
                break;
            case KeyEvent.VK_K:
                stateManager.goToPreviousState();
        }
        System.out.println(e.getKeyChar());
    }

    @Override
    public void handleKeyRelease(KeyEvent e) {
        // TODO: Implement this.
        System.out.println(e.getKeyChar());
    }
}
