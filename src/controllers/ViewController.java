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

    public void handleKeyPress(KeyEvent e) {
        System.out.println("Pressed: " + getKeyIntMapping(e));

        keyPressMapping.inputKey(getKeyIntMapping(e));
        stateManager.refreshState();
    }

    public void handleKeyRelease(KeyEvent e){
        keyPressMapping.keyReleased(getKeyIntMapping(e));
        stateManager.refreshState();
    }

    protected abstract void initKeyPressMapping();

    private final void initDefaultEscapeMapping() {

        Task escapeTask = new Task() {
            @Override
            public void run() {
                stateManager.goToPreviousState();
            }

            @Override
            public void stop() {}
        };

        addKeyPressMapping(escapeTask, KeyEvent.VK_ESCAPE);

    }

    protected final void addKeyPressMapping(Task task, int... key) {

        keyPressMapping.put(getKeyIntMapping(key), task);

    }

    public final void onWindowResize(Component component){
        view.onWindowResize(component);
    }

    private final int getKeyIntMapping(KeyEvent e) {

        /*
         * All of the modifiers are dumped into the e.getModifiers() int. This number is then multiplied by 1000
         * in order to ensure that there is no crossing paths with standard keys.
         */

        return e.getKeyCode() + 1000*e.getModifiers();

    }

    private final int getKeyIntMapping(int... key) {

        /*
         * The first key (key[0]) is the key we desire to be pressed.
         * All subsequent keys are considered masks and multiplied by 1000 as stated above.
         */

        int number = key[0];

        for (int i = 1; i < key.length; i++) {

            number += 1000*key[i];

        }

        return number;

    }

}
