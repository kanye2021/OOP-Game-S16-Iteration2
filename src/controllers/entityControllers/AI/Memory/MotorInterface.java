package controllers.entityControllers.AI.Memory;

import controllers.entityControllers.AI.Personality.Personality;

/**
 * Created by aseber on 3/12/16.
 */
public interface MotorInterface {

    Decision getDecision();
    Personality getPersonality();
    void setNewDecision(Decision decision);

}
