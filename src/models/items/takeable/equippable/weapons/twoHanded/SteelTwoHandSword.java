package models.items.takeable.equippable.weapons.twoHanded;

import models.Equipment;
import models.items.Item;
import models.items.takeable.equippable.EquippableItem;
import models.stats.StatModification;
import models.stats.StatModificationList;
import models.stats.Stats;
import views.sprites.Sprite;

/**
 * Created by ben on 3/2/16.
 */
public class SteelTwoHandSword extends EquippableItem{
    public SteelTwoHandSword(){
        ID = Item.ItemDictionary.STEEL_TWO_HAND_SWORD;
        name = "Steel 2h Sword";
        description = "2h sword made of steel";
        component = Equipment.Component.TWO_HANDED_WEAPON;
        sprite = new Sprite("./src/res/items/takeable/weapons/twoHanded/smasher-template-2h.png");
        monetaryValue = STEELCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.WEAPON_MODIFIER, STEELATK*2)
        );
        requiredLv = STEELLV;
        itemWeight = STEELWEIGHT;
        range = SMASHERRANGE;
    }
}
