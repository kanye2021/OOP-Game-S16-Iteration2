package models.skills.CommonSkills;

import models.entities.Avatar;
import models.entities.Entity;
import models.skills.ActiveSkill;
import models.skills.PassiveSkill;
import views.sprites.Sprite;

import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * Created by aseber on 2/24/16.
 */
public class ObservationSkill extends PassiveSkill {
    private int observationLv;
    private float percentError;
    private int upperBoundError;
    private int lowerBoundError;
    private final float constant = .04f;
    public ObservationSkill(){
        observationLv = 1;
        percentError = 1.0f;
//        upperBoundError = percentError;
//        lowerBoundError = -percentError;
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
        percentError = 1.0f - constant * level;
        percentError = percentError < 0.001 ? 0.001f : percentError;
    }

    @Override
    public Sprite initSprite() {
        return new Sprite(SKILL_ROOT_FILE_PATH + "common-observation.png");
    }


    //TODO:Get this to be displayed on the area viewport
    public int getCombatPercentError(int expected) {
//        resetBounds();
        Random random = new Random();
        float sign = random.nextFloat();
        sign = sign > 0.5 ? 1.0f : -1.0f;

        float error = random.nextFloat() * percentError;

        return (int) (sign * (error * expected) + expected);

//        Random random = new Random();
//        return (random.nextInt(upperBoundError-lowerBoundError+1)+lowerBoundError);
    }

}
