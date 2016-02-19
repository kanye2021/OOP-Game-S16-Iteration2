package controllers;

import utilities.State;
import utilities.StateManager;
import views.LoadGameView;
import views.SaveGameView;
import views.TestView;
import views.View;

import java.awt.event.KeyEvent;

/**
 * Created by dyeung on 2/18/16.
 */
public class SaveGameViewController extends ViewController{
    public enum SaveOptions {
        SAVE_AND_EXIT("Save and Exit"),
        SAVE("Just Save"),
        BACK("Nevermind...");

        private String s;

        private SaveOptions(String s) {
            this.s = s;
        }

        protected SaveOptions previous() {
            if (this.ordinal() == 0) {
                return SaveOptions.values()[SaveOptions.values().length - 1];
            } else {
                return SaveOptions.values()[this.ordinal() - 1];
            }
        }
        protected SaveOptions next() {
            if (this.ordinal() == SaveOptions.values().length - 1) {
                return SaveOptions.values()[0];
            } else {
                return SaveOptions.values()[this.ordinal() + 1];
            }
        }
        public String toString() {
            return s;
        }
    }

    //Controller Variables
    private SaveOptions option;

    public SaveGameViewController(View view, StateManager stateManager){
        super(view, stateManager);
        option = SaveOptions.SAVE_AND_EXIT;
        //TODO: Not a fan of having to cast the view (setSelected could be a view class function)
        ((SaveGameView) view).setSelected(option); //Lets the view know that it is currently the first option
    }
    public void handleKeyPress(KeyEvent e){
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            option = option.previous();
        } else if (key == KeyEvent.VK_DOWN) {
            option = option.next();
        } else if (key == KeyEvent.VK_ENTER) {
            // TODO: Actually go to the diff states instead of TestView.
            State nextState;
            switch (option) {
                case SAVE_AND_EXIT:
                    TestView testView = new TestView(view.getScreenWidth(), view.getScreenHeight());
                    TestViewController testController = new TestViewController(testView, stateManager);
                    nextState = new State(testController, testView);
                    stateManager.setActiveState(nextState);
                    break;
                case SAVE:
                    TestView testView2 = new TestView(view.getScreenWidth(), view.getScreenHeight());
                    TestViewController testController2 = new TestViewController(testView2, stateManager);
                    nextState = new State(testController2, testView2);
                    stateManager.setActiveState(nextState);
                    break;
                case BACK:
                    System.exit(0);
            }
        }
        ((SaveGameView) view).setSelected(option); //Newly updates the selected option
        stateManager.refreshState();
    }
    public void handleKeyRelease(KeyEvent e){

    }
}
