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

    //TODO:Go back to the items classes and put the constants in oh boi
    protected final int WOODCOST = 10;
    protected final int IRONCOST = 50;
    protected final int STEELCOST = 100;
    protected final int MITHRILCOST = 200;
    protected final int GOLDCOST = 400;
    protected final int RUNITECOST = 800;
    protected final int WOODATK = 10;
    protected final int IRONATK = 20;
    protected final int STEELATK = 30;
    protected final int MITHRILATK = 40;
    protected final int GOLDATK = 60;
    protected final int RUNITEATK = 80;

    // Equivalent to equipping.
    public final void onUse(Entity entity) {
        if (equipConditions.checkCondition(entity)) {
            System.out.println("Condition passed!");
            entity.applyStatMod(onEquipModifications);
            // remove item from inventory
            entity.getInventory().removeItem(this);
            entity.getEquipment().equipItem(this);

        }

    }
    // function for unequipping.
    public final void onUnequip(Entity entity) {
        entity.removeStatMod(onEquipModifications);
    }

    public final Equipment.Component getComponent() { return component; }

    @Override
    public String getType() {
        return "equippable";
    }

    @Override
    public boolean isEquipable() {
        return true;
    }
}
