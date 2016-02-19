package controllers;

import utilities.StateManager;
import utilities.Task;
import views.View;

import java.awt.event.KeyEvent;

/**
 * Created by Bradley on 2/17/16.
 */
public class TestViewController extends ViewController {

    private Task goToPreviousState = new Task() {
        @Override
        public void run() {
            stateManager.goToPreviousState();
        }
    };

    public TestViewController(View view, StateManager stateManager){
        super(view, stateManager);
    }

    @Override
    protected final void initKeyPressMapping() {

        addKeyPressMapping(KeyEvent.VK_ESCAPE, goToPreviousState);

    }

    @Override
    protected void initKeyReleaseMapping() {}

}
