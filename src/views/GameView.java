package views;

import models.entities.Avatar;
import models.map.Map;
import models.skills.SkillList;
import models.stats.Stats;
import utilities.SubState;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Bradley on 2/26/2016.
 */
public class GameView extends View{

    private AreaViewport areaViewport;
    private StatusViewport statusViewport;
    private SkillViewport skillViewport;
    private ArrayList<SubState> substates;
    //Container of views that will turn on or off
    private NPCMenuView npcActionView;

    private boolean hasNPCAction;
    public GameView(int width, int height, Display display){
        super(width, height, display);
        this.substates = new ArrayList<SubState>();
        hasNPCAction = false;
    }

    public void initAreaViewport(Map map, Avatar avatar){
        this.areaViewport = new AreaViewport(getScreenWidth(), getScreenHeight(), getDisplay(), map, avatar);
    }

    public void initStatusViewport(Stats stats){
        this.statusViewport = new StatusViewport(getScreenWidth(), getScreenHeight(), getDisplay(), stats);
    }

    public void initSkillViewport(Avatar avatar){
        SkillList skills = avatar.getSkills();
        Stats stats = avatar.getStats();
        this.skillViewport = new SkillViewport(getScreenWidth(), getScreenHeight(), getDisplay(), skills, stats);

    }

    @Override
    public void render(Graphics g) {
        if(areaViewport!=null ){
            areaViewport.render(g);
        }
        if (skillViewport != null) {
            skillViewport.render(g);
        }
        if (statusViewport!= null) {
            statusViewport.render(g);
        }


        // Render all subviews on top of the AreaViewPort.
        for (SubState subview : this.substates) {
            subview.render(g);
        }

//        //TEST TO CHECK FOR VIEWS
//        if (hasNPCAction){
//            npcActionView.render(g);
//        }
    }

    @Override
    public void onWindowResize(Component component) {
        super.onWindowResize(component);
        areaViewport.onWindowResize(component);
        statusViewport.onWindowResize(component);
        skillViewport.onWindowResize(component);
//        if (hasNPCAction) {
//            npcActionView.onWindowResize(component);
//        }
        // Render all subviews on top of the AreaViewPort.
        for (SubState subview : this.substates) {
            View v = subview.getView();
            v.onWindowResize(component);
        }
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
        // hacky..... necessary to remove toast from view.
        // Otherwise will only remove when refresh (key press)
        getDisplay().repaint();
    }
    public void popTopSubState() {
        substates.remove(substates.size() - 1);
        // hacky..... necessary to remove toast from view.
        // Otherwise will only remove when refresh (key press)
        getDisplay().repaint();
    }
    public void clearSubStates() {
        substates.clear();
        // hacky..... necessary to remove toast from view.
        // Otherwise will only remove when refresh (key press)
        getDisplay().repaint();
    }
    public void toggleDetailedStats() {
        statusViewport.toggleDetails();
    }

    public void toggleDebugInformation() {
        areaViewport.toggleDebugInformation();
    }

    public boolean hasSubState(){
        if(substates.size() > 0)
            return true;
        else
            return false;

    }

    public void renderNPCAction(boolean shouldRender){
        if (shouldRender) {
            hasNPCAction = true;
        }else {
            hasNPCAction = false;
        }
    }

    public void setAreaViewportOffset(Point offset){
        this.areaViewport.setViewportOffset(offset);
    }

    public void handleMouseClick(MouseEvent e) {
        // Pass mouseclick to view ports
        // Currently only skill viewport cares about mouse clicks
        this.skillViewport.handleMouseClick(e);
    }
}
