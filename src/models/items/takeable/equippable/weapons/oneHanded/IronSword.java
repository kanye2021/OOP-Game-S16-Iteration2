package models.items.takeable.equippable.weapons.oneHanded;

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
public class IronSword extends EquippableItem{
    public IronSword(){
        ID = Item.ItemDictionary.IRON_SWORD;
        name = "Iron Sword";
        description = "Sword made of iron";
        component = Equipment.Component.ONE_HANDED_WEAPON;
        sprite = new Sprite("./src/res/items/takeable/weapons/oneHanded/smasher-template-sword.png");
        monetaryValue = IRONCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.WEAPON_MODIFIER, IRONATK)
        );

        equipConditions = new ConditionList(
            new StatCondition(null, IRONLV, Stats.Type.LEVEL, Condition.Comparison.AT_LEAST)
        );
        itemWeight = IRONWEIGHT;
        range = SMASHERRANGE;

    }
}
