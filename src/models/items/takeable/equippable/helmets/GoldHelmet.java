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
public class GoldHelmet extends EquippableItem {

    public GoldHelmet() {

        ID = ItemDictionary.GOLD_HELMET;
        name = "Gold Helmet";
        description = "A helmet made of gold";
        component = Equipment.Component.HELMET;
        sprite = new Sprite("../res/items/takeable/armor/head/gold_knight.png");
        monetaryValue = 500;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, 15)
        );
        requiredLv = 20;
    }

}
