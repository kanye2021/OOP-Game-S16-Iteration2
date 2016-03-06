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
public class SteelCoif extends EquippableItem{
    public SteelCoif() {

        ID = Item.ItemDictionary.STEEL_COIF;
        name = "Steel Coif";
        description = "A coif the color of Steel";
        component = Equipment.Component.HELMET;
        sprite = new Sprite("./src/res/items/takeable/armor/head/sneak-template-helm.png");
        monetaryValue = STEELCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, STEELDEF)
        );
        requiredLv = STEELLV;
        itemWeight = STEELWEIGHT;
        range = RANGE;
    }
}
