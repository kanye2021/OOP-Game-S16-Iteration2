package models;

import models.attack.StatusEffects;
import models.entities.Entity;
import models.map.Map;

import java.awt.*;

/**
 * Created by ben on 3/10/16.
 */
public abstract class Attackion {
    protected Entity entity;
    protected Map map;
    protected int damage;//Total damage not including target defense
    protected int range;
    protected Map.Direction orientation;
    protected Point origin;
    protected StatusEffects.StatusEffect statusEffect;

    public abstract void calculateDamage();

    public boolean canAttack(Entity entity) {
        return entity.getStatusEffect() != StatusEffects.StatusEffect.SLEEP;
    }

}