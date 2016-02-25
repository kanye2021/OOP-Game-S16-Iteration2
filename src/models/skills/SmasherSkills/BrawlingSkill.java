package models.skills.SmasherSkills;

import models.entities.Entity;
import models.skills.PassiveSkill;

/**
 * Created by aseber on 2/24/16.
 */
public class BrawlingSkill extends PassiveSkill {

    @Override
    protected SkillDictionary initID() {

        return SkillDictionary.BRAWLING;

    }

    @Override
    public void onUpdate(Entity entity) {



    }

}
