package controllers;

import controllers.entityControllers.AvatarController;
import controllers.entityControllers.NPCController;
import models.entities.Avatar;
import models.entities.npc.NPC;
import models.map.Map;
import utilities.StateManager;
import views.GameView;
import views.View;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by sergiopuleri on 2/18/16.
 */
public class GameViewController extends ViewController{

    private ArrayList<NPC> npcList;
    private AvatarController avatarController;

    public GameViewController(View view, StateManager stateManager){
        super(view, stateManager);
        npcList = new ArrayList<>();
    }

    public void setAvatarController(AvatarController controller){
        avatarController = controller;
    }
    public void setNpcControllers(NPC npc){
        npcList.add(npc);
    }
    public void initViewports(Map map, Avatar avatar, ArrayList<NPC> npcList){
        ((GameView)view).initAreaViewport(map, avatar);
        ((GameView)view).initStatusViewport(avatar.getStats());

        //Temporarily get the first NPC
        ((GameView)view).initNPCActionView(npcList.get(0));
    }

    @Override
    public final void handleKeyPress(KeyEvent e) {
        super.handleKeyPress(e);

        // Give the keypress to the avatar controller as well
        if(avatarController!=null){
            avatarController.handleKeyPress(e);
        }
    }

    @Override
    public final void handleKeyRelease(KeyEvent e){
        super.handleKeyRelease(e);

        if(avatarController!=null){
            avatarController.handleKeyRelease(e);
        }
    }

    @Override
    protected void initKeyPressMapping() {

    }

}
