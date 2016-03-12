package models.attack;

import models.map.Map;
import utilities.Animator;

/**
 * Created by ben on 3/8/16.
 */
public class Projectile {
    //Things I would need:
    //Attack
    //
    protected int damage;
    protected int range;
    protected int speed;


    //Put status effects here
    public Projectile(int damage,int range,int speed){
        this.damage = damage;
        this.range = range;
        this.speed = speed;
    }
}
