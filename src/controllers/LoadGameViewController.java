package controllers;

import utilities.State;
import utilities.StateManager;
import views.LoadGameView;
import views.TestView;
import views.View;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by dyeung on 2/18/16.
 */
public class LoadGameViewController extends ViewController {
    //Variables
    private int myOption;

    private ArrayList<File> listOfSaveFiles; //This is only being retrieved from the LoadGameView

    public LoadGameViewController(View view, StateManager stateManager){
        super(view, stateManager);
        myOption = 0;
    }
    public void handleKeyPress(KeyEvent e){

        updateSaveFiles(); //TODO: Try to make it so you don't have to call it every key press

        int key = e.getKeyCode();
        switch (key){
            case  KeyEvent.VK_UP:
                if (myOption > 0) {
                    myOption--;
                }
                break;
            case  KeyEvent.VK_DOWN:
                if (myOption < listOfSaveFiles.size() - 1){
                    myOption++;
                }
                break;
            case  KeyEvent.VK_ENTER:
                if (listOfSaveFiles.size() != 0) {
                    loadGame();
                }
                break;
            case  KeyEvent.VK_ESCAPE:
                break;
        }
        sendViewUpdatedOption();
        stateManager.refreshState();
    }

    //TODO: Insert good name for these helper functions
    public void updateSaveFiles(){
        listOfSaveFiles = ((LoadGameView) view).getListOfSaveFiles();
    }
    public void sendViewUpdatedOption(){
        ((LoadGameView) view).updateOption(myOption);
    }
    public void loadGame(){ // Loads the game based on the current option
        System.out.println("Load game!");
        //TODO: Add game view
        TestView testView = new TestView(view.getScreenWidth(), view.getScreenHeight());
        TestViewController testController = new TestViewController(testView, stateManager);
        State nextState = new State(testController, testView);
        stateManager.setActiveState(nextState);

    }

    public void handleKeyRelease(KeyEvent e){

    }

}
