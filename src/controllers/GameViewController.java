package controllers;

import controllers.entityControllers.AvatarController;
import controllers.entityControllers.NPCController;
import models.entities.Avatar;
import models.map.Map;
import utilities.StateManager;
import views.GameView;
import views.View;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by sergiopuleri on 2/18/16.
 */
public class GameViewController extends ViewController implements Observer{

    private ArrayList<NPCController> npcControllers;
    private AvatarController avatarController;

    public GameViewController(View view, StateManager stateManager){
        super(view, stateManager);
        npcControllers = new ArrayList<>();
    }

    public void addAvatarController(AvatarController controller){
        avatarController = controller;
    }

    public void initViewports(Map map, Avatar avatar){
        ((GameView)view).initAreaViewport(map, avatar);
        ((GameView)view).initStatusViewport(avatar.getStats());
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

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Update called in Gameview Controller");
        stateManager.refreshState();
    }
}
