package controllers;

import utilities.InputMapping;
import utilities.StateManager;
import utilities.Task;
import views.View;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Bradley on 2/17/16.
 */
public abstract class ViewController {

    protected View view;
    protected StateManager stateManager;
    private InputMapping keyPressMapping;
    private InputMapping keyReleaseMapping;

    public ViewController(View view, StateManager stateManager){
        this.view = view;
        this.stateManager = stateManager;
        keyPressMapping = new InputMapping();
        keyReleaseMapping = new InputMapping();

        initKeyPressMapping();
        initKeyReleaseMapping();
    }

    public final void handleKeyPress(KeyEvent e) {
        System.out.println("Pressed: " + e.getKeyCode());
        keyPressMapping.inputKey(e.getKeyCode());
        stateManager.refreshState();
    }

    public final void handleKeyRelease(KeyEvent e) {
        System.out.println("Released: " + e.getKeyCode());
        keyReleaseMapping.inputKey(e.getKeyCode());
        stateManager.refreshState();
    }

    protected abstract void initKeyPressMapping();

    protected final void addKeyPressMapping(int key, Task task) {
        keyPressMapping.put(key, task);

    }

    protected final void addKeyReleaseMapping(int key, Task task) {

        keyReleaseMapping.put(key, task);

    }

    protected abstract void initKeyReleaseMapping();

    public final void onWindowResize(Component component){
        view.onWindowResize(component);
    }
}
