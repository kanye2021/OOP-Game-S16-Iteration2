package utilities;

import controllers.AvatarCreationViewController;
import controllers.GameViewController;
import controllers.entityControllers.AvatarController;
import models.entities.Avatar;

import models.entities.npc.NPC;

import models.map.Map;
import views.AvatarCreationView;
import views.GameView;
import views.View;

import java.util.ArrayList;
import java.util.Iterator;

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
        // If this is successful, the game loader will init the map, avatar, and npc list.
        gameLoader.createNewGame(this, occupation);

        // Init the entity controllers
        viewController.setAvatarController(new AvatarController(avatar, viewController));

        //viewController.setAvatarController(new AvatarController(avatar, (GameView)view));

        // Int the viewports
        viewController.initViewports(map, avatar, npcList);

        // Init the default skill key bindings
        viewController.initSkillKeyBindMappings();
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

    public void removeNpc(NPC npc){
        if(npcList.contains(npc)){
            npcList.remove(npc);
        }
    }

    @Override
    public void update(){
        if(((GameViewController) viewController).avatarDied()){
            ((GameViewController) viewController).gameOver();
        }else{
            ((GameViewController) viewController).update();
        }

        // Loop through the npcList
        for(Iterator<NPC> iterator = npcList.iterator(); iterator.hasNext();) {

            NPC npc = iterator.next();

            // Check to see if the npc died. If it did, remove it from the list.
            if (npc.getLives() < 1) {
                iterator.remove();
            } else {
                npc.update();
            }
        }
    }
}

