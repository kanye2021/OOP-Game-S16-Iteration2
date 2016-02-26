package models.items.interactive;

import models.entities.Entity;
import models.items.Item;

/**
 * Created by aseber on 2/21/16.
 */
public class InteractiveItem extends Item {

    public boolean onTouch(Entity entity) {

        return false;

    }


    @Override
    public String getType(){
        return "interactive";
    }
}
