package models.items.takeable.equippable.helmets;

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
public class RuniteHat extends EquippableItem{
    public RuniteHat() {

        ID = Item.ItemDictionary.RUNITE_HAT;
        name = "Runite Hat";
        description = "A pointy hat that is the color of Runite";
        component = Equipment.Component.HELMET;
        sprite = new Sprite("./src/res/items/takeable/armor/head/summoner-template-helm.png");
        monetaryValue = RUNITECOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, RUNITEDEF)
        );
        requiredLv = RUNITELV;
        itemWeight = RUNITEWEIGHT;
        range = RANGE;
    }
}
