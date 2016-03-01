package controllers.entityControllers;

import models.entities.npc.Action;
import models.entities.npc.NPC;
import utilities.InputMapping;
import views.NPCActionView;

import java.util.ArrayList;

/**
 * Created by Bradley on 2/27/16.
 */
//Movement on its NPC (handling movement by itself)
public class NPCController extends EntityController {
    private NPC npc;
    private ArrayList<Action> actionList;

    public NPCController(){} //Default constructor
    public NPCController(NPC npc){
        this.npc = npc;
    }

    public void start(){
        //TODO: Implement actual actions for NPC
        System.out.println("Start NPC stuff");

    }
}
