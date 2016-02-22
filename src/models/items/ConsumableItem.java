package models.items;

import models.Entity;

/**
 * Created by aseber on 2/21/16.
 */
public class ConsumableItem extends TakeableItem {

    public boolean onTouch(Entity entity) {

        return false;

    }

    public void onUse() {



    }

    public String getType() {

        return "consumable";

    }

}
