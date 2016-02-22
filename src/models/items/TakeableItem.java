package models.items;

import models.Entity;

/**
 * Created by aseber on 2/21/16.
 */
public abstract class TakeableItem extends Item {

    public String name;
    public String description;
    //public StatModifications onPickUpModifications;

    public boolean onTouch(Entity entity) {

        return false;

    }

    public abstract void onUse(); // Method for what should be done once the item is in the inventory and used

    public String getType() {

        return "take-able";

    }

}
