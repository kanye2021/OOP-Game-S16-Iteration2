package models.items.takeable.equippable.helmets;

import models.Equipment;
import models.items.takeable.equippable.EquippableItem;
import models.stats.StatModificationList;
import views.sprites.Sprite;

/**
 * Created by aseber on 2/22/16.
 */
public class IronHelmet extends EquippableItem {

    public IronHelmet() {

        ID = ItemDictionary.IRON_HELMET;
        name = "Wood Helmet";
        description = "A helmet made of wood";
        component = Equipment.Component.HELMET;
        sprite = new Sprite("path");
        onEquipModifications = new StatModificationList(
//                new StatModification()
        );

    }

}
