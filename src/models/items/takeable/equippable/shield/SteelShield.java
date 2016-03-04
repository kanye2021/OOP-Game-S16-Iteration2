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
public class SteelShield extends EquippableItem{
    public SteelShield(){
        ID = Item.ItemDictionary.STEEL_SHIELD;
        name = "Steel Shield";
        description = "Shield made of steel";
        component = Equipment.Component.SHIELD;
        sprite = new Sprite("./src/res/items/takeable/armor/secondary/smasher-template-shield.png");
        monetaryValue = STEELCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, STEELDEF)
        );
        requiredLv = STEELLV;
        itemWeight = STEELWEIGHT;
        range = RANGE;
    }
}
