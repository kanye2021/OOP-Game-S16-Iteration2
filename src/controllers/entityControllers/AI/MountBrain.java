package controllers.entityControllers.AI;

import controllers.entityControllers.AI.Thought.MountFrontalLobe;
import controllers.entityControllers.AI.Thought.Personalities;
import models.entities.Entity;
import models.entities.npc.NPC;

/**
 * Created by dyeung on 3/10/16.
 */
public class MountBrain extends Brain {
    public MountBrain(NPC npc, Personalities personality) {
        super(npc, personality);
        this.frontalLobe = new MountFrontalLobe(npc, personality); // Swap out the default frontal lobe for a mount one.

    }

    public void makeOwner(Entity entity) {
        ((MountFrontalLobe) frontalLobe).makeOwner(entity);
    }
}
