package models.attack;

import models.Attack;
import models.entities.Entity;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
/**
 * Created by ben on 3/8/16.
 */
public class RadialAttack extends Attack{
    public RadialAttack(Entity entity, Projectile projectile){
        this.origin = entity.getLocation();
        this.damage = projectile.damage;
        this.range = projectile.range;
        this.orientation = entity.getOrientation();
    }

    public void findBreadthFirstTile(){
    Queue<PointNode> pointQueue = new LinkedList<>();
     //pointQueue.add(new PointNode(origin))
    }
    class PointNode{
        Point target;

        public PointNode(Point origin,int distance){
            target = new Point();
            this.target.x=origin.x+distance;
            this.target.y = origin.y+distance;
        }
    }
    @Override
    public void calculateDamage() {

    }
}
