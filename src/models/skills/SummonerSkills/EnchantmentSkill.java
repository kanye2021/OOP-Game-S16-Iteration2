package models.skills.SummonerSkills;

import models.attack.Projectile;
import models.attack.RadialAttack;
import models.attack.StatusEffects;
import models.entities.Entity;
import models.skills.ActiveSkill;
import models.skills.PassiveSkill;

import java.awt.event.KeyEvent;

/**
 * Created by aseber on 2/24/16.
 */
public class EnchantmentSkill extends ActiveSkill {
    private int damage;
    private int range;
    private Projectile projectile;
    public EnchantmentSkill(){
        cooldown = false;
        cooldownTime = 4*SECONDS;
        damage = 0;
        range = 4;
        projectile = new Projectile(damage,range, StatusEffects.StatusEffect.SLEEP);
    }
    @Override
    public SkillDictionary initID() {

        return SkillDictionary.ENCHANTMENT;

    }

    @Override
    public String getName() {
        return "Enchantment";
    }


    @Override
    public void onActivate(Entity entity) {
        System.out.println("Enchantment skill is used");
        new RadialAttack(entity,projectile);
    }

    @Override
    public KeyEvent[] initActivatorKeys() {
        return new KeyEvent[0];
    }
}
