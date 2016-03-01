package models.items.takeable.equippable.shield;

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
public class GoldShield extends EquippableItem{
    public GoldShield(){
        ID = Item.ItemDictionary.GOLD_SHIELD;
        name = "Gold Shield";
        description = "Shield made of gold";
        component = Equipment.Component.SHIELD;
        sprite = new Sprite("pathname");
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, 40)
        );
        requiredLv = 20;
    }
}
