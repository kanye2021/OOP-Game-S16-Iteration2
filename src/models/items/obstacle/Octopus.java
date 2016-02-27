package models.items.obstacle;

import models.items.Item;
import views.sprites.Sprite;

/**
 * Created by aseber on 2/25/16.
 */
public class Octopus extends ObstacleItem {

    public Octopus() {

        ID = Item.ItemDictionary.OCTOPUS;
        sprite = new Sprite("octopus.png");

    }

}
