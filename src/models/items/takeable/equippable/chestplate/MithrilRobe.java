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
public class MithrilRobe extends EquippableItem{
    public MithrilRobe(){
        ID = Item.ItemDictionary.MITHRIL_ROBE;
        name = "Mithril Robe";
        description = "A robe the color of mithril";
        component = Equipment.Component.CHESTPLATE;
        sprite = new Sprite("./src/res/items/takeable/armor/chest/summoner-template-chestplate.png");
        monetaryValue = MITHRILCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, MITHRILDEF)
        );
        equipConditions = new ConditionList(
                new StatCondition(null, MITHRILLV, Stats.Type.LEVEL, Condition.Comparison.AT_LEAST)
        );
        itemWeight = MITHRILWEIGHT;
        range = RANGE;
    }
}
