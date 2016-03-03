package models.items.takeable.equippable.helmets;

import models.Equipment;
import models.conditions.Condition;
import models.conditions.ConditionList;
import models.conditions.StatCondition;
import models.entities.Entity;
import models.entities.SneakAvatar;
import models.items.takeable.equippable.EquippableItem;
import models.map.Map;
import models.stats.StatModification;
import models.stats.StatModificationList;
import models.stats.Stats;
import views.sprites.Sprite;

import java.awt.*;

/**
 * Created by aseber on 2/22/16.
 */
public class IronHelmet extends EquippableItem {

    public IronHelmet() {

        ID = ItemDictionary.IRON_HELMET;
        name = "Iron Helmet";
        description = "A helmet made of iron";
        component = Equipment.Component.HELMET;
        sprite = new Sprite("./src/res/items/takeable/armor/head/IronHelm.png");
        monetaryValue = IRONCOST;
        onEquipModifications = new StatModificationList(
            new StatModification(Stats.Type.ARMOR_MODIFIER, 25)
        );
        equipConditions = new ConditionList(
            new StatCondition(null, 5, Stats.Type.LEVEL, Condition.Comparison.AT_LEAST, Condition.Variable.PASS0)
        );
    }

}
