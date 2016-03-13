package controllers.entityControllers.AI.Thought;

import controllers.entityControllers.AI.Vision.VisualInformation;
import models.entities.Entity;
import models.entities.npc.NPC;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Bradley on 3/5/16.
 */
public class FrontalLobe {

    protected NPC npc;
    private Personalities personality;
    private ArrayList<Entity> friends;

    public FrontalLobe(NPC npc, Personalities personality){
        this.npc = npc;
        this.personality = personality;
        this.friends = new ArrayList<>();
    }

    public Decision process(VisualInformation visualInfo){

        // Declare the decision in this outer scope
        Decision decision = Decision.DEFAULT;

        // Entities take priority over items. If an entity was found, determine whether or not to pursue it.
        if(visualInfo.foundEntities()){
            // For now, intereract with the first entity in the list.
//            Entity entity = visualInfo.getEntities().get(0);
            double rand = Math.random();

            // Attacking takes priority over trading. But make sure the entity found is not a friend.
            for(Entity ent : visualInfo.getEntities()){
                if(!friends.contains(ent) && personality.getAttackOnSightProbability() >= rand){
                    decision = Decision.ATTACK;
                    decision.addAttachment(ent);
                }else if(personality.getTradeProbability() >= rand){
                    decision = Decision.TRADE;
                    decision.addAttachment(ent);
                }
            }
        }else if(visualInfo.foundItems()){
            Point itemLocation = visualInfo.getitemLocations().get(0);

           // decision = Decision.GET_ITEM;
            decision = Decision.DEFAULT;
            decision.addAttachment(itemLocation);
        }

        return decision;
    }


}
