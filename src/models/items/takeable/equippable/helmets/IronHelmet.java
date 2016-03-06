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
public class IronHelmet extends EquippableItem {

    public IronHelmet() {

        ID = ItemDictionary.IRON_HELMET;
        name = "Iron Helmet";
        description = "A helmet made of iron";
        component = Equipment.Component.HELMET;
        sprite = new Sprite("./src/res/items/takeable/armor/head/smasher-template-helm.png");
        monetaryValue = IRONCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, IRONDEF)
        );
        requiredLv = IRONLV;
        itemWeight = IRONWEIGHT;
        range = RANGE;
    }

}
