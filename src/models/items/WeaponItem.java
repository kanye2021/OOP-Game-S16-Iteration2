package models.items;

import models.Entity;

/**
 * Created by aseber on 2/21/16.
 */
public abstract class WeaponItem extends EquippableItem  {

    //component = Equipment.Component.WEAPON;

    public boolean onTouch(Entity entity) {

        return true;

    }

    public void onUse() {



    }

    public abstract void onActivate();

}
