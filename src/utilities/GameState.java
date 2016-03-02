package utilities;

import controllers.GameViewController;
import controllers.entityControllers.AvatarController;
import controllers.entityControllers.MountController;
import controllers.entityControllers.NPCController;
import controllers.entityControllers.PetController;
import models.entities.Avatar;
import models.entities.Pet;

import models.entities.npc.Horse;
import models.entities.npc.NPC;

import models.map.Map;
import views.GameView;
import views.View;

import java.util.ArrayList;

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

        for (NPC npc : npcList){
            //n.addObserver((GameView)view);
            viewController.setNpcControllers(npc);
        }
        // Int the viewports
        viewController.initViewports(map, avatar, npcList);
    }

    public void setMap(Map map){
        this.map = map;
    }

    public void setAvatar(Avatar avatar){

        this.avatar = avatar;
    }

    // TODO: Method may not belong here, just for developing/testing.
    public void setAvatarsPet(Pet pet, PetController petController) {
        this.avatar.setPet(pet);
        this.avatar.addObserver(petController);
    }

    //TODO: I don't think it should be here
    public void setMount(Horse horse, MountController mountController){
        this.avatar.setMount(horse);
        this.avatar.addObserver(mountController);
    }

    public void setNpcList(ArrayList<NPC> npcList){
        this.npcList = npcList;
    }

}

