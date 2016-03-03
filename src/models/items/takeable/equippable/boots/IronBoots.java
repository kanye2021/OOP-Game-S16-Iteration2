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
public class IronBoots extends EquippableItem{
    public IronBoots(){
        ID = Item.ItemDictionary.IRON_BOOTS;
        name = "Iron Boots";
        description = "Boots made of iron";
        component = Equipment.Component.BOOTS;
        sprite = new Sprite("./src/res/items/takeable/armor/boots/templateBoots.png");
        monetaryValue = IRONCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, 10)
        );
        requiredLv = 5;
    }
}
