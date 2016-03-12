package controllers;

import models.entities.Avatar;
import models.skills.ActiveSkill;
import utilities.State;
import utilities.StateManager;
import utilities.Task;
import views.LoadGameView;
import views.OptionsView;
import views.PauseView;
import views.View;

import java.awt.event.KeyEvent;

/**
 * Created by sergiopuleri on 3/11/16.
 */
public class OptionsViewController extends ViewController {

    private Task previousOption;
    private Task nextOption;
    private Task selectOption;

    private Task closeOptions;
    private KeyEvent lastHeardKeyPress;
    private int oldKeyBind;

    // Necessary to re-map skill keybindings. cuz thats where they live.
    private GameViewController gameViewController;

    public OptionsViewController(View view, StateManager stateManager, GameViewController gameVC){
        super(view, stateManager);
        this.gameViewController = gameVC;
    }

    @Override
    protected final void initKeyPressMapping() {

        OptionsView optionsView = ((OptionsView)view);
        previousOption = new Task() {
            @Override
            public void run() {
                optionsView.previousOption();
            }

            @Override
            public void stop() {}
        };

        nextOption = new Task() {
            @Override
            public void run() {
                optionsView.nextOption();
            }

            @Override
            public void stop() {}
        };

        selectOption = new Task() {
            @Override
            public void run() {

                // Set to listening for keybind
                if (!optionsView.isListeningForNewKeyBind()) {
                    optionsView.setListeningForNewKeyBind(true);
                    optionsView.setIndexForNewKeyBind(optionsView.getSelected());

                    // Set the remapping state of super class.
                    inRemappingKeysState = true;

                    // Get the skill we are re-mapping
                    ActiveSkill skillWeAreReMapping = optionsView.getCurrentSelectedSkill();

                    // Get and remember its old keybind
                    oldKeyBind = skillWeAreReMapping.getKeyBind();

                    // Re route all key presses for the new keybind
                    clearDefaultMappings();
                }
                // If hit enter, save new keybind and
                // Set to not listening to key bind
                else {
                    optionsView.setListeningForNewKeyBind(false);

                    // Save it
                    saveNewKeyBind();

                    // Set the remapping state of super class.
                    inRemappingKeysState = false;

                    // Allow to regularly navigate the menu
                    addDefaultMappings();
                }

            }

            @Override
            public void stop() {}

        };

        addDefaultMappings();

    }

    private void saveNewKeyBind() {
        OptionsView optionsView = ((OptionsView)view);

        // Get the skill we are re-mapping
        ActiveSkill skillWeAreReMapping = optionsView.getCurrentSelectedSkill();

        // Get current avatar lol
        Avatar avatar = gameViewController.getAvatar();

        Task activateSkill = new Task() {
            @Override
            public void run() { skillWeAreReMapping.onActivate(avatar); }

            @Override
            public void stop() {}
        };

        Task emptyTask = new Task() {
            @Override
            public void run() {}

            @Override
            public void stop() {}
        };

        // Set the keybind on the skill
        skillWeAreReMapping.setKeyBind( lastHeardKeyPress.getKeyCode() );

        // Remove old keybind
        gameViewController.addKeyPressMapping(emptyTask, oldKeyBind);

        // Set the keybind for realz with the game vc
        gameViewController.addKeyPressMapping(activateSkill, lastHeardKeyPress.getKeyCode());

    }

    @Override
    protected void handleNewKeyPressForRemap(KeyEvent e) {
        OptionsView optionsView = ((OptionsView)view);

        // Get the skill we are re-mapping
        ActiveSkill skillWeAreReMapping = optionsView.getCurrentSelectedSkill();

        lastHeardKeyPress = e;

        skillWeAreReMapping.setKeyBind(e.getKeyCode());
    }

    private void addDefaultMappings() {
        addKeyPressMapping(previousOption, KeyEvent.VK_UP);
        addKeyPressMapping(nextOption, KeyEvent.VK_DOWN);
        addKeyPressMapping(selectOption, KeyEvent.VK_ENTER);
    }
    private void clearDefaultMappings() {
        Task empty = new Task() {
            @Override
            public void run() { }
            @Override
            public void stop() {}
        };
        addKeyPressMapping(empty, KeyEvent.VK_UP);
        addKeyPressMapping(empty, KeyEvent.VK_DOWN);
    }

    public void setClose(Task task) {
        closeOptions = task;
        addKeyPressMapping(closeOptions, KeyEvent.VK_ESCAPE);
        addKeyPressMapping(closeOptions, KeyEvent.VK_M);
        addKeyPressMapping(closeOptions, KeyEvent.VK_P);
    }
}
