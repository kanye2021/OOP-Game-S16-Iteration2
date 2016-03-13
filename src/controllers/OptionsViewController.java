package controllers;

import models.entities.Avatar;
import models.skills.ActiveSkill;
import utilities.*;
import views.LoadGameView;
import views.OptionsView;
import views.PauseView;
import views.View;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

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
                    int skillCount = optionsView.getSkillCount();
                    int selectedIndex = optionsView.getSelected();
                    optionsView.setIndexForNewKeyBind(selectedIndex);

                    // Set the remapping state of super class.
                    inRemappingKeysState = true;

                    // Get the skill or regular task that we are re-mapping
                    if (selectedIndex < skillCount - 1) {
                        ActiveSkill skillWeAreReMapping = optionsView.getCurrentSelectedSkill();
                        // Get and remember its old keybind
                        oldKeyBind = skillWeAreReMapping.getKeyBind();
                    } else if (selectedIndex > skillCount) {
                        OptionsView.KeyCodeTaskObject taskWeAreReMapping = optionsView.getCurrentSelectedTask();
                        // Get and remember its old keybind
                        oldKeyBind = taskWeAreReMapping.getKeyCode();
                    }
                    // Re route all key presses for the new keybind
                    clearDefaultMappings();
                }
                // If hit enter, save new keybind and
                // Set to not listening to key bind
                else {
                    optionsView.setListeningForNewKeyBind(false);
                    int selectedIndex = optionsView.getSelected();
                    int skillCount = optionsView.getSkillCount();


                    // Save it
                    if (selectedIndex < skillCount - 1) {
                        saveNewSkillKeyBind();
                    } else if (selectedIndex > skillCount) {
                        saveNewGenericKeyBind();
                    }

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

    private void saveNewSkillKeyBind() {
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
        gameViewController.addKeyPressMapping(new TaskWrapper(emptyTask, "Empty Task"), oldKeyBind);

        // Set the keybind for realz with the game vc
        gameViewController.addKeyPressMapping(new TaskWrapper(activateSkill, "skill"), lastHeardKeyPress.getKeyCode());
    }
    private void saveNewGenericKeyBind() {
        OptionsView optionsView = ((OptionsView)view);

        // Get the skill we are re-mapping
        OptionsView.KeyCodeTaskObject genericTaskWeAreRemapping = optionsView.getCurrentSelectedTask();

        TaskWrapper taskWrapper = genericTaskWeAreRemapping.getTaskWrapper();
        String descriptor = taskWrapper.getDescriptor();
        Task taskToDo = taskWrapper.getTask();


        // Get current avatar lol
        Avatar avatar = gameViewController.getAvatar();


        Task emptyTask = new Task() {
            @Override
            public void run() {}

            @Override
            public void stop() {}
        };

        // Set the keybind on the skill
        genericTaskWeAreRemapping.setKeyCode( lastHeardKeyPress.getKeyCode() );

        // Remove old keybind
        gameViewController.addKeyPressMapping(new TaskWrapper(emptyTask, "Empty Task"), oldKeyBind);

        // Set the keybind for realz with the game vc
        gameViewController.addKeyPressMapping(new TaskWrapper(taskToDo, descriptor), lastHeardKeyPress.getKeyCode());
    }


    @Override
    protected void handleNewKeyPressForRemap(KeyEvent e) {
        OptionsView optionsView = ((OptionsView)view);
        int selectedIndex = optionsView.getSelected();
        int skillCount = optionsView.getSkillCount();

        // Get the skill or regular task that we are re-mapping
        if (selectedIndex < skillCount - 1) {
            // Get the skill we are re-mapping
            ActiveSkill skillWeAreReMapping = optionsView.getCurrentSelectedSkill();

            lastHeardKeyPress = e;

            skillWeAreReMapping.setKeyBind(e.getKeyCode());

        } else if (selectedIndex > skillCount) {
            OptionsView.KeyCodeTaskObject taskWeAreReMapping = optionsView.getCurrentSelectedTask();

            lastHeardKeyPress = e;

            taskWeAreReMapping.setKeyCode(e.getKeyCode());



        }



    }

    private void addDefaultMappings() {
        addKeyPressMapping(new TaskWrapper(previousOption, "Previous"), KeyEvent.VK_UP);
        addKeyPressMapping(new TaskWrapper(nextOption, "Next"), KeyEvent.VK_DOWN);
        addKeyPressMapping(new TaskWrapper(selectOption, "Select"), KeyEvent.VK_ENTER);
    }
    private void clearDefaultMappings() {
        Task empty = new Task() {
            @Override
            public void run() { }
            @Override
            public void stop() {}
        };
        addKeyPressMapping(new TaskWrapper(empty, "Empty Task"), KeyEvent.VK_UP);
        addKeyPressMapping(new TaskWrapper(empty, "Empty Task"), KeyEvent.VK_DOWN);
    }

    public void setClose(Task task) {
        closeOptions = task;
        addKeyPressMapping(new TaskWrapper(closeOptions, "Close"), KeyEvent.VK_ESCAPE);
        addKeyPressMapping(new TaskWrapper(closeOptions, "Close"), KeyEvent.VK_M);
        addKeyPressMapping(new TaskWrapper(closeOptions, "Close"), KeyEvent.VK_P);
    }
}
