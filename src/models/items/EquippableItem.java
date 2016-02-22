package models.items;

import models.Entity;
import models.Equipment;

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

    public String getType() {

        return "equippable";

    }

}
