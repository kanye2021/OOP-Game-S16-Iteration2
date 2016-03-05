package models.items.takeable.equippable.chestplate;

import models.Equipment;
import models.items.Item;
import models.items.takeable.equippable.EquippableItem;
import models.stats.StatModification;
import models.stats.StatModificationList;
import models.stats.Stats;
import views.sprites.Sprite;

/**
 * Created by ben on 3/5/16.
 */
public class RuniteRobe extends EquippableItem{
    public RuniteRobe(){
        ID = Item.ItemDictionary.RUNITE_ROBE;
        name = "Runite Robe";
        description = "A robe the color of Runite";
        component = Equipment.Component.CHESTPLATE;
        sprite = new Sprite("./src/res/items/takeable/armor/chest/summoner-runite-chestplate.png");
        monetaryValue = RUNITECOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, RUNITEDEF)
        );
        requiredLv = RUNITELV;
        itemWeight = RUNITEWEIGHT;
        range = RANGE;
    }
}
