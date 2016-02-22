package models.items;

import models.Equipment;

/**
 * Created by aseber on 2/22/16.
 */
public class MithrilHelmet extends EquippableItem {

    public MithrilHelmet() {

        ID = ItemDictionary.MITHRIL_HELMET;
        name = "Mithril Helmet";
        description = "A helmet made of mithril";
        component = Equipment.Component.HELMET;
        //sprite = new Sprite("path");
        /*onEquipStatModifications = new StatModificationList(
                new StatModification()
        )*/

    }

}
