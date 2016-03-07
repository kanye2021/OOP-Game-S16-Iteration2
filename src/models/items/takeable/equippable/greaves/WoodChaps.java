package models.items.takeable.equippable.greaves;

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
public class WoodChaps extends EquippableItem{
    public WoodChaps(){
        ID = Item.ItemDictionary.WOOD_CHAPS;
        name = "Wood Chaps";
        description = "Chaps made of Wood";
        component = Equipment.Component.GREAVES;
        sprite = new Sprite("./src/res/items/takeable/armor/legs/sneak-template-greaves.png");
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
