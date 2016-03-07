package models.items.takeable.equippable.chestplate;

import models.Equipment;
import models.conditions.*;
import models.items.Item;
import models.items.takeable.equippable.EquippableItem;
import models.stats.*;
import views.sprites.Sprite;

/**
 * Created by ben on 2/27/16.
 */
public class WoodChestplate extends EquippableItem{

    public WoodChestplate(){
        ID = Item.ItemDictionary.WOOD_CHESTPLATE;
        name = "Wood Chestplate";
        description = "A chestplate made of wood";
        component = Equipment.Component.CHESTPLATE;
        sprite = new Sprite("./src/res/items/takeable/armor/chest/smasher-template-chestplate.png");
        monetaryValue = WOODCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, WOODDEF)
        );

        equipConditions = new ConditionList(
                new StatCondition(null, WOODLV, Stats.Type.LEVEL, Condition.Comparison.AT_LEAST, Condition.Variable.PASS0)
        );
        itemWeight = WOODWEIGHT;
        range = RANGE;

    }
}
