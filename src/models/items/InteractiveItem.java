package models.items;

import models.Entity;

/**
 * Created by aseber on 2/21/16.
 */
public class InteractiveItem extends Item {

    public boolean onTouch(Entity entity) {

        return false;

    }

    public String getType() {

        return "interactive";

    }

}
