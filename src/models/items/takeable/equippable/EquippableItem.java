package models.items.takeable.equippable;

import models.entities.Entity;
import models.Equipment;
import models.items.takeable.TakeableItem;
import models.stats.StatModificationList;

/**
 * Created by aseber on 2/21/16.
 */
public class EquippableItem extends TakeableItem {

    protected Equipment.Component component;
    public StatModificationList onEquipModifications;

    public boolean onTouch(Entity entity) {

        return false;

    }

    public final void onUse(Entity entity) { // Equivalent to equipping.

        entity.applyStatMod(onEquipModifications);

    }

    public final void onUnequip(Entity entity) { // function for unequipping.

        entity.removeStatMod(onEquipModifications);

    }

    public final Equipment.Component getComponent() {

        return component;

    }

}