package models.items;

import models.Equipment;

/**
 * Created by aseber on 2/22/16.
 */
public class IronHelmet extends EquippableItem {

    public IronHelmet() {

        id = 1;
        name = "Wood Helmet";
        description = "A helmet made of wood";
        component = Equipment.Component.HELMET;
        //sprite = new Sprite("path");
        /*onEquipStatModifications = new StatModificationList(
                new StatModification()
        )*/

    }

}
