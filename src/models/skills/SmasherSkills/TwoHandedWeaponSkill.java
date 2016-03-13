package models.skills.SmasherSkills;

import models.attack.LinearAttack;
import models.attack.Projectile;
import models.attack.StatusEffects;
import models.entities.Entity;
import models.skills.ActiveSkill;
import models.skills.PassiveSkill;
import models.skills.Skill;
import models.stats.Stats;
import views.sprites.Sprite;

import java.awt.event.KeyEvent;

/**
 * Created by aseber on 2/24/16.
 */
//TODO:Figure out if level is connected to entity
public class TwoHandedWeaponSkill extends ActiveSkill {
    private int baseDamage;
    private int baseSpeed;
    private int finalDamage;
    private int twoHandedWeaponLv;
    public TwoHandedWeaponSkill(){
        cooldownTime = 3*SECONDS;
        cooldown = false;
    }
    @Override
    public SkillDictionary initID() {

        return Skill.SkillDictionary.TWO_HANDED_WEAPON;

    }
    @Override
    public String getName() {
        return "Two Handed Weapon Mastery";
    }


    @Override
    public Sprite initSprite() {
        return new Sprite(SKILL_ROOT_FILE_PATH + "smasher-twoHandedSkill.png");
    }

    @Override
    public void onActivate(Entity entity) {
        if(isCooldown()){
            return;
        }
        doTheCoolDown();
        int strength = entity.getStats().getStat(Stats.Type.STRENGTH);
        Projectile projectile = new Projectile(2*damageSent(entity),1, StatusEffects.StatusEffect.NONE);
        new LinearAttack(entity,projectile);
    }

    @Override
    public KeyEvent[] initActivatorKeys() {
        return null;
    }


    public int getCombatWeight(Entity entity){return (2*entity.getStats().getStat(Stats.Type.TOTAL_WEIGHT));}


    public int getFinalDamage() {
        return finalDamage;
    }

    public int getBaseSpeed() {
        return baseSpeed;
    }
}
