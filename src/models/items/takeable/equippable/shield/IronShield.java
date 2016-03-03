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
public class IronShield extends EquippableItem{
    public IronShield(){
        ID = Item.ItemDictionary.IRON_SHIELD;
        name = "Iron Shield";
        description = "Shield made of iron";
        component = Equipment.Component.SHIELD;
        sprite = new Sprite("./src/res/items/takeable/armor/secondary/smasher-template-shield.png");
        monetaryValue = IRONCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, 20)
        );
        requiredLv = 5;
    }
}
