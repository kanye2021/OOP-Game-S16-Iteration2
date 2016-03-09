package models.attack;

import models.Attack;
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
public class RadialAttack extends Attack{
    Map map;
    public RadialAttack(Entity entity, Projectile projectile){
        this.origin = entity.getLocation();
        this.damage = projectile.damage;
        this.range = projectile.range;
        this.orientation = entity.getOrientation();
        this.map = entity.getMap();
    }

    public void findBreadthFirstTile(){
    Queue<PointNode> pointQueue = new LinkedList<>();
     //pointQueue.add(new PointNode(origin));

        PointNode root = new PointNode(map.getTileAt(origin),origin);
        pointQueue.add(root);
        while(!pointQueue.isEmpty()){
            PointNode currentPointNode = pointQueue.poll();
            for(PointNode pointNode: getAdjacentTiles(currentPointNode)){
                pointNode.range = currentPointNode.range + 1;
                pointQueue.offer(pointNode);
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
            adjacentTiles.add(new PointNode(northTile, northLogicalPoint));
        }

        // Get the tile to the south of the current position.
        Point southLogicalPoint = new Point(pointNode.target);
        southLogicalPoint.translate(0, 1);


        Tile southTile = map.getTileAt(southLogicalPoint);
        if(southTile != null){
            adjacentTiles.add(new PointNode(southTile, southLogicalPoint));
        }


        // Get the tile to the north west of the current position.
        Point northWestLogicalPoint = new Point(pointNode.target);
        northWestLogicalPoint.translate(-1, 0);

        Tile northWestTile = map.getTileAt(northWestLogicalPoint);
        if(northWestTile != null){
            adjacentTiles.add(new PointNode(northWestTile, northWestLogicalPoint));
        }


        // Get the tile to the south east of the current position.
        Point southEastLogicalPoint = new Point(pointNode.target);
        southEastLogicalPoint.translate(1, 0);

        Tile southEastTile = map.getTileAt(southEastLogicalPoint);
        if(southEastTile != null){
            adjacentTiles.add(new PointNode(southEastTile, southEastLogicalPoint));
        }


        // Get the tile to the north east of the current position.
        Point northEastLogicaPoint = new Point(pointNode.target);
        northEastLogicaPoint.translate(1, -1);

        Tile northEastTile = map.getTileAt(northEastLogicaPoint);
        if(northEastTile != null){
            adjacentTiles.add(new PointNode(northEastTile, northEastLogicaPoint));
        }


        // Get the tile to the south west of the current position.
        Point southWestLogicalPoint = new Point(pointNode.target);
        southWestLogicalPoint.translate(-1, 1);

        Tile southWestTile = map.getTileAt(southWestLogicalPoint);
        if(southWestTile != null){
            adjacentTiles.add(new PointNode(southWestTile,southWestLogicalPoint));
        }

        return adjacentTiles;
    }
    class PointNode{
        public Point target;
        public Tile tile;
        public int range;
        public PointNode(Tile tile, Point point){
            target = new Point();
            this.target.x=point.x;
            this.target.y = point.y;
            this.tile = tile;
            this.range = -1;
        }
    }
    @Override
    public void calculateDamage() {

    }
}
