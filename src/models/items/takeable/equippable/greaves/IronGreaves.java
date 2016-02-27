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
public class IronGreaves extends EquippableItem{
    public IronGreaves(){
        ID = Item.ItemDictionary.IRON_GREAVES;
        name = "Iron Greaves";
        description = "Greaves made of iron";
        component = Equipment.Component.GREAVES;
        sprite = new Sprite("pathname");
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, 10)
        );
    }
}
