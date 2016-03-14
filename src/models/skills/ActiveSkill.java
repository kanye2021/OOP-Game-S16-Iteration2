package models.skills;

import models.conditions.StatCondition;
import models.entities.Entity;
import utilities.Animator;
import models.stats.Stats;


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

    public int damageSent(Entity entity){
        int strength = entity.getStats().getStat(Stats.Type.STRENGTH);
        int intellect = entity.getStats().getStat(Stats.Type.INTELLECT);
        int itemStrength = entity.getStats().getStat(Stats.Type.OFFSENSIVE_RATING);
        int agility = entity.getStats().getStat(Stats.Type.AGILITY);
        return(strength+intellect+itemStrength+agility);
    }
    //protected int level;

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


    public int getKeyBind() {
        return keyBind;
    }

    public void setKeyBind(int keyBind) {
        this.keyBind = keyBind;
    }



}
