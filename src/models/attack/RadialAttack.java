package models.attack;

import models.Attack;
import models.entities.Entity;
import models.map.Map;
import models.map.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
/**
 * Created by ben on 3/8/16.
 */
public class RadialAttack extends Attack{
    Map map;
    Point slope;


    public RadialAttack(Entity entity, Projectile projectile){
        this.origin = entity.getLocation();
        this.damage = projectile.damage;
        this.range = projectile.range;
        this.map = entity.getMap();
        slope = new Point();
        findBreadthFirstTile();
    }

    public void findBreadthFirstTile() {

        //Queue for the BFS
        Queue<PointNode> pointQueue = new LinkedList<>();

        //first tile to be pushed in
        PointNode root = new PointNode(map.getTileAt(origin), origin,orientation);
        pointQueue.add(root);



        //current node is the first tile in the
        PointNode currentPointNode = pointQueue.poll();

        //immediate neighbors
        ArrayList<PointNode> neighbors = getAdjacentTiles(currentPointNode);

        //have immediate neighbors
        for (int i = 0; i < neighbors.size(); i++) {

            //grabs current tile
            PointNode current = neighbors.get(i);
            Entity tempEntity = current.tile.getEntity();
            Point point = current.target;

            //with current tile, extend as far as possible given range
            for (int j = 0; j < range; j++) {
                if(tempEntity != null){
                    System.out.println(tempEntity);
                    break;
                }else{
                    if(current.direction == Map.Direction.NORTH){
                        System.out.println("North");
                        current.target.translate(0,-1);
                    }
                    else if(current.direction == Map.Direction.NORTH_EAST){
                        System.out.println("North East");
                        current.target.translate(1,-1);
                    }
                    else if(current.direction == Map.Direction.NORTH_WEST){
                        System.out.println("North West");
                        current.target.translate(-1,0);
                    }
                    else if(current.direction == Map.Direction.SOUTH){
                        System.out.println("South");
                        current.target.translate(0,1);
                    }
                    else if(current.direction == Map.Direction.SOUTH_EAST){
                        System.out.println("South East");
                        current.target.translate(1,0);
                    }
                    else if(current.direction == Map.Direction.SOUTH_WEST){
                        System.out.println("South West");
                        current.target.translate(-1,1);
                    }
                }
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
            adjacentTiles.add(new PointNode(northTile, northLogicalPoint, Map.Direction.NORTH));


        }

        // Get the tile to the south of the current position.
        Point southLogicalPoint = new Point(pointNode.target);
        southLogicalPoint.translate(0, 1);


        Tile southTile = map.getTileAt(southLogicalPoint);
        if(southTile != null){
            adjacentTiles.add(new PointNode(southTile, southLogicalPoint, Map.Direction.SOUTH));


        }


        // Get the tile to the north west of the current position.
        Point northWestLogicalPoint = new Point(pointNode.target);
        northWestLogicalPoint.translate(-1, 0);

        Tile northWestTile = map.getTileAt(northWestLogicalPoint);
        if(northWestTile != null){
            adjacentTiles.add(new PointNode(northWestTile, northWestLogicalPoint, Map.Direction.NORTH_WEST));


        }


        // Get the tile to the south east of the current position.
        Point southEastLogicalPoint = new Point(pointNode.target);
        southEastLogicalPoint.translate(1, 0);

        Tile southEastTile = map.getTileAt(southEastLogicalPoint);
        if(southEastTile != null){
            adjacentTiles.add(new PointNode(southEastTile, southEastLogicalPoint, Map.Direction.SOUTH_EAST));


        }


        // Get the tile to the north east of the current position.
        Point northEastLogicaPoint = new Point(pointNode.target);
        northEastLogicaPoint.translate(1, -1);

        Tile northEastTile = map.getTileAt(northEastLogicaPoint);
        if(northEastTile != null){
            adjacentTiles.add(new PointNode(northEastTile, northEastLogicaPoint, Map.Direction.NORTH_EAST));


        }


        // Get the tile to the south west of the current position.
        Point southWestLogicalPoint = new Point(pointNode.target);
        southWestLogicalPoint.translate(-1, 1);

        Tile southWestTile = map.getTileAt(southWestLogicalPoint);
        if(southWestTile != null){
            adjacentTiles.add(new PointNode(southWestTile,southWestLogicalPoint, Map.Direction.SOUTH_WEST));


        }

        return adjacentTiles;
    }
    class PointNode{
        public Point target;
        public Tile tile;
        public Map.Direction direction;
        public PointNode(Tile tile, Point point, Map.Direction direction){
            target = new Point();
            this.target.x=point.x;
            this.target.y = point.y;
            this.direction = direction;
            this.tile = tile;
        }
    }
    @Override
    public void calculateDamage() {

    }
}