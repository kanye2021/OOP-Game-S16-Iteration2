package controllers.entityControllers.AI.Thought;

import controllers.entityControllers.AI.Vision.VisualInformation;
import models.entities.Entity;
import models.entities.npc.NPC;

/**
 * Created by dyeung on 3/10/16.
 */
public class MountFrontalLobe extends FrontalLobe {

    private Entity owner;
    //private int maximumDistanceFromOwner = 0;

    public MountFrontalLobe(NPC npc, Personalities personality) {
        super(npc, personality);
        owner = null;
    }

    @Override
    public Decision process(VisualInformation visualInfo) {

        Decision decision = super.process(visualInfo); // Get the decision that was made from the super class.

        // If the mount does have an owner, make sure it moves when the owner moves
        if(owner != null){
//            // Make sure the pet is not trying to attack its owner
//            if(decision == Decision.ATTACK){
//                Entity entity = (Entity) decision.getAttachment();
//                if(entity.equals(owner)){
//                    decision = Decision.DEFAULT;
//                }
//            }
//            double distanceFromOwner = npc.getLocation().distance(owner.getLocation());
            decision = Decision.FOLLOW;
            decision.addAttachment(owner);
        }
        //else if the mount does not have an owner, return the original decision
        return decision;
    }

    public void makeOwner(Entity entity){
        owner = entity;
    }
}
