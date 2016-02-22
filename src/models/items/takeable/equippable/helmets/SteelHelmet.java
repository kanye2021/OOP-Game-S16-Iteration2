package models.items.takeable.equippable.helmets;

import models.Equipment;
import models.items.takeable.equippable.EquippableItem;

/**
 * Created by aseber on 2/22/16.
 */
public class SteelHelmet extends EquippableItem {

    public SteelHelmet() {

        ID = ItemDictionary.STEEL_HELMET;
        name = "Steel Helmet";
        description = "A helmet made of steel";
        component = Equipment.Component.HELMET;
        //sprite = new Sprite("path");
        /*onEquipStatModifications = new StatModificationList(
                new StatModification()
        )*/

    }

}
