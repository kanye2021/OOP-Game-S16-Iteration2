package models.items.takeable.equippable.helmets;

import models.Equipment;
import models.items.takeable.equippable.EquippableItem;
import models.stats.StatModificationList;
import views.sprites.Sprite;

/**
 * Created by aseber on 2/22/16.
 */
public class GoldHelmet extends EquippableItem {

    public GoldHelmet() {

        ID = ItemDictionary.GOLD_HELMET;
        name = "Gold Helmet";
        description = "A helmet made of gold";
        component = Equipment.Component.HELMET;
        sprite = new Sprite("path");
        onEquipModifications = new StatModificationList(
//                new StatModification()
        );

    }

}
