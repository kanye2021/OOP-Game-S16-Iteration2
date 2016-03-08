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
public class MithrilHide extends EquippableItem {
    public MithrilHide(){
        ID = Item.ItemDictionary.MITHRIL_HIDE;
        name = "Mithril Hide";
        description = "Hide the color of Mithril";
        component = Equipment.Component.CHESTPLATE;
        sprite = new Sprite("./src/res/items/takeable/armor/chest/sneak-template-chestplate.png");
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
