package AI.Memory;

import AI.Personality.Personality;
import models.entities.Entity_Action_Interface;

/**
 * Created by aseber on 3/12/16.
 */
public interface MotorInterface {

    Decision getDecision();

    Personality getPersonality();

    Entity_Action_Interface getNPC();

}
