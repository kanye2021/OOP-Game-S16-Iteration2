package controllers.entityControllers;

import models.entities.npc.Action;
import controllers.entityControllers.AI.Brain;
import models.entities.npc.Action;
import models.entities.npc.actions.Action;
import models.entities.npc.NPC;
import views.GameView;
import views.NPCActionView;
import views.View;
import views.GameView;
import views.NPCMenuView;

import java.util.ArrayList;

/**
 * Created by Bradley on 2/27/16.
 */
//Movement on its NPC (handling movement by itself)
public class NPCController {
    private NPC npc;
    private NPCActionView npcView;
    private NPCMenuView npcView;
    private ArrayList<Action> actionList;
    private Brain brain;

    public NPCController(NPC npc){
        this.npc = npc;
    } //Default constructor


    //When you start the interaction, it'll tell views to render a view
    public void start(){
        //TODO: Implement actual actions for NPC
        System.out.println("Start NPC stuff");
       // gameView.renderNPCAction(true);
    }

}
