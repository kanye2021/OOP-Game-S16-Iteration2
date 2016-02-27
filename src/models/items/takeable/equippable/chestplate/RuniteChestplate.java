package models.items.takeable.equippable.chestplate;

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
public class RuniteChestplate extends EquippableItem{

    public RuniteChestplate(){
        ID = Item.ItemDictionary.RUNITE_CHESTPLATE;
        name = "Runite Chestplate";
        description = "A chestplate made of runite";
        component = Equipment.Component.CHESTPLATE;
        sprite = new Sprite("pathname");
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, 100)
        );
    }
}
