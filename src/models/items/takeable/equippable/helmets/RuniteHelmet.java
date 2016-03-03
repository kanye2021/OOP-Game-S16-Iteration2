package models.items.takeable.equippable.helmets;

import models.Equipment;
import models.conditions.Condition;
import models.conditions.ConditionList;
import models.conditions.StatCondition;
import models.items.takeable.equippable.EquippableItem;
import models.stats.StatModification;
import models.stats.StatModificationList;
import models.stats.Stats;
import views.sprites.Sprite;

/**
 * Created by aseber on 2/22/16.
 */
public class RuniteHelmet extends EquippableItem {

    public RuniteHelmet() {

        ID = ItemDictionary.RUNITE_HELMET;
        name = "Runite Helmet";
        description = "A helmet made of runite";
        component = Equipment.Component.HELMET;
        sprite = new Sprite("./src/res/items/takeable/armor/head/smasher-runite-helm.png");
        monetaryValue = RUNITECOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, 80)
        );
        equipConditions = new ConditionList(
                new StatCondition(null, 40, Stats.Type.LEVEL, Condition.Comparison.AT_LEAST, Condition.Variable.PASS0)
        );

    }

}
