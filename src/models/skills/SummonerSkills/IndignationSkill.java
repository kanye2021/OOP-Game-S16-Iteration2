package models.skills.SummonerSkills;

import models.entities.Entity;
import models.skills.ActiveSkill;
import models.stats.Stats;

import java.awt.event.KeyEvent;

/**
 * Created by ben on 3/8/16.
 */

//Indignation is a skill that is an area effect skill
    //It is the strongest spell according to Tales of Symphonia

public class IndignationSkill extends ActiveSkill{
    @Override
    public SkillDictionary initID() {

        return SkillDictionary.INDIGNATION;

    }
    @Override
    public void onActivate(Entity entity) {
        if(cooldown){

            return;
        }
        cooldown = true;
        System.out.println("Can you take this? INDIGNATION!");
        Stats stats = entity.getStats();
        int delta = 5;

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {

                        System.out.println("Times Up");
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
