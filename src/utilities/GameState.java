package utilities;

import controllers.GameViewController;
import controllers.entityControllers.AvatarController;
import models.entities.Avatar;

import models.entities.npc.NPC;

import models.map.Map;
import views.AreaViewport;
import views.GameView;
import views.View;

import java.util.ArrayList;

/**
 * Created by Bradley on 2/18/16.
 */
public class GameState extends State {

    private Map map;
    private Avatar avatar;
    private ArrayList<NPC> npcList;

    private GameLoader gameLoader;
    private GameSaver gameSaver;

    // Create a new game.
    public GameState(GameViewController viewController, View view, String occupation, String fileName){

        super(viewController, view);
        //I don't like how we have to create the gameView and gameViewController outside of gameState
        gameLoader = new GameLoader();

        // When not given a file, ladGame will create the game state from defaults.
        // If this is successful, the game loader will init the map, avatar, and npc list.
        npcList = new ArrayList<>();

        //TODO fix this later, gamestate has to intialize a default version of the game before it updates to the new
        //loaded game
        if (occupation != null) {
            gameLoader.createNewGame(this, occupation);
        }else{
            gameLoader.loadGame(this,fileName);
        }

        // Init the entity controllers
        viewController.setAvatarController(new AvatarController(avatar, viewController));

        // Int the viewports
        viewController.initViewports(map, avatar, npcList);
        gameSaver = new GameSaver(map, avatar, npcList, getAreaViewport() );

    }

    @Override
    public void update(){
        for(NPC npc: npcList){
            npc.update();
        }
        ((GameViewController) viewController).update();
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
    public void loadGame(String filePath){
        gameLoader.loadGame(this,filePath);
    }
    public void saveGame(String filePath){
        //This is awkward since gameLoader might create new map avatar and npclist thus
        //previous gameSaver would not have references to the new ones
        gameSaver = new GameSaver(map, avatar, npcList, getAreaViewport());
        gameSaver.saveGame(filePath);
    }

    public AreaViewport getAreaViewport(){
       return ((GameView)view).getAreaViewport();
    }
}

