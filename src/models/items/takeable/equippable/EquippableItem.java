package models.items.takeable.equippable;

import models.Entity;
import models.Equipment;
import models.items.takeable.TakeableItem;

/**
 * Created by aseber on 2/21/16.
 */
public class EquippableItem extends TakeableItem {

    protected Equipment.Component component;
    //public StatModifications onEquipModifications;

    public boolean onTouch(Entity entity) {

        return false;

    }

    public void onUse() {



    }

    public final Equipment.Component getComponent() {

        return component;

    }

}
