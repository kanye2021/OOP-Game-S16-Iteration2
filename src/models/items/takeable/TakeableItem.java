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

    @Override
    public boolean onTouch(Entity entity) {
        boolean added = entity.addItemToInventory(this);
        entity.applyStatMod(onPickUpModifications);
        return added;
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

    //The idea being that some villagers might sell something for higher than it actually is
    public void setMonetaryValue(int money) {
        monetaryValue = money;
    }

    public boolean isEquipable() {
        return false;
    }
}
