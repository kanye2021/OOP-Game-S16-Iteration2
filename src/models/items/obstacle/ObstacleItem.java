package models.items.obstacle;

import models.entities.Entity;
import models.items.Item;

/**
 * Created by aseber on 2/21/16.
 */
public class ObstacleItem extends Item {

    public final boolean onTouch(Entity entity) {

        return false;

    }

}
