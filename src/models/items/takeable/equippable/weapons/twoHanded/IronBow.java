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
public class IronBow extends EquippableItem{
    public IronBow(){
        ID = Item.ItemDictionary.IRON_BOW;
        name = "Iron bow";
        description = "A bow made of iron";
        component = Equipment.Component.TWO_HANDED_WEAPON;
        sprite = new Sprite("./src/res/items/takeable/weapons/twoHanded/sneak-template-bow.png");
        monetaryValue = IRONCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.WEAPON_MODIFIER, IRONATK)
        );
        equipConditions = new ConditionList(
                new StatCondition(null, IRONLV, Stats.Type.LEVEL, Condition.Comparison.AT_LEAST)
        );
        itemWeight = IRONWEIGHT;
        range = SNEAKRANGE;

    }
}
