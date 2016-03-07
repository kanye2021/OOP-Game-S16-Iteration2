package models.items.takeable.equippable.shield;

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
public class MithrilShield extends EquippableItem{
    public MithrilShield(){
        ID = Item.ItemDictionary.MITHRIL_SHIELD;
        name = "Mithril Shield";
        description = "Shield made of mithril";
        component = Equipment.Component.SHIELD;
        monetaryValue = 200;
        sprite = new Sprite("./src/res/items/takeable/armor/secondary/smasher-template-shield.png");
        monetaryValue=MITHRILCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, MITHRILDEF)
        );
        equipConditions = new ConditionList(
                new StatCondition(null, MITHRILLV, Stats.Type.LEVEL, Condition.Comparison.AT_LEAST, Condition.Variable.PASS0)
        );
        itemWeight = MITHRILWEIGHT;
        range = RANGE;

    }
}
