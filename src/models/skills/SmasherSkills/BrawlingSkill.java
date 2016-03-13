package models.skills.SmasherSkills;

import models.entities.Entity;
import models.skills.PassiveSkill;
import models.stats.Stats;

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
        cooldown = false;
        cooldownTime = LOWTIME;
    }
    @Override
    public SkillDictionary initID() {

        return SkillDictionary.BRAWLING;

    }
    @Override
    public String getName() {
        return "Brawling-Mastery";
    }

    @Override
    public void onUpdate(Entity entity) {
        brawlLv = getLevel();
        finalDamage = baseDamage + brawlLv;

    }
    public int getCombatWeight(Entity entity){return ((int) 0.5*entity.getStats().getStat(Stats.Type.TOTAL_WEIGHT));}
    public int getFinalDamage(){//Used for combat always gets final damage
        return finalDamage;
    }

    public int getBaseSpeed(){
        return baseSpeed;
    }
}
