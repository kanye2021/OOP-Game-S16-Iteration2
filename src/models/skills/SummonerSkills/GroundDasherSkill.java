package models.skills.SummonerSkills;

import models.Attack;
import models.attack.AngularAttack;
import models.attack.Projectile;
import models.entities.Entity;
import models.skills.ActiveSkill;
import models.stats.Stats;

import java.awt.event.KeyEvent;

/**
 * Created by ben on 3/8/16.
 */
//GroundDasher is a skill that is done at an angle
public class GroundDasherSkill extends ActiveSkill{
    private int damage = 1;
    private int range = 3;
    private Projectile projectile;
    public GroundDasherSkill(){
        damage = 1;
        range = 3;
        projectile = new Projectile(damage,range);
    }
    @Override
    public SkillDictionary initID() {

        return SkillDictionary.GROUND_DASHER;

    }

    @Override
    public void onActivate(Entity entity) {
        if(cooldown){
            System.out.println("ANOTHA ONE");
            return;
        }
        cooldown = true;
        System.out.println("Bruh its OG Dasher");
        new AngularAttack(entity,projectile);
        //This attack is in the models
        //new Attack(entity);
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
