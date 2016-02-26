package models.skills;

import models.entities.Entity;

import java.awt.event.KeyEvent;

/**
 * Created by aseber on 2/24/16.
 */
public abstract class ActiveSkill extends Skill  {

    public abstract void onActivate(Entity entity);

    public abstract KeyEvent[] initActivatorKeys();

}
