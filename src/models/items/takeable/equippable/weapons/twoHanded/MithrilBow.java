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
public class MithrilBow extends EquippableItem{
    public MithrilBow(){
        ID = Item.ItemDictionary.MITHRIL_BOW;
        name = "Mithril bow";
        description = "A bow made of mithril";
        component = Equipment.Component.RANGED_WEAPON;
        sprite = new Sprite("./src/res/items/takeable/weapons/twoHanded/sneak-template-bow.png");
        monetaryValue = MITHRILCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.WEAPON_MODIFIER, MITHRILATK)
        );

        equipConditions = new ConditionList(
                new StatCondition(null, MITHRILLV, Stats.Type.LEVEL, Condition.Comparison.AT_LEAST)
        );
        itemWeight = MITHRILWEIGHT;
        range = SNEAKRANGE;

    }
}
