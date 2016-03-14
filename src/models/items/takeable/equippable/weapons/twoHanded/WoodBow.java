package models.items.takeable.equippable.weapons.twoHanded;

import models.Equipment;
import models.conditions.*;
import models.items.Item;
import models.items.takeable.equippable.EquippableItem;
import models.stats.*;
import views.sprites.Sprite;

/**
 * Created by ben on 3/2/16.
 */
public class WoodBow extends EquippableItem{
    public WoodBow(){
        ID = Item.ItemDictionary.WOOD_BOW;
        name = "Wood bow";
        description = "A bow made of wood";
        component = Equipment.Component.RANGED_WEAPON;
        sprite = new Sprite("pathname");
        monetaryValue = WOODCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.WEAPON_MODIFIER, WOODATK)
        );

        equipConditions = new ConditionList(
            new StatCondition(null, WOODLV, Stats.Type.LEVEL, Condition.Comparison.AT_LEAST)
        );
        itemWeight = WOODWEIGHT;
        range = SNEAKRANGE;

    }
}
