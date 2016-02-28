package controllers;

import utilities.State;
import utilities.StateManager;
import utilities.Task;
import views.*;

import java.awt.event.KeyEvent;

/**
 * Created by dyeung on 2/18/16.
 */
public class SaveGameViewController extends ViewController{
    private Task previousOption;
    private Task nextOption;
    private Task selectOption;



    public SaveGameViewController(View view, StateManager stateManager){
        super(view, stateManager);
    }

    @Override
    protected final void initKeyPressMapping() {

        previousOption = new Task() {
            @Override
            public void run() {
                ((StartMenuView) view).previousOption();
            }
            @Override
            public void stop() {}
        };

        nextOption = new Task() {
            @Override
            public void run() {
                ((StartMenuView) view).nextOption();
            }
            @Override
            public void stop() {}
        };

        selectOption = new Task() {
            @Override
            public void run() {
                State nextState;

                switch (((StartMenuView) view).getSelected()) {
                    case CREATE_GAME:
                        AvatarCreationView avatarCreationView = new AvatarCreationView(view.getScreenWidth(), view.getScreenHeight());
                        AvatarCreationViewController avatarCreationViewController = new AvatarCreationViewController(avatarCreationView, stateManager);
                        nextState = new State(avatarCreationViewController, avatarCreationView);
                        stateManager.setActiveState(nextState);
                        break;
                    case LOAD_GAME:
                        LoadGameView loadGameView = new LoadGameView(view.getScreenWidth(), view.getScreenHeight());
                        LoadGameViewController loadGameViewController = new LoadGameViewController(loadGameView, stateManager);
                        nextState = new State(loadGameViewController, loadGameView);
                        stateManager.setActiveState(nextState);
                        break;
                    case EXIT_GAME:
                        System.exit(0);

                }
            }
            @Override
            public void stop() {}
        };
    }
//    public void handleKeyPress(KeyEvent e){
////        int key = e.getKeyCode();
////
////        if (key == KeyEvent.VK_UP) {
////            option = option.previous();
////        } else if (key == KeyEvent.VK_DOWN) {
////            option = option.next();
////        } else if (key == KeyEvent.VK_ENTER) {
////            // TODO: Actually go to the diff states instead of TestView.
////            State nextState;
////            switch (option) {
////                case SAVE_AND_EXIT:
////                    TestView testView = new TestView(view.getScreenWidth(), view.getScreenHeight());
////                    TestViewController testController = new TestViewController(testView, stateManager);
////                    nextState = new State(testController, testView);
////                    stateManager.setActiveState(nextState);
////                    break;
////                case SAVE:
////                    TestView testView2 = new TestView(view.getScreenWidth(), view.getScreenHeight());
////                    TestViewController testController2 = new TestViewController(testView2, stateManager);
////                    nextState = new State(testController2, testView2);
////                    stateManager.setActiveState(nextState);
////                    break;
////                case BACK:
////                    System.exit(0);
////            }
////        }
////        ((SaveGameView) view).setSelected(option); //Newly updates the selected option
////        stateManager.refreshState();
////    }

}
