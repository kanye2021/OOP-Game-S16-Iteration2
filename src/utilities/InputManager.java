package utilities;

import controllers.ViewController;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;

/**
 * Created by Bradley on 2/17/16.
 */
public class InputManager extends ComponentAdapter implements KeyEventDispatcher {
    private ViewController activeController;

    public void setActiveController(ViewController activeController){
        this.activeController = activeController;
    }

    // Overrides function of KeyEventDispatcher.
    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            activeController.handleKeyPress(e);
            return true;
        } else if (e.getID() == KeyEvent.KEY_RELEASED) {
            activeController.handleKeyRelease(e);
            return true;
        }
        return false;
    }

    // Overrides function of componentResized.
    @Override
    public void componentResized(ComponentEvent e) {
        super.componentResized(e);
        activeController.onWindowResize(e.getComponent());
    }
}