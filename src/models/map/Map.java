package models.map;

import models.attack.Projectile;
import models.entities.Entity;
import models.items.Item;
import utilities.TileDetection;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by aseber on 2/22/16.
 */
public class Map {

    // This enum contains all the directions on the map
    public enum Direction {
        SOUTH_EAST {
            public Point neighbor(Point currentLocation){
                Point n = new Point(currentLocation);
                n.translate(1, 0);
                return n;
            }
        },
        SOUTH {
            public Point neighbor(Point currentLocation){
                Point n = new Point(currentLocation);
                n.translate(0, 1);
                return n;
            }
        },
        SOUTH_WEST {
            public Point neighbor(Point currentLocation){
                Point n = new Point(currentLocation);
                n.translate(-1, 1);
                return n;
            }
        },
        NORTH_WEST {
            public Point neighbor(Point currentLocation){
                Point n = new Point(currentLocation);
                n.translate(-1, 0);
                return n;
            }
        },
        NORTH {
            public Point neighbor(Point currentLocation){
                Point n = new Point(currentLocation);
                n.translate(0, -1);
                return n;
            }
        },
        NORTH_EAST {
            public Point neighbor(Point currentLocation){
                Point n = new Point(currentLocation);
                n.translate(1, -1);
                return n;
            }
        };

        public abstract Point neighbor(Point p);
    }
    public HashMap<Point, Tile> tiles;
    private boolean changeSinceLastRender;

    public Map(HashMap<Point, Tile> tiles){

        this.tiles = tiles;
        this.changeSinceLastRender = true;
    }

    // This function is really only called in initialization. Any other insertions/ deletions should be done via movement.
    public void insertEntity(Entity entity){
        tiles.get(entity.getLocation()).insertEntity(entity);
        updateTile(entity.getLocation());
    }

//    public void insertProjectile(Projectile projectile){
//        tiles.get(projectile.getLocation()).insertProjectile(projectile);
//        updateTile(projectile.getLocation());
//    }

    // To insert an item on the map. Used when loading and dropping items
    public void insertItemAtPoint(Item item, Point point) {
        if(tiles.containsKey(point)){
            tiles.get(point).addItem(item);
            updateTile(point);
        }

    }

    public void insertProjectileAtPoint(Projectile projectile, Point point){
        if(tiles.containsKey(point)){
            tiles.get(point).addProjectile(projectile);
            updateTile(point);
        }
    }

    public void removeProjectileAtPoint(Point point){
        if(tiles.containsKey(point)){
            tiles.get(point).removeProjectile();
            updateTile(point);
        }
    }


    // Checks if a valid tile is at a certain Point
    public boolean isTileValid(Point point) { return tiles.containsKey(point); }

    // This function will attempt to move an entity in the direction it wants to move. Will return the location where
    // the entity should be (may be its existing location if the move was not successful).
    public TileDetection moveEntity(Entity entity, Point desiredLocation){
        // Determine the location of the tile the entity wants to move to.
        Point currentLocation = entity.getLocation();
//        Point desiredLocation = direction.neighbor(currentLocation);

        // Get the tile at that location. Exit if it is not on the map.
        Tile desiredTile = tiles.get(desiredLocation);
        if(desiredTile == null) {
            //return currentLocation;
            return new TileDetection(null, currentLocation, false, false);
        }
        if (desiredTile.hasEntity()){
            return new TileDetection(desiredTile.getEntity(), currentLocation, false, false);
        }
        // Tell the tile that the entity wants to move to it. If it is successful, the tile will return true and carry
        // out any actions that result from the move. If not, it will return false.
        TileDetection resultOfMovement = desiredTile.insertEntity(entity);

        if (resultOfMovement.isTeleported()) {
            // return the new teleported loc.
            return new TileDetection(null, entity.getLocation(), false, true);
        }
        else if(resultOfMovement.isMoved()){
            Tile oldLocation = tiles.get(currentLocation);
            removeEntityAt(currentLocation);
            return new TileDetection(null, desiredLocation, true, false);
        }else{
            // return currentLocation;
            return new TileDetection(null,currentLocation, false, false);
        }
    }

