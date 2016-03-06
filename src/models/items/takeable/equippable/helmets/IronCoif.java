package models.items.takeable.equippable.helmets;

import models.Equipment;
import models.items.Item;
import models.items.takeable.equippable.EquippableItem;
import models.stats.StatModification;
import models.stats.StatModificationList;
import models.stats.Stats;
import views.sprites.Sprite;

/**
 * Created by ben on 3/5/16.
 */
public class IronCoif extends EquippableItem{
    public IronCoif() {

        ID = Item.ItemDictionary.IRON_COIF;
        name = "Iron Coif";
        description = "A coif the color of Iron";
        component = Equipment.Component.HELMET;
        sprite = new Sprite("./src/res/items/takeable/armor/head/sneak-template-helm.png");
        monetaryValue = IRONCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, IRONDEF)
        );
        requiredLv = IRONLV;
        itemWeight = IRONWEIGHT;
        range = RANGE;
    }
}
