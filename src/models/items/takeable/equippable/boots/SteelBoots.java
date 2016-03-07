package models.items.takeable.equippable.boots;

import models.Equipment;
import models.conditions.Condition;
import models.conditions.ConditionList;
import models.conditions.StatCondition;
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
        sprite = new Sprite("./src/res/items/takeable/armor/boots/templateBoots.png");
        monetaryValue = STEELCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, STEELDEF)
        );
        equipConditions = new ConditionList(
                new StatCondition(null, STEELLV, Stats.Type.LEVEL, Condition.Comparison.AT_LEAST)
        );
        itemWeight = STEELWEIGHT;
        range = RANGE;

    }
}
