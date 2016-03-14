package models.skills.SummonerSkills;

import models.attack.AngularAttack;
import models.attack.Projectile;
import models.attack.StatusEffects;
import models.entities.Entity;
import models.skills.ActiveSkill;
import views.sprites.Sprite;

import java.awt.event.KeyEvent;

/**
 * Created by ben on 3/8/16.
 */
//GroundDasher is a skill that is done at an angle
public class GroundDasherSkill extends ActiveSkill {
    private int damage = 1;
    private int range = 3;
    private Projectile projectile;

    public GroundDasherSkill() {
        damage = 1;

        range = 5;

        cooldownTime = 2 * SECONDS;
        projectile = new Projectile(damage, range, StatusEffects.StatusEffect.NONE, "summoner-groundDasher.png");
        cost = 10;
        level = 1;
    }

    @Override
    public SkillDictionary initID() {

        return SkillDictionary.GROUND_DASHER;

    }

    @Override
    public String getName() {
        return "Ground Dasher";
    }

    @Override
    public Sprite initSprite() {
        return new Sprite(SKILL_ROOT_FILE_PATH + "summoner-groundDasher.png");
    }


    @Override
    public void onActivate(Entity entity) {

        if (isCooldown()) {
            System.out.println("ANOTHA ONE");
            return;
        }
        if (!payMana(entity, cost)) {
            return;
        }
        doTheCoolDown();
        System.out.println("Bruh its OG Dasher");
        new AngularAttack(entity, projectile);
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
