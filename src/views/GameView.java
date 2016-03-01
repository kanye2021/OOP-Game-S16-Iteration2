package views;

import models.entities.Avatar;
import models.map.Map;
import models.stats.Stats;
import utilities.SubState;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by Bradley on 2/26/2016.
 */
public class GameView extends View {

    private AreaViewport areaViewport;
    private StatusViewport statusViewport;
    private ArrayList<SubState> substates;

    public GameView(int width, int height, Display display){
        super(width, height, display);
        this.substates = new ArrayList<SubState>();

    }

    public void initAreaViewport(Map map, Avatar avatar){
        this.areaViewport = new AreaViewport(getScreenWidth(), getScreenHeight(), getDisplay(), map, avatar);
    }

    public void initStatusViewport(Stats stats){
        this.statusViewport = new StatusViewport(getScreenWidth(), getScreenHeight(), getDisplay(), stats);
    }

    @Override
    public void render(Graphics g) {
        if(areaViewport!=null && statusViewport!= null){
            areaViewport.render(g);
            statusViewport.render(g);
        }
        // Render all subviews on top of the AreaViewPort.
        for (SubState subview : this.substates) {
            subview.render(g);
        }
    }

    @Override
    public void onWindowResize(Component component) {
        super.onWindowResize(component);
        areaViewport.onWindowResize(component);
        statusViewport.onWindowResize(component);
    }

    @Override
    public void scaleView() {

    }

    public void addSubState(SubState s) {
        s.setParent(this);
        this.substates.add(s);
    }
    public void insertSubState(SubState s, int index) {
        s.setParent(this);
        this.substates.add(index, s);
    }
    public ArrayList<SubState> getSubStates() {
        return this.substates;
    }
    public boolean passInputToSubstate(KeyEvent e) {
        // If the game view has substates, pass the input to the top-most substate.
        if (!substates.isEmpty()) {
            SubState top = substates.get(substates.size() - 1);
            if (top.getViewController() != null) {
                top.handleInput(e);
                return true;
            }
            else return false;
        }
        else return false;
    }
    public void removeSubState(SubState substate) {
        substates.remove(substate);
    }
    public void clearSubStates() {
        substates.clear();
    }

    public void toggleDetailedStats(){
        statusViewport.toggleDetails();
    }
}