    // the entity should be (may be its existing location if the move was not successful).
    public TileDetection moveEntity(Entity entity, Direction direction){
        // Determine the location of the tile the entity wants to move to.
        Point currentLocation = entity.getLocation();
        Point desiredLocation = direction.neighbor(currentLocation);

        // Get the tile at that location. Exit if it is not on the map.
        Tile desiredTile = tiles.get(desiredLocation);
        if(desiredTile == null) {
            //return currentLocation;
            return new TileDetection(null, currentLocation, false, false);
        }
        if (desiredTile.hasEntity()){
            return new TileDetection(desiredTile.getEntity(), currentLocation, false, false);
        }
        // Tell the tile that the entity wants to move to it. If it is successful, the tile will return true and carry
        // out any actions that result from the move. If not, it will return false.
        TileDetection resultOfMovement = desiredTile.insertEntity(entity);

        if (resultOfMovement.isTeleported()) {
            // return the new teleported loc.
            return new TileDetection(null, entity.getLocation(), false, true);
        }
        else if(resultOfMovement.isMoved()){
            Tile oldLocation = tiles.get(currentLocation);
            removeEntityAt(currentLocation);
            return new TileDetection(null, desiredLocation, true, false);
        }else{
            // return currentLocation;;
            return new TileDetection(null,currentLocation, false, false);
        }
    }

//    // the projectile movement
//    public ProjectileDetection moveProjectile(Projectile projectile, Direction direction){
//        //Determine the location of the tile that the projectile wants to move
//        Point currentLocation = projectile.getLocation();
//        Point desiredLocation = direction.neighbor(currentLocation);
//
//        //get the tile at that location. Exit if it is not on the map.
//        Tile desiredTile = tiles.get(desiredLocation);
//        if(desiredLocation == null){
//            return new ProjectileDetection(null,currentLocation,false);
//        }
//
//        //Tell the tile that the projectile wants to move to it. If it is successful, the tile will return true and carry
//        //out any actions that will result from the move. If not, it will return false.
//        ProjectileDetection resultOfMovement = desiredTile.insertProjectile(projectile);
//
//        return resultOfMovement;
//    }

    public Tile getTileAt(Point p){
        return tiles.get(p);
    }

    // Useful wrapper functions to avoid violating demeters law
    public Entity getEntityAt(Point p){
        if (tiles.containsKey(p)) {
            return tiles.get(p).getEntity();
        }
        return null;
    }

    public ArrayList<Item> getItemAt(Point p){
        if(tiles.containsKey(p)){
            return tiles.get(p).getItems();
        }
        return null;
    }

    public void removeItemsAt(Point p){
        if (tiles.containsKey(p)) {
            tiles.get(p).removeItems();
            updateTile(p);
        }
    }

    public void removeAreaEffectAt(Point p){
        if (tiles.containsKey(p)){
            tiles.get(p).removeAreaEffect();
            tiles.get(p).removeDecal();
            updateTile(p);
        }
    }

    public void setDecalVisibilityAtPoint(boolean b, Point p){
        if (tiles.containsKey(p)){
            tiles.get(p).setDecalVisibility(b);
            updateTile(p);
        }
    }

    public Terrain getTerrainAt(Point p){
        if(tiles.containsKey(p)){
            return tiles.get(p).getTerrain();
        }
        return null;
    }

    public void removeEntityAt(Point p) {
        if(tiles.containsKey(p)){
            tiles.get(p).removeEntity();
            updateTile(p);
        }
    }

    public void removeProjectileAt(Point p){
        if(tiles.containsKey(p)){
            tiles.get(p).removeProjectile();
            updateTile(p);
        }
    }

    public void updateTile(Point p){
        if(tiles.containsKey(p)){
            tiles.get(p).refreshImage();
        }
        this.changeSinceLastRender = true;
    }


    public boolean needsToBeRendered(){
        return changeSinceLastRender;
    }

    public void setNeedsToBeRendered(boolean b){
        changeSinceLastRender = b;
    }

    public HashMap<Point, Tile> getTiles(){
        return tiles;
    }
}
