package models.items.obstacle;

import models.items.Item;
import views.sprites.Sprite;

/**
 * Created by aseber on 2/25/16.
 */
public class Grave extends ObstacleItem {

    public Grave() {

        ID = Item.ItemDictionary.GRAVE;
        sprite = new Sprite("grave.png");

    }

}
