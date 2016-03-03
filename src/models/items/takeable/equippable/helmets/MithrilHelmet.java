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
public class MithrilHelmet extends EquippableItem {

    public MithrilHelmet() {

        ID = ItemDictionary.MITHRIL_HELMET;
        name = "Mithril Helmet";
        description = "A helmet made of mithril";
        component = Equipment.Component.HELMET;
        sprite = new Sprite("./src/res/items/takeable/armor/head/smasher-template-helm.png");
        monetaryValue = MITHRILCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, 40)
        );
        equipConditions = new ConditionList(
                new StatCondition(null, 20, Stats.Type.LEVEL, Condition.Comparison.AT_LEAST, Condition.Variable.PASS0)
        );

    }

}
