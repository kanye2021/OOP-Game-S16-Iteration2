package models.conditions;

import models.entities.Entity;

/**
 * Created by aseber
 * on 2/6/16.
 */

public class MapEntityCondition { //extends MapCondition {
/*

    Entity entity;
    MapCondition.Location location;
    int x;
    int y;
    Map map;

    public MapEntityCondition(Entity entity, MapCondition.Location location, int x, int y, Map map) {

        this.entity = entity;
        this.location = location;
        this.x = x;
        this.y = y;
        this.map = map;

    }

    public boolean checkCondition() {

        Entity entityOnMap = map.getEntityAtLocation(x, y);

        if (entityOnMap != null) {

            int i1 = entityOnMap.getID();
            int i2 = entity.getEntity().getID();

            return location.checkLocation(i1, i2);

        }

        // A bit hacky, but it allows us to return true when the item isn't in that location, or false when it is.
        // Dependent on the equality checker!
        return location.checkLocation(1, 0);

    }
*/
}
