package models.items;

import models.Entity;

/**
 * Created by aseber on 2/21/16.
 */
public abstract class Item {

    int id;
    String type;

    public abstract boolean onTouch(Entity entity);

    public abstract void onUse();

}
