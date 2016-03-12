package controllers;

import models.entities.Avatar;
import utilities.InputMapping;
import utilities.StateManager;
import utilities.Task;
import views.View;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Bradley on 2/17/16.
 */
public abstract class ViewController {

    protected View view;
    protected StateManager stateManager;
    private InputMapping keyPressMapping;
    protected boolean inRemappingKeysState;

    public ViewController(View view, StateManager stateManager){
        this.view = view;
        this.stateManager = stateManager;
        keyPressMapping = new InputMapping();
        this.inRemappingKeysState = false;

        initDefaultEscapeMapping();
        initKeyPressMapping();
    }

    public View getView() {
        return view;
    }

    public StateManager getStateManager() {
        return stateManager;
    }

    public void handleKeyPress(KeyEvent e) {
        // If we hit enter, or we are currently NOT remapping, handle via the VC's mappings
        // Because if we are remapping, ENTER saves the new mapping
        if (e.getKeyCode() == KeyEvent.VK_ENTER || !inRemappingKeysState ) {
            keyPressMapping.inputKey(getKeyIntMapping(e));
        }
        // Otherwise let the VC handle the new mapping
        else {
            handleNewKeyPressForRemap(e);
        }
    }

    // Only called when a VC is in a remapping state.
    // AKA when changing keybindings.
    // They will override this method, and do what they want with the key press --> (map it somewhere)
    protected void handleNewKeyPressForRemap(KeyEvent e) {
        // Method stub to be overriden by a subclass who wants to
    }

    public void handleKeyRelease(KeyEvent e){
        keyPressMapping.keyReleased(getKeyIntMapping(e));
    }


    // The default behavior of a mouse move is to do nothing. If a view controller wishes to do something on a mouse
    // move it must overrride this function.
    public void handleMouseDragged(java.awt.event.MouseEvent e){

    }

    public void handleMouseReleased(java.awt.event.MouseEvent e){

    }
    // The default behavior of a mouse move is to do nothing. If a view controller wishes to do something on a mouse
    // click it must overrride this function.
    public void handleMouseClicked(MouseEvent e) {

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

        Task altF4Task = new Task() {
            @Override
            public void run() {
                System.exit(0);
            }

            @Override
            public void stop(){}
        };

        addKeyPressMapping(escapeTask, KeyEvent.VK_BACK_SPACE);
        addKeyPressMapping(altF4Task, KeyEvent.VK_F4, KeyEvent.ALT_MASK);

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
