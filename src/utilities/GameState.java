package utilities;

import controllers.GameViewController;
import controllers.ViewController;
import controllers.entityControllers.AvatarController;
import models.map.Map;
import models.entities.Avatar;
import models.entities.NPC;
import views.View;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Bradley on 2/18/16.
 */
public class GameState extends State {

    private Avatar avatar;
    private Map map;
    private ArrayList<NPC> npcList;

    private GameLoader gameLoader;
//    private GameSaver gameSaver;

    // Create a new game.
    public GameState(GameViewController viewController, View view, String occupation){

        super(viewController, view);

        gameLoader = new GameLoader();
//        gameSaver = new GameSaver();

        // When not given a file, ladGame will create the game state from defaults.
        // If this is succesfull, the game loader will init the map, avatar, and npc list.
        gameLoader.createNewGame(this, occupation);

        // Init the entity controllers
        initControllers(viewController);
    }

    private void initControllers(GameViewController viewController){
        AvatarController avatarController = new AvatarController(this.avatar);
        viewController.addEntityController(avatarController);

        // TODO: Init other entity controllers.
    }

    public void setMap(Map map){
        this.map = map;
    }

    public void setAvatar(Avatar avatar){

        this.avatar = avatar;
    }

    public void setNpcList(ArrayList<NPC> npcList){
        this.npcList = npcList;
    }

//    // Load a existing game.
//    public GameState(ViewController viewController, View view, File gameFile){
//
//        super(viewController, view);
//
//        gameLoader = new GameLoader();
//        gameSaver = new GameSaver();
//
//        // When given a file, loadGame will create the game state from the given file.
//        gameLoader.loadGame(gameFile, this);
//    }
//
//
//    // Getters and Setters
//    public SmasherAvatar getAvatar(){
//        return avatar;
//    }
//    public void setAvatar(SmasherAvatar avatar){
//        this.avatar = avatar;
//    }
//    public Map getMap() {
//        return map;
//    }
//    public void setMap(Map map) {
//        this.map = map;
//    }
//    public ArrayList<NPC> getNpcList() {
//        return npcList;
//    }
//
//    public void setNpcList(ArrayList<NPC> npcList) {
//        this.npcList = npcList;
//    }
//
//    // Save the current game state
//    public void saveState(){
//        gameSaver.saveGame(this);
//    }
}
