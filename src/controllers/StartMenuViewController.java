package controllers;

import utilities.State;
import utilities.StateManager;
import views.StartMenuView;
import views.TestView;
import views.View;

import java.awt.event.KeyEvent;

/**
 * Created by Bradley on 2/17/16.
 */
public class StartMenuViewController extends ViewController {

    // This enum represents the menu options available on this screen. The setView() function maps to an individual view
    // such as utilities.IOMediator.Views.CREATE_GAME;
    public enum MenuOptions {
        CREATE_GAME("Create Game"),
        LOAD_GAME("Load Game"),
        EXIT_GAME("Exit Game");

        private String s;

        private MenuOptions(String s) {
            this.s = s;
        }

        protected MenuOptions previous() {
            if (this.ordinal() == 0) {
                return MenuOptions.values()[MenuOptions.values().length - 1];
            } else {
                return MenuOptions.values()[this.ordinal() - 1];
            }
        }
        protected MenuOptions next() {
            if (this.ordinal() == MenuOptions.values().length - 1) {
                return MenuOptions.values()[0];
            } else {
                return MenuOptions.values()[this.ordinal() + 1];
            }
        }
        public String toString() {
            return s;
        }
    }

    private MenuOptions option;

    public StartMenuViewController(View view, StateManager stateManager){
        super(view, stateManager);
        option = MenuOptions.CREATE_GAME;
    }

    @Override
    public void handleKeyPress(KeyEvent e){
        // TODO: Implement key handling
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            option = option.previous();
        } else if (key == KeyEvent.VK_DOWN) {
            option = option.next();
        } else if (key == KeyEvent.VK_ENTER) {
            // Can't use the enum to do this like we previously did, because
            // The enum is static and doesn't have access to this instance's
            // StateManager in order to set the state.
            // TODO: Actually go to the diff states instead of TestView.
            State nextState;
            switch (option) {
                case CREATE_GAME:
                    TestView testView = new TestView(view.getScreenWidth(), view.getScreenHeight());
                    TestViewController testController = new TestViewController(testView, stateManager);
                    nextState = new State(testController, testView);
                    stateManager.setActiveState(nextState);
                    break;
                case LOAD_GAME:
                    TestView testView2 = new TestView(view.getScreenWidth(), view.getScreenHeight());
                    TestViewController testController2 = new TestViewController(testView2, stateManager);
                    nextState = new State(testController2, testView2);
                    stateManager.setActiveState(nextState);
                    break;
                case EXIT_GAME:
                    System.exit(0);

            }
        }

        System.out.println(e.getKeyChar());
        // Set data in view
        ((StartMenuView) view).setSelected(option);
        // Tell View to re-render
        stateManager.refreshState();
    }

    @Override
    public void handleKeyRelease(KeyEvent e) {
        // TODO: Implement this.
        System.out.println(e.getKeyChar());
    }
}
