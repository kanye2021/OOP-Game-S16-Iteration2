package utilities;

import controllers.ViewController;
import views.Display;
import views.GameView;
import views.View;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by sergiopuleri on 2/29/16.
 */

// Basically just to encapsulate a view and viewcontrolelr together..
public class SubState {
    private ViewController viewController;
    private View view;
    private GameView parent;

    public SubState(ViewController viewController, View view){
        this.viewController = viewController;
        this.view = view;
    }

    public void render(Graphics g) {
        this.view.render(g);
    }
    public void handleInput(KeyEvent e) {
        this.viewController.handleKeyPress(e);
    }

    public ViewController getViewController() {
        return viewController;
    }

    public View getView() {
        return view;
    }

    public void setParent(GameView parent) {
        this.parent = parent;
    }

    public GameView getParent() {
        return parent;
    }
    public void dismiss() {
        this.parent.removeSubState(this);
    }

    public void setView(View view){
        this.view = view;
    }
}
