package models.items.takeable.equippable.gloves;

import models.Equipment;
import models.conditions.*;
import models.items.Item;
import models.items.takeable.equippable.EquippableItem;
import models.stats.*;
import views.sprites.Sprite;

/**
 * Created by ben on 2/27/16.
 */
public class GoldGloves extends EquippableItem{
    public GoldGloves(){
        ID = Item.ItemDictionary.GOLD_GLOVES;
        name = "Gold Gloves";
        description = "Gloves made of gold";
        component = Equipment.Component.GLOVES;
        sprite = new Sprite("./src/res/items/takeable/armor/gloves/templateGloves.png");
        monetaryValue = GOLDCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, GOLDDEF)
        );
        equipConditions = new ConditionList(
                new StatCondition(null, GOLDLV, Stats.Type.LEVEL, Condition.Comparison.AT_LEAST, Condition.Variable.PASS0)
        );
        itemWeight = GOLDWEIGHT;
        range = RANGE;

    }

}
