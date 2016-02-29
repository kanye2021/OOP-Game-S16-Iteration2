package models.entities.npc;

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
    public Action(){

    }
    protected abstract void activate();
}
