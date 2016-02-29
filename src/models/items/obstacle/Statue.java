package models.items.obstacle;

import models.items.Item;
import views.sprites.Sprite;

/**
 * Created by aseber on 2/25/16.
 */
public class Statue extends ObstacleItem {

    public Statue() {

        ID = Item.ItemDictionary.STATUE;
        sprite = new Sprite("statue.png");

    }

}
