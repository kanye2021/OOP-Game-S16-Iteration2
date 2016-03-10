package models.skills.SmasherSkills;

import models.entities.Entity;
import models.skills.PassiveSkill;
import models.skills.SkillList;
import models.stats.Stats;

import java.awt.event.KeyEvent;

/**
 * Created by aseber on 2/24/16.
 */
//TODO:Figure out if level is connected to entity
public class OneHandedWeaponSkill extends PassiveSkill {
    private int baseDamage;
    private int baseSpeed;
    private int finalDamage;
    private int oneHandedWeaponLv;

    public OneHandedWeaponSkill(){
        baseDamage=MID;
        baseSpeed=MID;
        oneHandedWeaponLv=1;
        cooldownTime=MIDTIME;
        cooldown=false;
    }

    @Override
    public SkillDictionary initID() {

        return SkillDictionary.ONE_HANDED_WEAPON;

    }

    @Override
    public String getName() {
        return "One Handed Weapon Mastery";
    }

    @Override
    public void onUpdate(Entity entity) {
        oneHandedWeaponLv=getLevel();
        finalDamage = baseDamage + oneHandedWeaponLv;
    }

    public int getCombatWeight(Entity entity){return (1*entity.getStats().getStat(Stats.Type.TOTAL_WEIGHT));}


    public int getFinalDamage() {
        return finalDamage;
    }

    public int getBaseSpeed() {
        return baseSpeed;
    }
}
