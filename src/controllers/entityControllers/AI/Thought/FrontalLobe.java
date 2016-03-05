package controllers.entityControllers.AI.Thought;

import controllers.entityControllers.AI.Vision.VisualInformation;
import models.entities.Entity;
import models.entities.npc.NPC;

import java.awt.*;

/**
 * Created by Bradley on 3/5/16.
 */
public class FrontalLobe {

    private NPC npc;
    private Personalities personality;

    public FrontalLobe(NPC npc, Personalities personality){
        this.npc = npc;
        this.personality = personality;

    }

    public Decision process(VisualInformation visualInfo){

        // Declare the decision in this outer scope
        Decision decision = null;

        // Entities take priority over items. If an entity was found, determine whether or not to pursue it.
        if(visualInfo.foundEntities()){
            // For now, intereract with the first entity in the list.
            Entity entity = visualInfo.getEntities().get(0);
            double rand = Math.random();

            // Attacking takes priority over trading.
            if(personality.getAttackOnSightProbability() >= rand){
                decision = Decision.ATTACK;
                decision.addAttachment(entity);
            }
            else if(personality.getTradeProbability() >= rand){
                decision = Decision.TRADE;
                decision.addAttachment(entity);
            }
        }else if(visualInfo.foundItems()){
            Point itemLocation = visualInfo.getitemLocations().get(0);

            decision = Decision.GET_ITEM;
            decision.addAttachment(itemLocation);
        }else{
            decision = Decision.DEFAULT;
        }

        return decision;
    }


}
