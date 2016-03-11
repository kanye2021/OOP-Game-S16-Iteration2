package models.attack;

import models.Attack;
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

    public LinearAttack(Entity entity,Projectile projectile){
        this.entity = entity;
        this.map = entity.getMap();
        this.origin = entity.getLocation();
        this.damage = projectile.damage;
        this.range = projectile.range;
        this.orientation = entity.getOrientation();
        slope = new Point();
        findSlope(orientation);
        findEffectedTiles(slope);
    }

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
            System.out.println("WTF HOW DID YOU GET THERE");
        }
    }

    public void findEffectedTiles(Point slope){

        Queue<PointNode> pointQueue = new LinkedList<>();

        for(int i = 1;i<=range;i++){
            pointQueue.add(new PointNode(origin,slope,i));//adds in all the nodes
        }


        while(!pointQueue.isEmpty()){

           /* new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            Point attackPoint = new Point();
                            PointNode current = pointQueue.poll();//returns the top
                            //Point attackPoint = new Point(0,0);

                            attackPoint.x=current.target.x;
                            attackPoint.y=current.target.y;
                            System.out.println(attackPoint.x);
                            System.out.println(attackPoint.y);
                            Tile desiredTile = map.getTileAt(attackPoint);


                            if(desiredTile.hasEntity()){
                                Entity target = desiredTile.getEntity();
                                target.takeDamage(-damage);
                                System.out.println("Has Entity yo");
                            }
                        }
                    },
                    2000//half a second
            );*/

            PointNode current = pointQueue.poll();//returns the top
            Point attackPoint = new Point();

            attackPoint.x=current.target.x;
            attackPoint.y=current.target.y;
            //System.out.println(attackPoint.x);
            //System.out.println(attackPoint.y);
            Tile desiredTile = map.getTileAt(attackPoint);


            if(desiredTile.hasEntity()){
                Entity target = desiredTile.getEntity();
                target.takeDamage(-damage);

            }
            //TODO:Implement a timer that does run into a out of memory exception
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
