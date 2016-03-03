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
public class WoodHelmet extends EquippableItem {

    public WoodHelmet() {

        ID = ItemDictionary.WOOD_HELMET;
        name = "Wood Helmet";
        description = "A helmet made of wood";
        component = Equipment.Component.HELMET;
        sprite = new Sprite("./src/res/items/takeable/armor/head/WoodHelm.png");
        monetaryValue = WOODCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, 10)
        );
        requiredLv = 1;
    }

}
