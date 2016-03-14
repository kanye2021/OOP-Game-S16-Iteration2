package models.items.interactive;

import models.conditions.ConditionList;
import models.entities.Entity;
import models.items.Item;

/**
 * Created by aseber on 2/21/16.
 */
public abstract class InteractiveItem extends Item {

    private ConditionList onTouchConditions;

    public InteractiveItem() {

        onTouchConditions = initConditions();

    }

    public boolean onTouch(Entity entity) {

        return onTouchConditions.checkCondition(entity);

    }

    protected abstract ConditionList initConditions();

    @Override
    public String getType() {
        return "interactive";
    }
}
