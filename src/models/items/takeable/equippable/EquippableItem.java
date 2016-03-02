package models.items.takeable.equippable;

import models.conditions.ConditionList;
import models.entities.Entity;
import models.Equipment;
import models.items.takeable.TakeableItem;
import models.stats.StatModificationList;

/**
 * Created by aseber on 2/21/16.
 */
public class EquippableItem extends TakeableItem {

    protected Equipment.Component component;
    protected StatModificationList onEquipModifications = new StatModificationList();
    protected ConditionList equipConditions = new ConditionList();

    protected int requiredLv;
    public boolean onTouch(Entity entity) {

        return false;

    }

    // Equivalent to equipping.
    public final void onUse(Entity entity) {
        if (equipConditions.checkCondition()) {
            entity.applyStatMod(onEquipModifications);
            // add item to inventory

        }

    }
    // function for unequipping.
    public final void onUnequip(Entity entity) {
        entity.removeStatMod(onEquipModifications);
    }

    public final Equipment.Component getComponent() { return component; }
    public final StatModificationList getOnEquipModifications() { return onEquipModifications; }
    public final ConditionList getEquipConditions(){ return equipConditions; }

    @Override
    public String getType(){
        return "equippable";
    }

    @Override
    public boolean isEquipable() {
        return true;
    }
}
