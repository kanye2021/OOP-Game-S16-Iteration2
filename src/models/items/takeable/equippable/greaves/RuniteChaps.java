package models.items.takeable.equippable.greaves;

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
public class RuniteChaps extends EquippableItem{
    public RuniteChaps(){
        ID = Item.ItemDictionary.RUNITE_CHAPS;
        name = "Runite Chaps";
        description = "Chaps made of runite";
        component = Equipment.Component.GREAVES;
        sprite = new Sprite("./src/res/items/takeable/armor/legs/sneak-runite-greaves.png");
        monetaryValue = RUNITECOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, RUNITEDEF)
        );
        requiredLv = RUNITELV;
        itemWeight = RUNITEWEIGHT;
        range = RANGE;
    }
}
