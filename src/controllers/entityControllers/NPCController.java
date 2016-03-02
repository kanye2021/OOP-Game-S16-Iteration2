package controllers.entityControllers;

import models.entities.npc.Action;
import models.entities.npc.NPC;
import utilities.InputMapping;
import views.GameView;
import views.NPCActionView;
import views.View;

import java.util.ArrayList;

/**
 * Created by Bradley on 2/27/16.
 */
//Movement on its NPC (handling movement by itself)
public class NPCController extends EntityController {
    private NPC npc;
    private NPCActionView npcView;
    private ArrayList<Action> actionList;

    public NPCController(){} //Default constructor
    public NPCController(NPC npc, GameView view){
       // this.gameView = view;
        this.npc = npc;
    }
    //When you start the interaction, it'll tell views to render a view
    public void start(){
        //TODO: Implement actual actions for NPC
        System.out.println("Start NPC stuff");
       // gameView.renderNPCAction(true);
    }

}
