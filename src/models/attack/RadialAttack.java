package models.attack;

import models.Attackion;
import models.attack.status_effects.Sleep;
import models.entities.Entity;
import models.map.Map;
import models.map.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
/**
 * Created by ben on 3/8/16.
 */
public class RadialAttack extends Attackion{
    //Map map;
    public RadialAttack(Entity entity, Projectile projectile){
        this.entity = entity;
        //If you cannot attack
        if(!canAttack(entity)){
            return;
        }
        this.origin = entity.getLocation();
        this.damage = projectile.damage;
        this.range = projectile.range;
        this.orientation = entity.getOrientation();
        this.map = entity.getMap();
        this.statusEffect = projectile.statusEffect;
        findBreadthFirstTile();
    }

    public void findBreadthFirstTile(){//BFS algorithm here
    Queue<PointNode> pointQueue = new LinkedList<>();
     //pointQueue.add(new PointNode(origin));
        Tile originTile = map.getTileAt(origin);
        PointNode root = new PointNode(originTile,origin,0);

        pointQueue.add(root);//Push into the queue the original node
        while(!pointQueue.isEmpty()){
            PointNode current = pointQueue.poll();
            Point attackPoint = new Point();

            attackPoint.x=current.target.x;
            attackPoint.y=current.target.y;
            //System.out.println(attackPoint.x);
            //System.out.println(attackPoint.y);
            Tile desiredTile = map.getTileAt(attackPoint);


            //Only takes damage if you are not the caster. or the mount of the caster

            if(desiredTile.hasEntity()&&originTile!=desiredTile&&entity!=desiredTile.getEntity()){
                Entity target = desiredTile.getEntity();
                target.takeDamage(-damage);
                if(statusEffect!= StatusEffects.StatusEffect.NONE){
                    System.out.println("Status Effects Happens!!!");

                    target.setStatusEffect(statusEffect);
                    new Sleep(target);
                }

            }
            for(PointNode pointNode: getAdjacentTiles(current)){
                pointNode.range = current.range + 1;
                pointQueue.offer(pointNode);

                /*if(pointNode.range>range){
                    return;
                }*/
            }
            if(current.range>range){
                return;
            }

        }
    }
    private ArrayList<PointNode> getAdjacentTiles(PointNode pointNode){
        ArrayList<PointNode> adjacentTiles = new ArrayList<>();

        // Get the tile adjacent to the north.
        Point northLogicalPoint = new Point(pointNode.target); // Get the tiles logical point
        northLogicalPoint.translate(0, -1);


        Tile northTile = map.getTileAt(northLogicalPoint);
        if(northTile != null){
            adjacentTiles.add(new PointNode(northTile, northLogicalPoint,pointNode.range+1));
        }

        // Get the tile to the south of the current position.
        Point southLogicalPoint = new Point(pointNode.target);
        southLogicalPoint.translate(0, 1);


        Tile southTile = map.getTileAt(southLogicalPoint);
        if(southTile != null){
            adjacentTiles.add(new PointNode(southTile, southLogicalPoint,pointNode.range+1));
        }


        // Get the tile to the north west of the current position.
        Point northWestLogicalPoint = new Point(pointNode.target);
        northWestLogicalPoint.translate(-1, 0);

        Tile northWestTile = map.getTileAt(northWestLogicalPoint);
        if(northWestTile != null){
            adjacentTiles.add(new PointNode(northWestTile, northWestLogicalPoint,pointNode.range+1));
        }


        // Get the tile to the south east of the current position.
        Point southEastLogicalPoint = new Point(pointNode.target);
        southEastLogicalPoint.translate(1, 0);

        Tile southEastTile = map.getTileAt(southEastLogicalPoint);
        if(southEastTile != null){
            adjacentTiles.add(new PointNode(southEastTile, southEastLogicalPoint,pointNode.range+1));
        }


        // Get the tile to the north east of the current position.
        Point northEastLogicaPoint = new Point(pointNode.target);
        northEastLogicaPoint.translate(1, -1);

        Tile northEastTile = map.getTileAt(northEastLogicaPoint);
        if(northEastTile != null){
            adjacentTiles.add(new PointNode(northEastTile, northEastLogicaPoint,pointNode.range+1));
        }


        // Get the tile to the south west of the current position.
        Point southWestLogicalPoint = new Point(pointNode.target);
        southWestLogicalPoint.translate(-1, 1);

        Tile southWestTile = map.getTileAt(southWestLogicalPoint);
        if(southWestTile != null){
            adjacentTiles.add(new PointNode(southWestTile,southWestLogicalPoint,pointNode.range+1));
        }

        return adjacentTiles;
    }
    class PointNode{
        public Point target;
        public Tile tile;
        public int range;
        public PointNode(Tile tile, Point point, int range){
            target = new Point();
            this.target.x=point.x;
            this.target.y = point.y;
            this.tile = tile;
            this.range = range;
            //this.range = -1;
        }
    }
    @Override
    public void calculateDamage() {

    }


}
