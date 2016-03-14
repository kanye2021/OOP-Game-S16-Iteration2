package models.attack;

import models.Attackion;
import models.entities.Entity;
import models.map.Map;
import models.map.Tile;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
/**
 * Created by ben on 3/8/16.
 */

//You are only supposed to call the constructor.  I.e new LinearAttack(Entity,Projectile);
public class LinearAttack extends Attackion{
    Point slope;
    Projectile projectile;

    public LinearAttack(Entity entity,Projectile projectile){
        this.projectile = projectile;
        this.entity = entity;
        //If you cannot attack
        if(!canAttack(entity)){
            return;
        }
        this.map = entity.getMap();
        this.origin = entity.getLocation();
        this.damage = projectile.damage;
        this.range = projectile.range;
        this.orientation = entity.getOrientation();
        this.projectile = projectile;
        slope = new Point();
        findSlope(orientation);
        launchAttack();
    }

    private void launchAttack(){
        new Thread(new Runnable() {
            public void run() {
                findEffectedTiles(slope); // Launches attack
            }
        }).start();
    }

   /* @Override
    public boolean canAttack(Entity entity) {
        if(entity.getStatusEffect()== StatusEffects.StatusEffect.SLEEP){
            return false;
        }
        return true;
    }*/

    @Override
    public void calculateDamage() {

    }

    public void findSlope(Map.Direction orientation){


        if(orientation == Map.Direction.NORTH){
            slope.x=0;
            slope.y=-1;

        }
        else if(orientation == Map.Direction.NORTH_EAST){
            slope.x = 1;
            slope.y = -1;
        }
        else if(orientation == Map.Direction.SOUTH_EAST){
            slope.x = 1;
            slope.y = 0;
        }
        else if(orientation == Map.Direction.SOUTH){
            slope.x = 0;
            slope.y = 1;
        }
        else if(orientation == Map.Direction.SOUTH_WEST){
            slope.x = -1;
            slope.y = 1;
        }
        else if(orientation == Map.Direction.NORTH_WEST){
            slope.x = -1;
            slope.y = 0;
        }else{
            System.out.println("LinearAttack: HOW DID YOU GET THERE");
        }
    }

    public void findEffectedTiles(Point slope){
//        projectile.projectileMove(entity.getOrientation());
        Queue<PointNode> pointQueue = new LinkedList<>();

        for(int i = 1;i<=range;i++){
            pointQueue.add(new PointNode(origin,slope,i));//adds in all the nodes
        }


        while(!pointQueue.isEmpty()){


            PointNode current = pointQueue.poll();//returns the top
            Point attackPoint = new Point();

            attackPoint.x=current.target.x;
            attackPoint.y=current.target.y;
            Tile desiredTile = map.getTileAt(attackPoint);
            if(desiredTile!=null){
                // Add the projectile to the tile.
                map.insertProjectileAtPoint(projectile, attackPoint);

                if(desiredTile.hasEntity()){
                    Entity target = desiredTile.getEntity();
                    target.takeDamage(-damage);

                }

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Remove the projectile from the tile.
                map.removeProjectileAtPoint(attackPoint);
            }
        }
    }

    class PointNode{
        Point target;

        public PointNode(Point origin,Point offset,int distance){
            target = new Point();
            this.target.x=origin.x+offset.x*distance;
            this.target.y = origin.y+offset.y*distance;
        }
    }
}
