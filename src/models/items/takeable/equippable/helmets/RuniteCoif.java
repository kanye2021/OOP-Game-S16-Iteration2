package models.items.takeable.equippable.helmets;

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
public class RuniteCoif extends EquippableItem{
    public RuniteCoif() {

        ID = Item.ItemDictionary.RUNITE_COIF;
        name = "Runite Coif";
        description = "A coif the color of Runite";
        component = Equipment.Component.HELMET;
        sprite = new Sprite("./src/res/items/takeable/armor/head/sneak-template-helm.png");
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
