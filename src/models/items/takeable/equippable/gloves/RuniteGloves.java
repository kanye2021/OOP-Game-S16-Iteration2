package models.items.takeable.equippable.gloves;

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
public class RuniteGloves extends EquippableItem{
    public RuniteGloves(){
        ID = Item.ItemDictionary.RUNITE_GLOVES;
        name = "Runite Gloves";
        description = "Gloves made of runite";
        component = Equipment.Component.GLOVES;
        sprite = new Sprite("./src/res/items/takeable/armor/gloves/runiteGloves.png");
        monetaryValue = RUNITECOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, RUNITEDEF)
        );
        equipConditions = new ConditionList(
                new StatCondition(null, RUNITELV, Stats.Type.LEVEL, Condition.Comparison.AT_LEAST, Condition.Variable.PASS0)
        );
        itemWeight = RUNITEWEIGHT;
        range = RANGE;

    }
}
