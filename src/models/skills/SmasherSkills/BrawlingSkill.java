package models.skills.SmasherSkills;

import models.entities.Entity;
import models.skills.PassiveSkill;

/**
 * Created by aseber on 2/24/16.
 */
public class BrawlingSkill extends PassiveSkill {
    private int baseDamage;
    private int baseSpeed;
    private int finalDamage;
    private int brawlLv;
    public BrawlingSkill(){
        baseDamage=LOW;
        baseSpeed=HIGH;
        brawlLv = 1;
    }
    @Override
    protected SkillDictionary initID() {

        return SkillDictionary.BRAWLING;

    }

    @Override
    public void onUpdate(Entity entity) {
        brawlLv = getLevel();
        finalDamage = baseDamage + brawlLv;

    }
    public int getFinalDamage(){//Used for combat always gets final damage
        return finalDamage;
    }

    public int getBaseSpeed(){
        return baseSpeed;
    }
}
