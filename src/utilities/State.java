package utilities;

import controllers.ViewController;
import views.Display;
import views.View;

import java.util.ArrayList;

/**
 * Created by Bradley on 2/17/16.
 */
public class State {
    protected ViewController viewController;
    protected View view;
    protected ArrayList<SubState> substates;

    public State(ViewController viewController, View view) {
        this.viewController = viewController;
        this.view = view;
    }

    public void activate(InputManager inputManager, Display display) {
        inputManager.setActiveController(viewController);
        display.setActiveView(view);

        // update the display.
        display.repaint();
    }


    public View getView() {
        return view;
    }

    public void update() {
    }


}
