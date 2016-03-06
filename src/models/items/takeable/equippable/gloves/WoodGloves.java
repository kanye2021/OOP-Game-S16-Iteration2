package models.items.takeable.equippable.gloves;

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
 * Created by ben on 2/27/16.
 */
public class WoodGloves extends EquippableItem{
    public WoodGloves(){
        ID = Item.ItemDictionary.WOOD_GLOVES;
        name = "Wood Gloves";
        description = "Gloves made of wood";
        component = Equipment.Component.GLOVES;
        sprite = new Sprite("./src/res/items/takeable/armor/gloves/templateGloves.png");
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