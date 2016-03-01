package models.entities.npc;

import models.entities.Avatar;
import sun.security.x509.AVA;

/**
 * Created by dyeung on 2/28/16.
 */
public abstract class Action {
//    public enum ActionList{
//        TALK("Talk"),
//        ATTACK("Attack"),
//        USE_ITEM("Use Item"),
//        USE_SKILL("Use Skill");
//
//        private String actionLabel;
//
//        ActionList(String label){
//            actionLabel = label;
//        }
//    }

    //Actions occur between two people
    private NPC npc;

    public Action(NPC npc){
        this.npc = npc;
    }
    protected abstract void activate();
    public abstract String getName();
}
