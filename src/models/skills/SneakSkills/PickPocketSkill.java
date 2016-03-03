package models.skills.SneakSkills;

import models.entities.Entity;
import models.skills.ActiveSkill;

import java.awt.event.KeyEvent;

/**
 * Created by aseber on 2/25/16.
 */
public class PickPocketSkill extends ActiveSkill {

    public PickPocketSkill(){
        cooldown = false;
        cooldownTime = 3*SECONDS;
    }
    @Override
    public SkillDictionary initID() {

        return SkillDictionary.PICK_POCKET;

    }

    @Override
    public void onActivate(Entity entity) {
        if(cooldown){
            System.out.println("Cooldown is not over yet!");
            return;
        }
        cooldown = true;
        //TODO:Pick the pockets here!
        System.out.println("I am pick pocket skill");
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
