package models.skills.SneakSkills;

import models.entities.Entity;
import models.skills.PassiveSkill;

/**
 * Created by aseber on 2/25/16.
 */
public class RangedAttackSkill extends PassiveSkill {

    @Override
    protected SkillDictionary initID() {

        return SkillDictionary.RANGED_ATTACK;

    }

    @Override
    public void onUpdate(Entity entity) {



    }

}
