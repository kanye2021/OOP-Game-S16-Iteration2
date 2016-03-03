package models.skills.SneakSkills;

import models.entities.Entity;
import models.skills.ActiveSkill;
import models.stats.Stats;

import java.awt.event.KeyEvent;

/**
 * Created by aseber on 2/25/16.
 */
public class CreepSkill extends ActiveSkill {
    private javax.swing.Timer debuffTimer;
    private int debuffTimerDelay;

    private final int cost = 10;
    private final double constant = 0.5;//reduces speed by half
    @Override
    public SkillDictionary initID() {

        return SkillDictionary.CREEP;

    }

    @Override
    public void onActivate(Entity entity) {
    //need to use alphacomposite on entity here
        int mana = entity.getStats().getStat(Stats.Type.MANA);
        if(mana > cost){
            Stats stats = entity.getStats();
            //int originalSpeed = stats.getMovement();
            //double entityFinalSpeed = stats.getMovement() * constant;
            //need a timer here
            int delta = 3;
            stats.modifyStat(Stats.Type.MOVEMENT, -delta);//decreases speed by a constant
            //TODO:show that the avatar looks invisible
            //TODO:implement back attack to cause extra damaage
            //This timer means after 5 seconds it will revert movement back to the old speed
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            stats.modifyStat(Stats.Type.MOVEMENT, delta);
                            //TODO:make avatar look visible again
                        }
                    },
                    5000
            );
        }

    }

    @Override
    public KeyEvent[] initActivatorKeys() {


        return null;

    }

}
