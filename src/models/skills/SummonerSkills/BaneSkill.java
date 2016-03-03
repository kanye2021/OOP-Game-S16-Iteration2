package models.skills.SummonerSkills;

import models.entities.Entity;
import models.skills.PassiveSkill;

/**
 * Created by aseber on 2/24/16.
 */
public class BaneSkill extends PassiveSkill {
    public BaneSkill(){
        cooldown = false;
        cooldownTime = 1 *SECONDS;
    }
    @Override
    public SkillDictionary initID() {

        return SkillDictionary.BANE;

    }

    @Override
    public void onUpdate(Entity entity) {



    }

}
