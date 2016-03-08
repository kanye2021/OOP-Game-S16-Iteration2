package controllers.entityControllers.AI;

import controllers.entityControllers.AI.Thought.Personalities;
import controllers.entityControllers.AI.Thought.PetFrontalLobe;
import models.entities.Entity;
import models.entities.npc.NPC;

/**
 * Created by Bradley on 3/7/2016.
 */
public class PetBrain extends Brain {

    public PetBrain(NPC npc, Personalities personality) {
        super(npc, personality);
        this.frontalLobe = new PetFrontalLobe(npc, personality); // Swap out the default frontal lobe for a pet one.
    }

    @Override
    public void think() {
        super.think();
    }


}
