package models.skills.SummonerSkills;

import models.entities.Entity;
import models.skills.ActiveSkill;

import java.awt.event.KeyEvent;

/**
 * Created by aseber on 2/24/16.
 */
public class StaffSkill extends ActiveSkill {
    public StaffSkill(){
        cooldownTime = 1*SECONDS;//Inconsitant with lowtime because of active and passive skill
        cooldown=false;
    }
    @Override
    public SkillDictionary initID() {

        return SkillDictionary.STAFF;

    }

    @Override
    public void onActivate(Entity entity) {
        if(cooldown){
            System.out.println("You have not chilled yet");
        }
        cooldown = true;
        System.out.println("I am staff skill");
        //line of code to attack here.
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
