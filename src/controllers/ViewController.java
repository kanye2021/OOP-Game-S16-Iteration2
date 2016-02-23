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

    public ViewController(View view, StateManager stateManager){
        this.view = view;
        this.stateManager = stateManager;
        keyPressMapping = new InputMapping();

        initDefaultEscapeMapping();
        initKeyPressMapping();
    }

    public final void handleKeyPress(KeyEvent e) {
        System.out.println("Pressed: " + e.getKeyCode());
        keyPressMapping.inputKey(e.getKeyCode() + 1000*e.getModifiers());
        stateManager.refreshState();
    }

    protected abstract void initKeyPressMapping();

    private final void initDefaultEscapeMapping() {

        Task escapeTask = new Task() {
            @Override
            public void run() {
                stateManager.goToPreviousState();
            }
        };

        addKeyPressMapping(escapeTask, KeyEvent.VK_ESCAPE);

    }

    protected final void addKeyPressMapping(Task task, int... key) {
        int number = key[0];

        for (int i = 1; i < key.length; i++) {

            number += 1000*key[i];

        }

        keyPressMapping.put(number, task);

    }

    public final void onWindowResize(Component component){
        view.onWindowResize(component);
    }
}
