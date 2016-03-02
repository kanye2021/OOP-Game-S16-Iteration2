package views;

import models.entities.Avatar;
import models.entities.Entity;
import models.entities.npc.NPC;
import models.map.Map;
import models.stats.Stats;
import utilities.SubState;

import java.awt.*;
<<<<<<< HEAD
import java.util.Observable;
import java.util.Observer;
=======
import java.awt.event.KeyEvent;
import java.util.ArrayList;
>>>>>>> 701dfeb4ee5743fd379ae06c2f3658da7aeef295

/**
 * Created by Bradley on 2/26/2016.
 */
public class GameView extends View implements Observer{

    private AreaViewport areaViewport;
    private StatusViewport statusViewport;
<<<<<<< HEAD
    //Container of views that will turn on or off
    private NPCActionView npcActionView;

    private boolean hasNPCAction;

    public GameView(int width, int height, Display display){
        super(width, height, display);
        hasNPCAction = false;
=======
    private ArrayList<SubState> substates;

    public GameView(int width, int height, Display display){
        super(width, height, display);
        this.substates = new ArrayList<SubState>();

>>>>>>> 701dfeb4ee5743fd379ae06c2f3658da7aeef295
    }

    public void initAreaViewport(Map map, Avatar avatar){
        this.areaViewport = new AreaViewport(getScreenWidth(), getScreenHeight(), getDisplay(), map, avatar);
    }

    public void initStatusViewport(Stats stats){
        this.statusViewport = new StatusViewport(getScreenWidth(), getScreenHeight(), getDisplay(), stats);
    }
    public void initNPCActionView(NPC npc){
        this.npcActionView = new NPCActionView(getScreenWidth(), getScreenHeight(), getDisplay(), npc);
        //showEntityInteraction = false;
    }

    @Override
    public void render(Graphics g) {
        if(areaViewport!=null && statusViewport!= null){
            areaViewport.render(g);
            statusViewport.render(g);
        }
<<<<<<< HEAD

        //TEST TO CHECK FOR VIEWS
        if (hasNPCAction){
            npcActionView.render(g);
=======
        // Render all subviews on top of the AreaViewPort.
        for (SubState subview : this.substates) {
            subview.render(g);
>>>>>>> 701dfeb4ee5743fd379ae06c2f3658da7aeef295
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

<<<<<<< HEAD
    @Override
    public void update(Observable o, Object arg) {
    }
    public void renderNPCAction(boolean shouldRender){
        if (shouldRender) {
            hasNPCAction = true;
        }else {
            hasNPCAction = false;
        }
=======
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
>>>>>>> 701dfeb4ee5743fd379ae06c2f3658da7aeef295
    }
}
