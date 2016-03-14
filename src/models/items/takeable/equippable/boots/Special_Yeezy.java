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
 * Created by ben on 3/5/16.
 */
public class Special_Yeezy extends EquippableItem {
    public Special_Yeezy() {
        ID = Item.ItemDictionary.YEEZY;
        name = "Yeezys";
        description = "Yeezus they are so stylish and help you run";
        component = Equipment.Component.BOOTS;
        sprite = new Sprite("./src/res/items/takeable/armor/boots/yeezy.png");
        monetaryValue = CHUCKNORRISCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.MOVEMENT, CHUCKNORRISDEF)//basically defense into spd
        );
        equipConditions = new ConditionList(
                new StatCondition(null, CHUCKNORRISLV, Stats.Type.LEVEL, Condition.Comparison.AT_LEAST)
        );
        itemWeight = CHUCKNORRISWEIGHT;
        range = RANGE;
    }

}
