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
public class IronTwoHandSword extends EquippableItem{
    public IronTwoHandSword(){
        ID = Item.ItemDictionary.IRON_TWO_HAND_SWORD;
        name = "Iron 2h Sword";
        description = "2h sword made of gold";
        component = Equipment.Component.TWO_HANDED_WEAPON;
        sprite = new Sprite("./src/res/items/takeable/weapons/twoHanded/smasher-template-2h.png");
        monetaryValue = IRONCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.WEAPON_MODIFIER, IRONATK*2)
        );
        equipConditions = new ConditionList(
                new StatCondition(null, IRONLV, Stats.Type.LEVEL, Condition.Comparison.AT_LEAST, Condition.Variable.PASS0)
        );
        itemWeight = IRONWEIGHT;
        range = SMASHERRANGE;

    }

}