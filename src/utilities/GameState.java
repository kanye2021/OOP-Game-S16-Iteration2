package utilities;

import controllers.AvatarCreationViewController;
import controllers.GameViewController;
import controllers.entityControllers.AvatarController;
import models.entities.Avatar;

import models.entities.npc.NPC;

import models.map.Map;
import views.AvatarCreationView;
import views.AreaViewport;
import views.GameView;
import views.View;

import java.util.ArrayList;
import java.util.Iterator;

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
        if (occupation == null){ //only run when the game is being loaded, after the areaviewport is initalized
            gameLoader.loadSeenTiles(this,fileName);
        }
        // Init the default skill key bindings
        viewController.initSkillKeyBindMappings();
        gameSaver = new GameSaver(map, avatar, npcList, getAreaViewport() );

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

    public void removeNpc(NPC npc){
        if(npcList.contains(npc)){
            npcList.remove(npc);
        }
    }

    @Override
    public void update() {

        avatar.animator.update(System.currentTimeMillis());

        if (((GameViewController) viewController).avatarDied()) {
            ((GameViewController) viewController).gameOver();
        } else {
            ((GameViewController) viewController).update();
        }

        // Loop through the npcList
        for (Iterator<NPC> iterator = npcList.iterator(); iterator.hasNext(); ) {

            NPC npc = iterator.next();

            // Check to see if the npc died. If it did, remove it from the list.
            if (npc.getLives() < 1) {
                iterator.remove();
            } else {
                npc.update();
            }
        }
    }

    public AreaViewport getAreaViewport(){
       return ((GameView)view).getAreaViewport();
    }
}

