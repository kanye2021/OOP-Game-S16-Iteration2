package models.items.takeable.equippable.helmets;

import models.Equipment;
import models.items.takeable.equippable.EquippableItem;
import models.stats.StatModification;
import models.stats.StatModificationList;
import models.stats.Stats;
import views.sprites.Sprite;

/**
 * Created by aseber on 2/22/16.
 */
public class RuniteHelmet extends EquippableItem {

    public RuniteHelmet() {

        ID = ItemDictionary.RUNITE_HELMET;
        name = "Runite Helmet";
        description = "A helmet made of runite";
        component = Equipment.Component.HELMET;
        sprite = new Sprite("path");
        monetaryValue = 300;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.StatType.ARMOR_MODIFIER, 80)
        );

    }

}