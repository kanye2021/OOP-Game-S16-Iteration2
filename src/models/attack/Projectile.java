package models.attack;

import models.map.Map;

/**
 * Created by ben on 3/8/16.
 */
public class Projectile {
    //Things I would need:
    //Attack
    //
    protected int damage;
    protected int range;
    protected StatusEffects.StatusEffect statusEffect;
    //Put status effects here
    public Projectile(int damage,int range,StatusEffects.StatusEffect attackEffect){
        this.damage = damage;
        this.range = range;
        this.statusEffect = attackEffect;
    }
}
