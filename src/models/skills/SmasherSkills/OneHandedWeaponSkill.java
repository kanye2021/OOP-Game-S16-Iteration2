package models.skills.SmasherSkills;

import models.attack.LinearAttack;
import models.attack.Projectile;
import models.attack.StatusEffects;
import models.entities.Entity;
import models.skills.ActiveSkill;
import models.stats.Stats;
import views.sprites.Sprite;

import java.awt.event.KeyEvent;

/**
 * Created by aseber on 2/24/16.
 */
//TODO:Figure out if level is connected to entity
public class OneHandedWeaponSkill extends ActiveSkill {
    private int baseDamage;
    private int baseSpeed;
    private int finalDamage;
    private int oneHandedWeaponLv;

    public OneHandedWeaponSkill() {
        cooldownTime = 2 * SECONDS;
        cooldown = false;
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
    public void onActivate(Entity entity) {
        if (isCooldown()) {
            return;
        }
        doTheCoolDown();
        int strength = entity.getStats().getStat(Stats.Type.STRENGTH);
        Projectile projectile = new Projectile(damageSent(entity), 1, StatusEffects.StatusEffect.NONE);
        new LinearAttack(entity, projectile);
    }

    @Override
    public KeyEvent[] initActivatorKeys() {
        return null;
    }

    @Override
    public Sprite initSprite() {
        return new Sprite(SKILL_ROOT_FILE_PATH + "smasher-oneHandedSkill.png");
    }

    public int getCombatWeight(Entity entity) {
        return (1 * entity.getStats().getStat(Stats.Type.TOTAL_WEIGHT));
    }


    public int getFinalDamage() {
        return finalDamage;
    }

    public int getBaseSpeed() {
        return baseSpeed;
    }

    @Override
    public boolean isActive() {
        return false;
    }
}
