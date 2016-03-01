package controllers;

import controllers.ViewController;
import utilities.StateManager;
import views.View;

/**
 * Created by dyeung on 2/29/16.
 */
public class NPCInteractionController extends ViewController {
    public NPCInteractionController(View view, StateManager stateManager) {
        super(view, stateManager);
    }

    @Override
    protected void initKeyPressMapping() {

    }
}
