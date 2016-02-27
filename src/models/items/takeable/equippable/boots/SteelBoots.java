package models.items.takeable.equippable.boots;

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
public class SteelBoots extends EquippableItem{
    public SteelBoots(){
        ID = Item.ItemDictionary.STEEL_BOOTS;
        name = "Steel Boots";
        description = "Boots made of steel";
        component = Equipment.Component.BOOTS;
        sprite = new Sprite("pathname");
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, 15)
        );
    }
}
