package controllers;

import controllers.entityControllers.EntityController;
import utilities.GameState;
import utilities.State;
import utilities.StateManager;
import utilities.Task;
import views.AvatarCreationView;
import views.View;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by sergiopuleri on 2/18/16.
 */
public class GameViewController extends ViewController implements Observer{

    private ArrayList<EntityController> entityControllers;
    private AreaViewPort areaViewPort;
    private StatusViewport statusViewport;

    public GameViewController(View view, StateManager stateManager){
        super(view, stateManager);
        entityControllers = new ArrayList<>();
    }

    public void addEntityController(EntityController controller){
        controller.addObserver(this);
        entityControllers.add(controller);
    }

    @Override
    public final void handleKeyPress(KeyEvent e) {
        super.handleKeyPress(e);

        for(int i=0; i<entityControllers.size(); i++){
            entityControllers.get(i).handleKeyPress(e);
        }
    }


    @Override
    protected void initKeyPressMapping() {

    }

    @Override
    public void update(Observable o, Object arg) {
        stateManager.refreshState();
    }
}
