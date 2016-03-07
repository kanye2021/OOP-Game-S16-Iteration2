package models.conditions;

import models.entities.Entity;
import models.items.takeable.equippable.EquippableItem;

/**
 * Created by aseber
 * on 2/6/16.
 */
public class EquippedCondition extends Condition {
    // Param 0 = Entity
    // Param 1 = EquippableItem

    public EquippedCondition(Entity entity, EquippableItem item) {
        setParameter(0, entity);
        setParameter(1, item);
    }

    protected boolean checkConditionInternal() {

        Entity entity = (Entity) getParameter(0);
        EquippableItem item = (EquippableItem) getParameter(1);

        EquippableItem equippedItems = entity.getEquipment().getEquipmentLocation(item.getComponent())[0];

        if (equippedItems != null) {

            return equippedItems.getID() == item.getID();

        }

        return false;

    }

}
