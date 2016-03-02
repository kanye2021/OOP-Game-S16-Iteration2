package models.skills;

import models.entities.Entity;

/**
 * Created by aseber on 2/24/16.
 */
public abstract class PassiveSkill extends Skill {
    //These variables are used for Smasher Skills
    //I didn't create a smasher class to preserve the current structure of the game
    protected final int LOW = 5;
    protected final int MID = 10;
    protected final int HIGH = 15;


    public abstract void onUpdate(Entity entity);


    //Passive skills return false for activeSkill check
    @Override
    public boolean isActive() {
        return false;
    }
}
