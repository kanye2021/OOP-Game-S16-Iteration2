package controllers;

import utilities.StateManager;
import views.View;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Bradley on 2/17/16.
 */
public abstract class ViewController {

    protected View view;
    protected StateManager stateManager;

    public ViewController(View view, StateManager stateManager){
        this.view = view;
        this.stateManager = stateManager;
    }

    public abstract void handleKeyPress(KeyEvent e);

    public abstract void handleKeyRelease(KeyEvent e);


    public final void onWindowResize(Component component){
        view.onWindowResize(component);
    }
}
