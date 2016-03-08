package models.map;

import models.entities.Entity;
import models.items.Item;
import models.skills.SneakSkills.TileDetection;
import models.entities.npc.NPC;
import java.awt.*;
import java.util.HashMap;

/**
 * Created by aseber on 2/22/16.
 */
public class Map {

    // This enum contains all the directions on the map
    public enum Direction {
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
        },
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
        };

        public abstract Point neighbor(Point p);
    }
    public HashMap<Point, Tile> tiles;

    public Map(HashMap<Point, Tile> tiles){
        this.tiles = tiles;
    }

    // This function is really only called in initialization. Any other insertions/ deletions should be done via movement.
    public void insertEntity(Entity entity){
        tiles.get(entity.getLocation()).insertEntity(entity);
    }

    // To insert an item on the map. Used when loading and dropping items
    public void insertItemAtPoint(Item item, Point point) { tiles.get(point).addItem(item); }

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
        if (desiredTile.hasNPC()){
            return new TileDetection((NPC)desiredTile.getEntity(), currentLocation, false, false);
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
            oldLocation.removeEntity();
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
        if (desiredTile.hasNPC()){
            return new TileDetection((NPC)desiredTile.getEntity(), currentLocation, false, false);
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
            oldLocation.removeEntity();
            return new TileDetection(null, desiredLocation, true, false);
        }else{
            // return currentLocation;
            return new TileDetection(null,currentLocation, false, false);
        }
    }

    public Tile getTileAt(Point p){
        return tiles.get(p);
    }

    // Useful wrapper functions to avoid violating demeters law
    public Entity getEntityAt(Point p){
        return tiles.get(p).getEntity();
    }

    public Item getItemAt(Point p){
        return tiles.get(p).getItem();
    }

    public Terrain getTerrainAt(Point p){
        return tiles.get(p).getTerrain();
    }
}
