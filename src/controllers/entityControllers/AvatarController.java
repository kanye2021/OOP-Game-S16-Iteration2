package controllers.entityControllers;

import models.entities.Avatar;
import models.map.Map;
import utilities.InputMapping;
import utilities.Task;

import java.awt.event.KeyEvent;

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
    }

    public void handleKeyRelease(KeyEvent e){

        keyPressMapping.keyReleased(getKeyIntMapping(e));
    }

    protected void initKeyPressMapping(){
        Task moveNorth = new Task() {
            @Override
            public void run() { avatar.move(Map.Direction.NORTH);}

            @Override
            public void stop() { avatar.stopMoving(); }
        };
        Task moveNorthWest = new Task() {
            @Override
            public void run() { avatar.move(Map.Direction.NORTH_WEST);}

            @Override
            public void stop() { avatar.stopMoving(); }
        };
        Task moveSouthWest = new Task() {
            @Override
            public void run() { avatar.move(Map.Direction.SOUTH_WEST);}

            @Override
            public void stop() { avatar.stopMoving(); }
        };
        Task moveSouth = new Task() {
            @Override
            public void run() { avatar.move(Map.Direction.SOUTH);}

            @Override
            public void stop() { avatar.stopMoving(); }
        };
        Task moveSouthEast = new Task() {
            @Override
            public void run() { avatar.move(Map.Direction.SOUTH_EAST);}

            @Override
            public void stop() { avatar.stopMoving(); }
        };
        Task moveNorthEast = new Task() {
            @Override
            public void run() { avatar.move(Map.Direction.NORTH_EAST);}

            @Override
            public void stop() { avatar.stopMoving(); }
        };

//        addKeyPressMapping(moveNorth, KeyEvent.VK_NUMPAD8);
//        addKeyPressMapping(moveNorthWest, KeyEvent.VK_NUMPAD7);
//        addKeyPressMapping(moveSouthWest, KeyEvent.VK_NUMPAD1);
//        addKeyPressMapping(moveSouth, KeyEvent.VK_NUMPAD2);
//        addKeyPressMapping(moveSouthEast, KeyEvent.VK_NUMPAD3);
//        addKeyPressMapping(moveNorthEast, KeyEvent.VK_NUMPAD9);

        addKeyPressMapping(moveNorth, KeyEvent.VK_W);
        addKeyPressMapping(moveNorthWest, KeyEvent.VK_Q);
        addKeyPressMapping(moveSouthWest, KeyEvent.VK_Z);
        addKeyPressMapping(moveSouth, KeyEvent.VK_S);
        addKeyPressMapping(moveSouthEast, KeyEvent.VK_C);
        addKeyPressMapping(moveNorthEast, KeyEvent.VK_E);
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
