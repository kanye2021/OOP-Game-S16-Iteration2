package models.skills.SummonerSkills;

import models.entities.Entity;
import models.skills.PassiveSkill;

/**
 * Created by aseber on 2/24/16.
 */
public class EnchantmentSkill extends PassiveSkill {

    @Override
    protected SkillDictionary initID() {

        return SkillDictionary.ENCHANTMENT;

    }

    @Override
    public void onUpdate(Entity entity) {



    }

}
