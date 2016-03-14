package utilities;

import controllers.ViewController;

import java.awt.*;
import java.awt.event.*;

/**
 * Created by Bradley on 2/17/16.
 */
public class InputManager extends ComponentAdapter implements KeyEventDispatcher, MouseListener, MouseMotionListener {
    private ViewController activeController;

    public void setActiveController(ViewController activeController) {
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

    // MouseMotion methods
    @Override
    public void mouseDragged(MouseEvent e) {
        activeController.handleMouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    // Mouse Listner Methods
    @Override
    public void mouseClicked(MouseEvent e) {
        activeController.handleMouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        activeController.handleMouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}