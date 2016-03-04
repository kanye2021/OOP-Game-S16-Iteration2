package models.items.takeable.equippable.boots;

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
public class RuniteBoots extends EquippableItem{
    public RuniteBoots(){
        ID = Item.ItemDictionary.RUNITE_BOOTS;
        name = "Runite Boots";
        description = "Boots made of runite";
        component = Equipment.Component.BOOTS;
        sprite = new Sprite("./src/res/items/takeable/armor/boots/runiteBoots.png");
        monetaryValue = RUNITECOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, RUNITEDEF)
        );
        requiredLv = RUNITELV;
        itemWeight = RUNITEWEIGHT;
        range = RANGE;
    }
}
