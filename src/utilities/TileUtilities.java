package utilities;

import models.map.Map;
import models.map.Tile;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by aseber on 3/13/16.
 */
public class TileUtilities {

    // This function intentionally ignores the center point
    public static HashMap<Tile, Point> getTilesRecursively(Map map, Point center, int radius) {

        HashMap<Tile, Point> tiles = new HashMap<>();

        for (int i = 1; i < radius; ++i) {

            tiles.putAll(getTileRing(map, center, i));

        }

        return tiles;

    }

    // This function intentionally ignores the center tile
    public static HashMap<Tile, Point> getTileRing(Map map, Point center, int radius) {

        HashMap<Tile, Point> tiles = new HashMap<>();
        Point point = new Point(center);
        Tile tileToAdd;

        if (radius < 1) {

            return tiles;

        }

        for (int i = 0; i < radius; ++i) {

            point = Map.Direction.NORTH.neighbor(point);

        }

        for (Map.Direction direction : Map.Direction.values()) {


            for (int i = 0; i < radius; ++i) {

                tileToAdd = map.getTileAt(point);

                if (tileToAdd != null) {

                    tiles.put(tileToAdd, point);

                }

                point = direction.neighbor(point);

            }

        }

        return tiles;

    }

    // This function takes in a set of tiles and applies a mask to them where
    // inputTiles refers to the tiles that the function takes in.
    // center refers to the center point, can use the same one you used to get the tiles originally
    // direction is [0, 2pi] and refers to the direction angle.
    // span is [0, pi] and refers to the angle to add to each side of direction to add
    private static HashSet<Tile> maskTilesByAngle(HashSet<Tile> inputTiles, Point center, double direciton, double span) {

        MathUtilities.putInRange(0.0, direciton, 2 * Math.PI);
        MathUtilities.putInRange(0.0, span, Math.PI);

        return inputTiles;

    }

    private static double getAngle(Point startPoint, Point endPoint) {

        double x = endPoint.x - startPoint.x;
        double y = endPoint.y - startPoint.y;

        return Math.asin(y / x);

    }

}
