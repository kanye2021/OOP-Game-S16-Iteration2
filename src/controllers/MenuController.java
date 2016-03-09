package controllers;

import utilities.StateManager;
import utilities.SubState;
import utilities.Task;
import views.PauseView;
import views.View;

/**
 * Created by david on 3/8/16.
 */
public class MenuController extends ViewController {

    private GameViewController gameViewController;

    public MenuController(View view, StateManager stateManager, GameViewController gameViewController){
        super(view, stateManager);
        this.gameViewController = gameViewController;
    }

    //Creates PauseSubstate and sets a close substate keybind withing PauseViewController
    public void openPauseMenu(){
        System.out.println("1:Am I in");
        PauseView pauseView = new PauseView(gameViewController.getScreenWidth(), gameViewController.getScreenHeight(), gameViewController.getDisplay());
        PauseViewController pauseViewController = new PauseViewController(pauseView, getStateManager());
        SubState pauseSubstate = new SubState(pauseViewController, pauseView);
        // Add closing task.
        pauseViewController.setClosePause(new Task() {
            @Override
            public void run() {
                System.out.println("2:Did I close ");
                pauseSubstate.dismiss();
            }

            @Override
            public void stop() {}
        });
        // Add the substate
        gameViewController.addSubState(pauseSubstate);
    }

    public void initKeyPressMapping(){}
}
