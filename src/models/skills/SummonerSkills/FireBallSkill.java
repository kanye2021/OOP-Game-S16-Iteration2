package models.skills.SummonerSkills;

import models.attack.LinearAttack;
import models.attack.Projectile;
import models.attack.StatusEffects;
import models.entities.Avatar;
import models.entities.Entity;
import models.skills.ActiveSkill;
import models.stats.Stats;

import java.awt.event.KeyEvent;

/**
 * Created by ben on 3/8/16.
 */
//From Tales of symphonia is a linear path of a fire ball
    //Kinda like fireball jutsu if you want to think of it like that
public class FireBallSkill extends ActiveSkill{
    private int damage;
    private int range;

    public FireBallSkill(){
        damage = 5;
        range = 3;
        cooldownTime = 1*SECONDS;
        cost = 10;
    }

    @Override
    public SkillDictionary initID() {

        return SkillDictionary.FIREBALL;

    }

    @Override
    public String getName() {
        return "Fireball";
    }


    @Override
    public void onActivate(Entity entity) {
        if(cooldown){
            return;
        }

        System.out.println("Can you take this? Fireball!");
        Projectile projectile = new Projectile(damage,range, StatusEffects.StatusEffect.NONE);
        if(!payMana(entity,cost)){
            return;
        }
        cooldown=true;
        new LinearAttack(entity,projectile);//This is the attack
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {

                        System.out.println("Times up");
                        cooldown = false;
                    }
                },
                cooldownTime
        );

    }



    @Override
    public KeyEvent[] initActivatorKeys() {

        // This initializes the key to activate this skill! It should not be null if we intend to actually
        // use the skill
        return null;

    }
}
