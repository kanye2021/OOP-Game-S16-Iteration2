package models.skills;

import models.conditions.StatCondition;
import models.entities.Entity;

import java.awt.event.KeyEvent;

/**
 * Created by aseber on 2/24/16.
 */
public abstract class ActiveSkill extends Skill  {

    protected StatCondition statsCondition;//all active skills cost something

    public abstract void onActivate(Entity entity);


    //activeSkills return true for activeSkill check
    @Override
    public boolean isActive() {
        return true;
    }

    public abstract KeyEvent[] initActivatorKeys();

}
