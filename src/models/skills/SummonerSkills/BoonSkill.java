package models.skills.SummonerSkills;

import models.entities.Entity;
import models.skills.ActiveSkill;
import models.skills.PassiveSkill;
import models.skills.Skill;
import models.stats.Stats;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by aseber on 2/24/16.
 */
public class BoonSkill extends ActiveSkill {
    public BoonSkill(){
        cooldown = false;
        cooldownTime = 5000;//5000 milliseconds
    }
    @Override
    public SkillDictionary initID() {

        return Skill.SkillDictionary.BOON;

    }

    @Override
    public String getName() {
        return "Boon";
    }

    public void buff(Stats stats,int delta){
        stats.modifyStat(Stats.Type.HARDINESS,delta);
        stats.modifyStat(Stats.Type.STRENGTH,delta);
        stats.modifyStat(Stats.Type.MOVEMENT,delta);
    }
    @Override
    public void onActivate(Entity entity) {
        if(cooldown){
            System.out.println("ANOTHA ONE");
            return;
        }
        cooldown = true;
        System.out.println("Boon Skill Used");
        Stats stats = entity.getStats();
        int delta = 5;
        stats.modifyStat(Stats.Type.HEALTH,delta);
        buff(stats,delta);
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        buff(stats,-delta);
                        System.out.println("Got Debuffed");
                        cooldown = false;
                    }
                },
                cooldownTime
        );


    }

    @Override
    public KeyEvent[] initActivatorKeys() {
        return null;
    }

    @Override
    public ArrayList<Image> initSprite() {
        return null;
    }
}
