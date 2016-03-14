package models.conditions;

import models.items.Item;
import models.map.Map;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by aseber
 * on 2/6/16.
 */

public class MapItemCondition extends MapCondition {
    // Param 0 = Item.ItemDictionary
    // Param 1 = MapCondition.Location
    // Param 2 = Point
    // Param 3 = Map


    // The map conditional statement. It only supports one point on the map at this time but it can be expanded to any number
    // in the future given functons in Map that return rectangles, circles etc.
    public MapItemCondition(Item.ItemDictionary item, MapCondition.Location location, Point point, Map map) {

        setParameter(0, item);
        setParameter(1, location);
        setParameter(2, point);
        setParameter(3, map);

    }

    protected boolean checkConditionInternal() {

        Item.ItemDictionary item = (Item.ItemDictionary) getParameter(0);
        MapCondition.Location location = (MapCondition.Location) getParameter(1);
        Point point = (Point) getParameter(2);
        Map map = (Map) getParameter(3);


        ArrayList<Item> itemsOnMap = map.getTileAt(point).getItems();

        boolean returnValue = location.checkLocation(item, null);

        for (Item itemOnMap : itemsOnMap) {
            if (location.checkLocation(item, itemOnMap)) {
                returnValue = returnValue || location.checkLocation(item, itemOnMap);
            }
        }

        return returnValue;
    }
}
