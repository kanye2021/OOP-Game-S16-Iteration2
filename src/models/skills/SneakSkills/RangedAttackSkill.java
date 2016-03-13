package models.skills.SneakSkills;

import models.entities.Entity;
import models.skills.PassiveSkill;

/**
 * Created by aseber on 2/25/16.
 */
public class RangedAttackSkill extends PassiveSkill {
    public RangedAttackSkill(){
        cooldown = false;
        cooldownTime=MIDTIME;
    }
    @Override
    public SkillDictionary initID() {

        return SkillDictionary.RANGED_ATTACK;

    }

    @Override
    public String getName() {
        return "Ranged-Attack-Mastery";
    }


    @Override
    public void onUpdate(Entity entity) {



    }

}
