package models.items.takeable.equippable.greaves;

import models.Equipment;
import models.items.Item;
import models.items.takeable.equippable.EquippableItem;
import models.stats.StatModification;
import models.stats.StatModificationList;
import models.stats.Stats;
import views.sprites.Sprite;

/**
 * Created by ben on 2/27/16.
 */
public class RuniteGreaves extends EquippableItem{
    public RuniteGreaves(){
        ID = Item.ItemDictionary.RUNITE_GREAVES;
        name = "Runite Greaves";
        description = "Greaves made of runite";
        component = Equipment.Component.GREAVES;
        sprite = new Sprite("pathname");
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, 40)
        );
        requiredLv = 40;
    }
}
