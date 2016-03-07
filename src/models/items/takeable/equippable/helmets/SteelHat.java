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
public class SteelHat extends EquippableItem{
    public SteelHat() {

        ID = Item.ItemDictionary.STEEL_HAT;
        name = "Steel Hat";
        description = "A pointy hat that is the color of Steel";
        component = Equipment.Component.HELMET;
        sprite = new Sprite("./src/res/items/takeable/armor/head/summoner-template-helm.png");
        monetaryValue = STEELCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, STEELDEF)
        );
        equipConditions = new ConditionList(
                new StatCondition(null, STEELLV, Stats.Type.LEVEL, Condition.Comparison.AT_LEAST, Condition.Variable.PASS0)
        );
        itemWeight = STEELWEIGHT;
        range = RANGE;
    }
}
