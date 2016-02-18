package utilities;

import controllers.ViewController;
import views.Display;
import views.View;

/**
 * Created by Bradley on 2/17/16.
 */
public class State {
    private ViewController viewController;
    private View view;

    public State(ViewController viewController, View view){
        this.viewController = viewController;
        this.view = view;
    }

    public void activate(InputManager inputManager, Display display){
        inputManager.setActiveController(viewController);
        display.setActiveView(view);

        // Update the display.
        display.repaint();
    }
}
