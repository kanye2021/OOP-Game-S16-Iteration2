package models.items.takeable.equippable.boots;

import models.Equipment;
import models.items.Item;
import models.items.takeable.equippable.EquippableItem;
import models.stats.StatModification;
import models.stats.StatModificationList;
import models.stats.Stats;
import views.sprites.Sprite;

/**
 * Created by ben on 2/27/16.
 */
public class WoodBoots extends EquippableItem{

    public WoodBoots(){
        ID = Item.ItemDictionary.WOOD_BOOTS;
        name = "Wood Boots";
        description = "Boots made of wood";
        component = Equipment.Component.BOOTS;
        sprite = new Sprite("./src/res/items/takeable/armor/boots/templateBoots.png");
        monetaryValue=WOODCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, WOODDEF)
        );
        requiredLv = WOODLV;
        itemWeight = WOODWEIGHT;
        range = RANGE;
    }
}
