package models.skills;

import models.entities.Entity;

/**
 * Created by aseber on 2/24/16.
 */
public abstract class ActiveSkill extends Skill  {

    public abstract void onActivate(Entity entity);

}
