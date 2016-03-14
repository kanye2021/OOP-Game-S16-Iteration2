package models.skills.SummonerSkills;

import models.attack.Projectile;
import models.attack.RadialAttack;
import models.attack.StatusEffects;
import models.entities.Entity;
import models.skills.ActiveSkill;
import models.skills.PassiveSkill;
import views.sprites.Sprite;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

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
        cost = 10;
        level = 1;
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
        if(isCooldown()){
            System.out.println("CoolDown in affect");
            return;
        }
        if(!payMana(entity,cost)){
            return;
        }

        doTheCoolDown();
        new RadialAttack(entity,projectile);

    }

    @Override
    public KeyEvent[] initActivatorKeys() {
        return new KeyEvent[0];
    }

    @Override
    public Sprite initSprite() {
        return null;
    }


}
