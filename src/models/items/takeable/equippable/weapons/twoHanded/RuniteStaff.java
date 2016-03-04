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
public class RuniteStaff extends EquippableItem{
    public RuniteStaff(){
        ID = Item.ItemDictionary.RUNITE_STAFF;
        name = "Runite staff";
        description = "A staff made of runite";
        component = Equipment.Component.TWO_HANDED_WEAPON;
        sprite = new Sprite("./src/res/items/takeable/weapons/twoHanded/StaffOfAir.png");
        monetaryValue = RUNITECOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.WEAPON_MODIFIER, RUNITEATK)
        );
        requiredLv = RUNITELV;
        itemWeight = RUNITEWEIGHT;
        range = SUMMONERRANGE;
    }
}