package models.skills.SummonerSkills;

import models.entities.Entity;
import models.skills.PassiveSkill;
import models.skills.Skill;

/**
 * Created by aseber on 2/24/16.
 */
public class BoonSkill extends PassiveSkill {

    @Override
    protected SkillDictionary initID() {

        return Skill.SkillDictionary.BOON;

    }

    @Override
    public void onUpdate(Entity entity) {



    }

}
