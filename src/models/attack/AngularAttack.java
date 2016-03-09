package models.attack;

import models.Attack;
import models.entities.Entity;

/**
 * Created by ben on 3/8/16.
 */
public class AngularAttack extends Attack{

    public AngularAttack(Entity entity,Projectile projectile){
        this.origin = entity.getLocation();
        this.damage = projectile.damage;
        this.range = projectile.range;
        this.orientation = entity.getOrientation();
    }
    @Override
    public void calculateDamage() {

    }
}
