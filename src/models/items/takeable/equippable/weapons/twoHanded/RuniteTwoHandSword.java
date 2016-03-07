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
public class RuniteTwoHandSword extends EquippableItem{
    public RuniteTwoHandSword(){
        ID = Item.ItemDictionary.RUNITE_TWO_HAND_SWORD;
        name = "Runite 2h Sword";
        description = "2h sword made of runite";
        component = Equipment.Component.TWO_HANDED_WEAPON;
        sprite = new Sprite("./src/res/items/takeable/weapons/twoHanded/smasher-template-2h.png");
        monetaryValue = RUNITECOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.WEAPON_MODIFIER, RUNITEATK*2)
        );
        equipConditions = new ConditionList(
                new StatCondition(null, RUNITELV, Stats.Type.LEVEL, Condition.Comparison.AT_LEAST)
        );

        itemWeight = RUNITEWEIGHT;
        range = SMASHERRANGE;
    }
}
