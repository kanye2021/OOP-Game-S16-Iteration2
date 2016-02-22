package models.items;

import models.Entity;

/**
 * Created by aseber on 2/21/16.
 */
public class OneShotItem extends Item {

    public boolean onTouch(Entity entity) {

        return false;

    }

    public String getType() {

        return "one-shot";

    }

}
