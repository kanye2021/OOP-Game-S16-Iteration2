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
public class GoldHat extends EquippableItem{
    public GoldHat() {

        ID = Item.ItemDictionary.GOLD_HAT;
        name = "Gold Hat";
        description = "A pointy hat that is the color of gold";
        component = Equipment.Component.HELMET;
        sprite = new Sprite("./src/res/items/takeable/armor/head/summoner-template-helm.png");
        monetaryValue = GOLDCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, GOLDDEF)
        );
        equipConditions = new ConditionList(
                new StatCondition(null, GOLDLV, Stats.Type.LEVEL, Condition.Comparison.AT_LEAST)
        );
        itemWeight = GOLDWEIGHT;
        range = RANGE;
    }
}