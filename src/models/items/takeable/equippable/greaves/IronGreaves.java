package models.items.takeable.equippable.greaves;

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
public class IronGreaves extends EquippableItem{
    public IronGreaves(){
        ID = Item.ItemDictionary.IRON_GREAVES;
        name = "Iron Greaves";
        description = "Greaves made of iron";
        component = Equipment.Component.GREAVES;
        sprite = new Sprite("./src/res/items/takeable/armor/legs/smasher-template-greaves.png");
        monetaryValue = IRONCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, IRONDEF),
                // just testing multiple stat mods
                new StatModification(Stats.Type.HARDINESS, 20),
                new StatModification(Stats.Type.MOVEMENT, -10)

        );
        equipConditions = new ConditionList(
                new StatCondition(null, IRONLV, Stats.Type.LEVEL, Condition.Comparison.AT_LEAST, Condition.Variable.PASS0)
        );
        itemWeight = IRONWEIGHT;
        range = RANGE;

    }
}
