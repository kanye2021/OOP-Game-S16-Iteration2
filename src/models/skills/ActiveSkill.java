package models.skills;

import models.conditions.StatCondition;
import models.entities.Entity;
import models.stats.Stats;

import java.awt.event.KeyEvent;

/**
 * Created by aseber on 2/24/16.
 */
public abstract class ActiveSkill extends Skill  {

    protected StatCondition statsCondition;//all active skills cost something

    public abstract void onActivate(Entity entity);

    protected int cost;

    //activeSkills return true for activeSkill check
    @Override
    public boolean isActive() {
        return true;
    }

    public abstract KeyEvent[] initActivatorKeys();

    public boolean payMana(Entity entity,int delta){
        int mana = entity.getStats().getStat(Stats.Type.MANA);
        if(mana>=cost){
            //takes away mana and allows action to happen
            entity.getStats().modifyStat(Stats.Type.MANA,-delta);
            return true;
        }
        return false;
    }
}
