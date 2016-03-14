package models.skills.SummonerSkills;

import models.attack.Projectile;
import models.attack.RadialAttack;
import models.attack.StatusEffects;
import models.entities.Entity;
import models.skills.ActiveSkill;

import java.awt.event.KeyEvent;

/**
 * Created by ben on 3/8/16.
 */

//Indignation is a skill that is an area effect skill
    //It is the strongest spell according to Tales of Symphonia

public class IndignationSkill extends ActiveSkill{
    Projectile projectile;
    private int damage;
    private int range;
    public IndignationSkill(){
        cooldown = false;
        cooldownTime = 3*SECONDS;
        damage = 1;
        range = 3;
        projectile = new Projectile(damage,range, StatusEffects.StatusEffect.NONE, "summoner-indignation.png");
        cost = 10;
        level = 1;
    }
    @Override
    public SkillDictionary initID() {

        return SkillDictionary.INDIGNATION;

    }

    @Override
    public String getName() {
        return "Indigniation";
    }

    @Override
    public void onActivate(Entity entity) {
        if(isCooldown()){
            return;
        }
        if(!payMana(entity,cost)){
            return;
        }
        doTheCoolDown();
        System.out.println("Can you take this? INDIGNATION!");

        new RadialAttack(entity,projectile);

    }

    @Override
    public KeyEvent[] initActivatorKeys() {

        // This initializes the key to activate this skill! It should not be null if we intend to actually
        // use the skill
        return null;

    }
}
