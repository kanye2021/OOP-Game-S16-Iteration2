package controllers;

import utilities.StateManager;
import utilities.Task;
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
    protected final void initKeyPressMapping() {}

}
