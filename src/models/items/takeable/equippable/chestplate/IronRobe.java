package models.items.takeable.equippable.chestplate;

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
 * Created by ben on 3/5/16.
 */
public class IronRobe extends EquippableItem{
    public IronRobe(){
        ID = Item.ItemDictionary.IRON_ROBE;
        name = "Iron Robe";
        description = "A robe the color of iron";
        component = Equipment.Component.CHESTPLATE;
        sprite = new Sprite("./src/res/items/takeable/armor/chest/summoner-template-chestplate.png");
        monetaryValue = IRONCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, IRONDEF)
        );
        equipConditions = new ConditionList(
                new StatCondition(null, IRONLV, Stats.Type.LEVEL, Condition.Comparison.AT_LEAST, Condition.Variable.PASS0)
        );
        itemWeight = IRONWEIGHT;
        range = RANGE;
    }
}
