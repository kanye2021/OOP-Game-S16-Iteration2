package controllers.entityControllers;

import java.awt.event.KeyEvent;
import java.util.Observable;

/**
 * Created by aseber on 2/22/16.
 */
public abstract class EntityController extends Observable {

    public abstract void handleKeyPress(KeyEvent e);
}
