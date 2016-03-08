package models.items.takeable.equippable.weapons;

import models.entities.Entity;
import models.items.takeable.equippable.EquippableItem;

/**
 * Created by aseber on 2/21/16.
 */
public abstract class WeaponItem extends EquippableItem {

    //component = Equipment.Component.WEAPON;

    public void onUse() {



    }

    public abstract void onActivate(); // Method to describe what happens when a weapons is fired

}
