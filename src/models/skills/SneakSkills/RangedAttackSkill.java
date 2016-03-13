package models.skills.SneakSkills;

import models.attack.AngularAttack;
import models.attack.Projectile;
import models.attack.StatusEffects;
import models.entities.Entity;
import models.skills.ActiveSkill;
import models.skills.PassiveSkill;

import java.awt.event.KeyEvent;

/**
 * Created by aseber on 2/25/16.
 */
public class RangedAttackSkill extends ActiveSkill {
    public RangedAttackSkill(){
        cooldown = false;
        cooldownTime=1*SECONDS;


    }
    @Override
    public SkillDictionary initID() {

        return SkillDictionary.RANGED_ATTACK;

    }

    @Override
    public String getName() {
        return "Ranged Attack Mastery";
    }


    @Override
    public void onActivate(Entity entity) {
        if(isCooldown()){
            return;
        }
        cooldown = true;
        doTheCoolDown();
        int damage  = damageSent(entity);
        Projectile projectile = new Projectile(damage,3, StatusEffects.StatusEffect.NONE);
        new AngularAttack(entity,projectile);

    }

    @Override
    public KeyEvent[] initActivatorKeys() {
        return null;
    }
}
