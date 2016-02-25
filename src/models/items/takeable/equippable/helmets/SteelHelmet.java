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
public class SteelHelmet extends EquippableItem {

    public SteelHelmet() {

        ID = ItemDictionary.STEEL_HELMET;
        name = "Steel Helmet";
        description = "A helmet made of steel";
        component = Equipment.Component.HELMET;
        sprite = new Sprite("../res/items/takeable/armor/head/knight_helm.png");
        monetaryValue = 40;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, 35)
        );

    }

}
