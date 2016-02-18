package controllers;

import utilities.StateManager;
import views.View;

import java.awt.event.KeyEvent;

/**
 * Created by Bradley on 2/17/16.
 */
public class TestViewController extends ViewController {

    public TestViewController(View view, StateManager stateManager){
        super(view, stateManager);
    }

    @Override
    public void handleKeyPress(KeyEvent e){
        // TODO: Implement key handling
        int key = e.getKeyCode();
        switch(key){
            case KeyEvent.VK_K:
                stateManager.goToPreviousState();
                break;
        }
        System.out.println(e.getKeyChar());
    }

    @Override
    public void handleKeyRelease(KeyEvent e) {
        // TODO: Implement this.
        System.out.println(e.getKeyChar());
    }
}
