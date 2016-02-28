package models.conditions;

import models.entities.Entity;
import models.items.takeable.TakeableItem;
import models.items.takeable.equippable.EquippableItem;

/**
 * Created by aseber
 * on 2/6/16.
 */
public class EquippedCondition extends Condition {

    //Properties of InventoryCondition
    private Entity entity;
    private EquippableItem item;

    public EquippedCondition(Entity entity, EquippableItem item) {

        this.entity = entity;
        this.item = item;

    }

    public boolean checkCondition() {

        TakeableItem equippedItem = null;//item.getComponent().getCurrentEquippedItem(entity.getEntity());

        if (equippedItem != null) {

            return equippedItem.getID() == item.getID();

        }

        return false;

    }

}
