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
public class WoodCoif extends EquippableItem{
    public WoodCoif() {

        ID = Item.ItemDictionary.WOOD_COIF;
        name = "Wood Coif";
        description = "A coif the color of Wood";
        component = Equipment.Component.HELMET;
        sprite = new Sprite("./src/res/items/takeable/armor/head/sneak-template-helm.png");
        monetaryValue = WOODCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, WOODDEF)
        );
        requiredLv = WOODLV;
        itemWeight = WOODWEIGHT;
        range = RANGE;
    }
}
