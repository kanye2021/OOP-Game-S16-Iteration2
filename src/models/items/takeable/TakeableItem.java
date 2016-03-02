package models.items.takeable;

import models.entities.Entity;
import models.items.Item;
import models.stats.StatModificationList;

/**
 * Created by aseber on 2/21/16.
 */
public abstract class TakeableItem extends Item {

    protected String name;
    protected String description;
    protected StatModificationList onPickUpModifications;
    protected int monetaryValue;

    public boolean onTouch(Entity entity) {

        entity.applyStatMod(onPickUpModifications);
        return false;

    }

    public abstract void onUse(Entity entity); // Method for what should be done once the item is in the inventory and used


    @Override
    public String getType(){
        return "take-able";
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public StatModificationList getOnPickUpModifications() {
        return onPickUpModifications;
    }

    public int getMonetaryValue() {
        return monetaryValue;
    }

    public boolean isEquipable() {
        return false;
    }
}
