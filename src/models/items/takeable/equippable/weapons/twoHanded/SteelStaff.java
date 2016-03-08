package models.items.takeable.equippable.weapons.twoHanded;

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
 * Created by ben on 3/2/16.
 */
public class SteelStaff extends EquippableItem{
    public SteelStaff(){
        ID = Item.ItemDictionary.STEEL_STAFF;
        name = "Steel staff";
        description = "A staff made of steel";
        component = Equipment.Component.TWO_HANDED_WEAPON;
        sprite = new Sprite("./src/res/items/takeable/weapons/twoHanded/StaffOfAir.png");
        monetaryValue = STEELCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.WEAPON_MODIFIER, STEELATK)
        );
        equipConditions = new ConditionList(
                new StatCondition(null, STEELLV, Stats.Type.LEVEL, Condition.Comparison.AT_LEAST)
        );
        itemWeight = STEELWEIGHT;
        range = SUMMONERRANGE;

    }
}
