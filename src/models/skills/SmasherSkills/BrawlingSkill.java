package models.skills.SmasherSkills;

import models.attack.LinearAttack;
import models.attack.Projectile;
import models.attack.StatusEffects;
import models.entities.Entity;
import models.skills.ActiveSkill;
import models.skills.PassiveSkill;
import models.stats.Stats;

import java.awt.event.KeyEvent;

/**
 * Created by aseber on 2/24/16.
 */

public class BrawlingSkill extends ActiveSkill {
    private int baseDamage;
    private int baseSpeed;
    private int finalDamage;
    private int brawlLv;

    public BrawlingSkill(){
       cooldownTime = 1*SECONDS;
        cooldown = false;
        baseDamage = 10;
    }
    @Override
    public SkillDictionary initID() {

        return SkillDictionary.BRAWLING;

    }
    @Override
    public String getName() {
        return "Brawling Mastery";
    }

    @Override
    public void onActivate(Entity entity) {
        if(isCooldown()){
            return;
        }
        doTheCoolDown();

        Projectile projectile = new Projectile(damageSent(entity)/2,1, StatusEffects.StatusEffect.NONE);
        new LinearAttack(entity,projectile);

    }

    @Override
    public KeyEvent[] initActivatorKeys() {
        return new KeyEvent[0];
    }

    public int getCombatWeight(Entity entity){return ((int) 0.5*entity.getStats().getStat(Stats.Type.TOTAL_WEIGHT));}
    public int getFinalDamage(){//Used for combat always gets final damage
        return finalDamage;
    }

    public int getBaseSpeed(){
        return baseSpeed;
    }
}
