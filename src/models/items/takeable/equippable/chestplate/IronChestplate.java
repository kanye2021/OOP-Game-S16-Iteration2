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
public class IronChestplate extends EquippableItem{

    public IronChestplate(){
        ID = Item.ItemDictionary.IRON_CHESTPLATE;
        name = "Iron Chestplate";
        description = "A chestplate made of iron";
        component = Equipment.Component.CHESTPLATE;
        sprite = new Sprite("./src/res/items/takeable/armor/chest/smasher-template-chestplate.png");
        monetaryValue = IRONCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, 30)
        );
        requiredLv = 5;
    }
}
