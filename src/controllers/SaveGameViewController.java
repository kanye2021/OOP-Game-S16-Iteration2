package controllers;

import utilities.State;
import utilities.StateManager;
import utilities.TaskWrapper;
import utilities.*;
import views.SaveGameView;
import views.View;

import java.awt.event.KeyEvent;

/**
 * Created by dyeung on 2/18/16.
 */
public class SaveGameViewController extends ViewController{

    private Task previousOption;
    private Task nextOption;
    private Task selectOption;
    private GameState gameState;
    public SaveGameViewController(View view, StateManager stateManager, GameState gS){
        super(view, stateManager);
        gameState = gS;
    }
    @Override
    protected final void initKeyPressMapping() {

        previousOption = new Task() {
            @Override
            public void run() {
                ((SaveGameView)view).previousOption();
            }

            @Override
            public void stop() {}
        };

        nextOption = new Task() {
            @Override
            public void run() {
                ((SaveGameView)view).nextOption();
            }

            @Override
            public void stop() {}
        };

        selectOption = new Task() {
            @Override
            public void run() {
                State nextState;

                switch (((SaveGameView)view).getSelected()) {
                    case SAVE_GAME_EXIT:
                        //TODO: Add save game
                        saveGame();
                        System.exit(0);
                        break;
                    case SAVE_GAME:
                        saveGame();
                        goPrevious();
                        break;
                    case EXIT:
                        goPrevious();
                        break;

                }
            }

            @Override
            public void stop() {}
        };
        //Task empty = null;
        addKeyPressMapping(new TaskWrapper(previousOption, "Previous"), KeyEvent.VK_UP);
        addKeyPressMapping(new TaskWrapper(nextOption, "Next"), KeyEvent.VK_DOWN);
        addKeyPressMapping(new TaskWrapper(selectOption, "Select"), KeyEvent.VK_ENTER);


    }
    public void saveGame(){
        String saveGameName = ((SaveGameView)view).getSaveFileName();
        System.out.println("THE SAVE STATE NAME IS: " + saveGameName);
        if (saveGameName.isEmpty()) {
            System.out.println("File name is empty");
            saveGameName = "No_File_Name.xml";
        }
        gameState.saveGame(saveGameName);
    }
    public void goPrevious(){
        //TODO: Make this nicer somehow...
        view.getDisplay().remove(((SaveGameView)view).getSaveStateName());
        //stateManager.goToPreviousState();
        stateManager.goToPreviousState();
    }

}
