package models.skills.SummonerSkills;

import models.entities.Entity;
import models.skills.ActiveSkill;
import models.stats.Stats;

import java.awt.event.KeyEvent;

/**
 * Created by ben on 3/8/16.
 */
//GroundDasher is a skill that is done at an angle
public class GroundDasherSkill extends ActiveSkill{

    @Override
    public SkillDictionary initID() {

        return SkillDictionary.GROUND_DASHER;

    }
    @Override
    public String getName() {
        return "Ground Dasher";
    }


    @Override
    public void onActivate(Entity entity) {
        if(cooldown){
            System.out.println("ANOTHA ONE");
            return;
        }
        cooldown = true;
        System.out.println("Bruh its OG Dasher");
        Stats stats = entity.getStats();
        int delta = 5;
        stats.modifyStat(Stats.Type.HEALTH,delta);

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
