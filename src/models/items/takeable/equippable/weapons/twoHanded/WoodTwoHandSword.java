package models.items.takeable.equippable.weapons.twoHanded;

import models.Equipment;
import models.conditions.*;
import models.items.Item;
import models.items.takeable.equippable.EquippableItem;
import models.stats.StatModification;
import models.stats.StatModificationList;
import models.stats.Stats;
import views.sprites.Sprite;

/**
 * Created by ben on 3/2/16.
 */
public class WoodTwoHandSword extends EquippableItem{
    public WoodTwoHandSword(){
        ID = Item.ItemDictionary.WOOD_TWO_HAND_SWORD;
        name = "Wood 2h Sword";
        description = "2h sword made of wood";
        component = Equipment.Component.TWO_HANDED_WEAPON;
        sprite = new Sprite("./src/res/items/takeable/weapons/twoHanded/smasher.template-2h.png");
        monetaryValue = WOODCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.WEAPON_MODIFIER, WOODATK*2)
        );
        equipConditions = new ConditionList(
            new StatCondition(null, WOODLV, Stats.Type.LEVEL, Condition.Comparison.AT_LEAST, Condition.Variable.PASS0)
        );
        itemWeight = WOODWEIGHT;
        range = SMASHERRANGE;
    }
}
