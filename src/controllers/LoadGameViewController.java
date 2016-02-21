package controllers;

import utilities.IOUtilities;
import utilities.State;
import utilities.StateManager;
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
    private int myOption;
    private ArrayList<File> listOfSaveFiles; //This is only being retrieved from the LoadGameView

    public LoadGameViewController(View view, StateManager stateManager){
        super(view, stateManager);
        myOption = 0;
        listOfSaveFiles = new ArrayList<>();
        updateSaveFiles(); // Initial update for save files
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
    public void handleKeyRelease(KeyEvent e){

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
        System.out.println("Load game!");
        //TODO: Remove this stuff and add game view
//        TestView testView = new TestView(view.getScreenWidth(), view.getScreenHeight());
//        TestViewController testController = new TestViewController(testView, stateManager);
//        State nextState = new State(testController, testView);
        SaveGameView saveGameView = new SaveGameView(view.getScreenWidth(), view.getScreenHeight());
        SaveGameViewController sGv = new SaveGameViewController(saveGameView, stateManager);
        State nextState = new State(sGv, saveGameView);
        stateManager.setActiveState(nextState);
    }


}
