package models.items.takeable.equippable.helmets;

import models.Equipment;
import models.items.takeable.equippable.EquippableItem;
import models.stats.StatModificationList;
import views.sprites.Sprite;

/**
 * Created by aseber on 2/22/16.
 */
public class MithrilHelmet extends EquippableItem {

    public MithrilHelmet() {

        ID = ItemDictionary.MITHRIL_HELMET;
        name = "Mithril Helmet";
        description = "A helmet made of mithril";
        component = Equipment.Component.HELMET;
        sprite = new Sprite("path");
        onEquipModifications = new StatModificationList(
//                new StatModification()
        );

    }

}
