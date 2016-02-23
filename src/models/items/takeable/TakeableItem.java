package models.items.takeable;

import models.entities.Entity;
import models.items.Item;
import models.stats.StatModificationList;

/**
 * Created by aseber on 2/21/16.
 */
public abstract class TakeableItem extends Item {

    public String name;
    public String description;
    public StatModificationList onPickUpModifications;

    public boolean onTouch(Entity entity) {

        entity.applyStatMod(onPickUpModifications);
        return false;

    }

    public abstract void onUse(Entity entity); // Method for what should be done once the item is in the inventory and used

}
