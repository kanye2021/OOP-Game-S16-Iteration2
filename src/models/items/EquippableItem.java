package models.items;

import models.Entity;
import models.Equipment;

/**
 * Created by aseber on 2/21/16.
 */
public class EquippableItem extends Item {

    protected Equipment.Component component;

    public boolean onTouch(Entity entity) {

        return false;

    }

    public void onUse() {



    }

}
