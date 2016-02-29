package models.map;

import models.entities.Entity;

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

    // This function will attempt to move an entity in the direction it wants to move. Will return the location where
    // the entity should be (may be its existing location if the move was not successful).
    public Point moveEntity(Entity entity, Direction direction){
        // Determine the location of the tile the entity wants to move to.
        Point currentLocation = entity.getLocation();
        Point desiredLocation = direction.neighbor(currentLocation);

        // Get the tile at that location. Exit if it is not on the map.
        Tile desiredTile = tiles.get(desiredLocation);
        if(desiredTile == null)
            return currentLocation;

        // Tell the tile that the entity wants to move to it. If it is successful, the tile will return true and carry
        // out any actions that result from the move. If not, it will return false.
        boolean entityMoved = desiredTile.insertEntity(entity);

        if(entityMoved){
            Tile oldLocation = tiles.get(currentLocation);
            oldLocation.removeEntity();
            return desiredLocation;
        }else{
            return currentLocation;
        }
    }

    public Tile getTileAt(Point p){
        return tiles.get(p);
    }
}
