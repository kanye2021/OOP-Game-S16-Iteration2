package models.skills;

import models.conditions.StatCondition;
import models.entities.Entity;
import utilities.Animator;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by aseber on 2/24/16.
 */
public abstract class ActiveSkill extends Skill  {
    //Each projectile with an animator object
    public Animator animator;


    protected StatCondition statsCondition;//all active skills cost something

    public abstract void onActivate(Entity entity);

    // All active skills have a keybinding
    protected int keyBind;

    protected int cost;

    //activeSkills return true for activeSkill check
    @Override
    public boolean isActive() {
        return true;
    }

    public abstract KeyEvent[] initActivatorKeys();

    public abstract ArrayList<Image> initSprite();


    public int getKeyBind() {
        return keyBind;
    }

    public void setKeyBind(int keyBind) {
        this.keyBind = keyBind;
    }


}
