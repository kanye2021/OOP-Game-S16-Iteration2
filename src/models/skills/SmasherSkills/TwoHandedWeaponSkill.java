package models.skills.SmasherSkills;

import models.entities.Entity;
import models.skills.PassiveSkill;
import models.skills.Skill;
import models.stats.Stats;

/**
 * Created by aseber on 2/24/16.
 */
//TODO:Figure out if level is connected to entity
public class TwoHandedWeaponSkill extends PassiveSkill {
    private int baseDamage;
    private int baseSpeed;
    private int finalDamage;
    private int twoHandedWeaponLv;
    public TwoHandedWeaponSkill(){
        baseDamage=HIGH;
        baseSpeed=LOW;
        twoHandedWeaponLv = 1;
        cooldownTime = HIGHTIME;
        cooldown = false;
    }
    @Override
    public SkillDictionary initID() {

        return Skill.SkillDictionary.TWO_HANDED_WEAPON;

    }
    @Override
    public String getName() {
        return "Two-Handed-Weapon-Mastery";
    }

    @Override
    public void onUpdate(Entity entity) {
        twoHandedWeaponLv = getLevel();
        finalDamage = baseDamage + twoHandedWeaponLv;
    }

    public int getCombatWeight(Entity entity){return (2*entity.getStats().getStat(Stats.Type.TOTAL_WEIGHT));}


    public int getFinalDamage() {
        return finalDamage;
    }

    public int getBaseSpeed() {
        return baseSpeed;
    }
}
