package models.items.takeable.equippable.shield;

import models.Equipment;
import models.items.Item;
import models.items.takeable.equippable.EquippableItem;
import models.stats.StatModification;
import models.stats.StatModificationList;
import models.stats.Stats;
import views.sprites.Sprite;

/**
 * Created by ben on 2/27/16.
 */
public class RuniteShield extends EquippableItem{
    public RuniteShield(){
        ID = Item.ItemDictionary.RUNITE_SHIELD;
        name = "Runite Shield";
        description = "Shield made of runite";
        component = Equipment.Component.SHIELD;
        sprite = new Sprite("./src/res/items/takeable/armor/secondary/smasher-runite-shield.png");
        monetaryValue = RUNITECOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, RUNITEDEF)
        );
        requiredLv = RUNITELV;
        itemWeight = RUNITEWEIGHT;
        range = RANGE;
    }
}
