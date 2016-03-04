package models.conditions;

import models.entities.Entity;
import models.map.Map;

import java.awt.*;

/**
 * Created by aseber
 * on 2/6/16.
 */

public class MapEntityCondition extends MapCondition {

    public MapEntityCondition(Entity entity, MapCondition.Location location, Point point, Map map, Variable... runtimeArguments) {

        setParameter(0, entity);
        setParameter(1, location);
        setParameter(2, point);
        setParameter(3, map);

    }

    public boolean checkCondition(Object... args) {

        getRuntimeParameters(args);

        Entity entity = (Entity) getParameter(0);
        MapCondition.Location location = (MapCondition.Location) getParameter(1);
        Point point = (Point) getParameter(2);
        Map map = (Map) getParameter(3);

        Entity entityOnMap = map.getTileAt(point).getEntity();

        if (entityOnMap != null) {

            //int i1 = entityOnMap.getID();
            //int i2 = entity.getID();

            //return location.checkLocation(i1, i2);

        }

        // A bit hacky, but it allows us to return true when the item isn't in that location, or false when it is.
        // Dependent on the equality checker!
        return location.checkLocation(1, 0);

    }

}
