package models.skills.SneakSkills;

import models.entities.Entity;
import models.skills.ActiveSkill;

import java.awt.event.KeyEvent;

/**
 * Created by aseber on 2/25/16.
 */
public class DetectRemoveTrapSkill extends ActiveSkill {

    public DetectRemoveTrapSkill(){
        cooldownTime = 3*SECONDS;
        cooldown = false;
    }

    @Override
    public SkillDictionary initID() {

        return SkillDictionary.DETECT_REMOVE_TRAP;

    }

    @Override
    public void onActivate(Entity entity) {
        if(cooldown){
            System.out.println("Cooldown time is not over!");
            return;
        }
        cooldown = true;
        // This needs to take a Item!
        System.out.println("I am detect and remove trap skill");
        //TODO:Do shit here!
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {


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

}
