package models.skills.CommonSkills;

import models.entities.Avatar;
import models.entities.Entity;
import models.skills.PassiveSkill;

import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * Created by aseber on 2/24/16.
 */
public class ObservationSkill extends PassiveSkill {
    private int observationLv;
    private int percentError;
    private int upperBoundError;
    private int lowerBoundError;
    private final int constant = 4;
    public ObservationSkill(){
        observationLv = 1;
        percentError = 100;
        upperBoundError = percentError;
        lowerBoundError = -percentError;
    }

    @Override
    public SkillDictionary initID() {

        return SkillDictionary.OBSERVATION;

    }

    @Override
    public String getName() {
        return "Observation";
    }

    @Override
    public void onUpdate(Entity entity) {
        observationLv = getLevel();//Gets the newly updated level
        percentError = 100 - constant*observationLv;
    }
    public void resetBounds(){
        upperBoundError = percentError;
        lowerBoundError = -percentError;
    }
    //TODO:Get this to be displayed on the area viewport
    public int getCombatPercentError() {
        resetBounds();
        Random random = new Random();
        return (random.nextInt(upperBoundError-lowerBoundError+1)+lowerBoundError);
    }

}
