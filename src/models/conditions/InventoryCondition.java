package models.conditions;

import models.entities.Entity;
import models.items.Item;
import models.items.takeable.TakeableItem;

/**
 * Created by denzel on 2/6/16.
 */
public class InventoryCondition extends Condition {
    // Param 0 = Entity
    // Param 1 = Condition.Comparison
    // Param 2 = int
    // Param 3 = Item.ItemDictionary

    public InventoryCondition(Entity entity, Condition.Comparison itemComparison, int count, Item.ItemDictionary item) {

        setParameter(0, entity);
        setParameter(1, itemComparison);
        setParameter(2, count);
        setParameter(3, item);

    }

    protected boolean checkConditionInternal() {

        Entity entity = (Entity) getParameter(0);
        Condition.Comparison itemComparison = (Condition.Comparison) getParameter(1);
        int requiredCount = (int) getParameter(2);
        Item.ItemDictionary item = (Item.ItemDictionary) getParameter(3);

        int inventoryCount = entity.getInventory().getAmountOfItem(item);

        return (itemComparison.isValid(inventoryCount, requiredCount));
    }

}
