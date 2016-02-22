package models.items;

import models.Equipment;

/**
 * Created by aseber on 2/22/16.
 */
public class GoldHelmet extends EquippableItem {

    public GoldHelmet() {

        ID = ItemDictionary.GOLD_HELMET;
        name = "Gold Helmet";
        description = "A helmet made of gold";
        component = Equipment.Component.HELMET;
        //sprite = new Sprite("path");
        /*onEquipStatModifications = new StatModificationList(
                new StatModification()
        )*/

    }

}
