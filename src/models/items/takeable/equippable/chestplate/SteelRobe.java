package models.items.takeable.equippable.chestplate;

import models.Equipment;
import models.items.Item;
import models.items.takeable.equippable.EquippableItem;
import models.stats.StatModification;
import models.stats.StatModificationList;
import models.stats.Stats;
import views.sprites.Sprite;

/**
 * Created by ben on 3/5/16.
 */
public class SteelRobe extends EquippableItem{
    public SteelRobe(){
        ID = Item.ItemDictionary.STEEL_ROBE;
        name = "Steel Robe";
        description = "A robe the color of steel";
        component = Equipment.Component.CHESTPLATE;
        sprite = new Sprite("./src/res/items/takeable/armor/chest/summoner-template-chestplate.png");
        monetaryValue = STEELCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, STEELDEF)
        );
        requiredLv = STEELLV;
        itemWeight = STEELWEIGHT;
        range = RANGE;
    }
}
