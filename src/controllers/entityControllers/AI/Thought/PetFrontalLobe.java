//package controllers.entityControllers.AI.Thought;
//
//import controllers.entityControllers.AI.Vision.VisualInformation;
//import models.entities.Entity;
//import models.entities.npc.NPC;
//
///**
// * Created by Bradley on 3/7/2016.
// */
//public class PetFrontalLobe extends FrontalLobe{
//
//    private Entity owner;
//    private int maximumDistanceFromOwner = 5;
//
//    public PetFrontalLobe(NPC npc, Personalities personality) {
//        super(npc, personality);
//        owner = null;
//    }
//
//    @Override
//    public Decision process(VisualInformation visualInfo) {
//
//        Decision decision = super.process(visualInfo); // Get the decision that was made from the super class.
//
//        // If the pet does not have an owner and an entity was found, make that entity its owner.
//        if(owner == null && visualInfo.foundEntities()){
//            this.owner = visualInfo.getEntities().get(0);
//        }
//
//        // If the pet does have an owner, make sure it is within range.
//        if(owner != null){
//            // Make sure the pet is not trying to attack its owner
//            if(decision == Decision.ATTACK){
//                Entity entity = (Entity) decision.getAttachment();
//                if(entity.equals(owner)){
//                    decision = Decision.DEFAULT;
//                }
//            }
//            double distanceFromOwner = npc.getLocation().distance(owner.getLocation());
//
//            if(distanceFromOwner > maximumDistanceFromOwner){
//                decision = Decision.FOLLOW;
//                decision.addAttachment(owner);
//            }
//        }
//
//        return decision;
//    }
//}
