package AI.Memory;

import AI.Personality.Personality;
import models.entities.npc.NPC;

/**
 * Created by aseber on 3/12/16.
 */
public interface MotorInterface {

    Decision getDecision();

    Personality getPersonality();

    NPC getNPC();

}
