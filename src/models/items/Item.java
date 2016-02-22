package models.items;

import models.Entity;

/**
 * Created by aseber on 2/21/16.
 */
public abstract class Item {

    int id;
    //Sprite sprite;

    public abstract boolean onTouch(Entity entity); // Method to describe what happens when an item is touched

    public final int getID() {

        return id;

    }

    /*public final Sprite getSprite() {

        return sprite;

    }*/

    public final boolean equals(Object o) {

        if (o instanceof Item) {

            Item otherItem = (Item) o;

            return this.getID() == otherItem.getID();

        }

        return false;

    }

}
