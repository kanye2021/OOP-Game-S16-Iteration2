package models.attack;

import models.map.Map;

/**
 * Created by ben on 3/8/16.
 */
public class Projectile {

    //Projectiles have damage, range and direction
    protected int damage;
    protected int range;


    //Put status effects here
    public Projectile(int damage, int range){
        this.damage = damage;
        this.range = range;

    }
}
