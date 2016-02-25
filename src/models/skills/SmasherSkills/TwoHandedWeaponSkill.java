package models.skills.SmasherSkills;

import models.entities.Entity;
import models.skills.PassiveSkill;
import models.skills.Skill;

/**
 * Created by aseber on 2/24/16.
 */
public class TwoHandedWeaponSkill extends PassiveSkill {

    @Override
    protected SkillDictionary initID() {

        return Skill.SkillDictionary.TWO_HANDED_WEAPON;

    }

    @Override
    public void onUpdate(Entity entity) {



    }

}
