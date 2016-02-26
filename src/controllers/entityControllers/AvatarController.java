package controllers.entityControllers;

import controllers.entityControllers.EntityController;
import models.entities.Avatar;
import models.map.Map;
import utilities.InputMapping;
import utilities.StateManager;
import utilities.Task;
import views.View;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Observable;

/**
 * Created by Bradley on 2/26/2016.
 */
public class AvatarController extends EntityController {
    private InputMapping keyPressMapping;
    private Avatar avatar;

    public AvatarController(Avatar avatar){
        this.avatar = avatar;
        keyPressMapping = new InputMapping();

        initKeyPressMapping();
    }

    public void handleKeyPress(KeyEvent e) {

        keyPressMapping.inputKey(getKeyIntMapping(e));
        notifyObservers();
    }

    protected void initKeyPressMapping(){
        Task moveNorth = new Task() {
            @Override
            public void run() { avatar.move(Map.Direction.NORTH);}
        };
        Task moveNorthWest = new Task() {
            @Override
            public void run() { avatar.move(Map.Direction.NORTH_WEST);}
        };
        Task moveSouthWest = new Task() {
            @Override
            public void run() { avatar.move(Map.Direction.SOUTH_WEST);}
        };
        Task moveSouth = new Task() {
            @Override
            public void run() { avatar.move(Map.Direction.SOUTH);}
        };
        Task moveSouthEast = new Task() {
            @Override
            public void run() { avatar.move(Map.Direction.SOUTH_EAST);}
        };
        Task moveNorthEast = new Task() {
            @Override
            public void run() { avatar.move(Map.Direction.NORTH_EAST);}
        };

        addKeyPressMapping(moveNorth, KeyEvent.VK_NUMPAD8);
        addKeyPressMapping(moveNorthWest, KeyEvent.VK_NUMPAD7);
        addKeyPressMapping(moveSouthWest, KeyEvent.VK_NUMPAD1);
        addKeyPressMapping(moveSouth, KeyEvent.VK_NUMPAD2);
        addKeyPressMapping(moveSouthEast, KeyEvent.VK_NUMPAD3);
        addKeyPressMapping(moveNorthEast, KeyEvent.VK_NUMPAD9);
    }


    protected final void addKeyPressMapping(Task task, int... key) {

        keyPressMapping.put(getKeyIntMapping(key), task);
    }

    private final int getKeyIntMapping(KeyEvent e) {

        /*
         * All of the modifiers are dumped into the e.getModifiers() int. This number is then multiplied by 1000
         * in order to ensure that there is no crossing paths with standard keys.
         */

        return e.getKeyCode() + 1000*e.getModifiers();

    }

    private final int getKeyIntMapping(int... key) {

        /*
         * The first key (key[0]) is the key we desire to be pressed.
         * All subsequent keys are considered masks and multiplied by 1000 as stated above.
         */

        int number = key[0];

        for (int i = 1; i < key.length; i++) {

            number += 1000*key[i];

        }

        return number;

    }
}
