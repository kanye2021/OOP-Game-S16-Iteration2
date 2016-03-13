package controllers;

import utilities.*;
import views.GameView;
import views.LoadGameView;
import views.SaveGameView;
import views.View;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by dyeung on 2/18/16.
 */
public class LoadGameViewController extends ViewController {
    //Constants
    private final String SAVE_FILE_LOCATION = IOUtilities.getFileSystemDependentPath("./src/res/save_files/");
    //Variables for game view
    private int myOption = 0;
    private ArrayList<File> listOfSaveFiles = new ArrayList<>(); //This is only being retrieved from the LoadGameView
    private Task previousOption;
    private Task nextOption;
    private Task selectOption;

    public LoadGameViewController(View view, StateManager stateManager){
        super(view, stateManager);
        updateSaveFiles(); // Initial update for save files
    }

    @Override
    protected void initKeyPressMapping() {

        previousOption = new Task() {
            @Override
            public void run() {
                if (myOption > 0) {
                    myOption--;
                    ((LoadGameView) view).updateOption(myOption);
                }
            }

            @Override
            public void stop() {}
        };

        nextOption = new Task() {
            @Override
            public void run() {
                if (myOption < listOfSaveFiles.size() - 1){
                    myOption++;
                    ((LoadGameView) view).updateOption(myOption);
                }
            }
            @Override
            public void stop() {}
        };

        selectOption = new Task() {
            @Override
            public void run() {
                System.out.println("Ran");
                loadGame();
            }

            @Override
            public void stop() {}
        };

        Task escapeTask = new Task() {
            @Override
            public void run() {
                stateManager.goToPreviousState();
            }

            @Override
            public void stop() {}
        };

        addKeyPressMapping(escapeTask, KeyEvent.VK_ESCAPE);
        addKeyPressMapping(previousOption, KeyEvent.VK_UP);
        addKeyPressMapping(nextOption, KeyEvent.VK_DOWN);
        addKeyPressMapping(selectOption, KeyEvent.VK_ENTER);

    }

/*-------------------Main functions --------------*/
    public void updateSaveFiles(){
        File folder = new File(SAVE_FILE_LOCATION);
        if (!folder.exists()) {
            folder.mkdir();
        }
        File[] f = folder.listFiles();
        //TODO: good idea? limited the amount of save files in case there are too many to show on screen
        int limit = 5;
        listOfSaveFiles.clear(); // Needs to reset the array whenever it retrieves the new set of files
        for (int i = 0; i < f.length && i < limit; i++) {
            File current = f[i];
            // Checks if its a valid name (IE not .DS_Store) and adds it to the array list
            if (checkValidFileName(current.getName()))  {
                listOfSaveFiles.add(current);
            }
        }
        ((LoadGameView) view).updateSaveFiles(listOfSaveFiles); //Sends the list of save files to the view
    }

    //Added this because the check valid part might be longer in the future
    public boolean checkValidFileName(String name) {
        //TODO: Add more checks (IE what about .bd.sf.xml or a.xml.df.sf)
        if (name.contains(".xml") && !name.equals(".xml")){
            return true;
        }
        return false;
    }
    public void sendViewUpdatedOption(){
        ((LoadGameView) view).updateOption(myOption);
    }

    public void loadGame(){ // Loads the game based on the current option
        String selectedFile = listOfSaveFiles.get(myOption).getName();

        GameView gameView = new GameView(view.getScreenWidth(), view.getScreenHeight(), view.getDisplay());
        GameViewController gameViewController = new GameViewController(gameView, stateManager);
        Toast.initWithGameViewController(gameViewController); //inits when you're loading the game
        GameState nextState = new GameState(gameViewController, gameView, null, selectedFile);
        //nextState.loadGame(selectedFile);
        stateManager.setActiveState(nextState);
        //Load game = new Load("test.xml");
        //game.loadGame();

    }


}
