package models.items.takeable.consumable;

import models.entities.Entity;
import models.items.takeable.TakeableItem;
import models.stats.StatModificationList;

/**
 * Created by aseber on 2/21/16.
 */
public class ConsumableItem extends TakeableItem {

    public StatModificationList onConsumeModifications;

    public boolean onTouch(Entity entity) {

        return false;

    }

    public void onUse(Entity entity) {

        entity.applyStatMod(onConsumeModifications);

    }

}
