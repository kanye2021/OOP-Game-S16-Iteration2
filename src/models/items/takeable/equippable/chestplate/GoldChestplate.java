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
public class GoldChestplate extends EquippableItem{

    public GoldChestplate(){
        ID = Item.ItemDictionary.GOLD_CHESTPLATE;
        name = "Gold Chestplate";
        description = "A chestplate made of gold";
        component = Equipment.Component.CHESTPLATE;
        sprite = new Sprite("pathname");
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, 20)
        );
        requiredLv = 20;
    }
}
