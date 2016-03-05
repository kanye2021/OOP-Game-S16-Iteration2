package models.items.takeable.equippable.chestplate;

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
public class WoodHide extends EquippableItem{
    public WoodHide(){
        ID = Item.ItemDictionary.WOOD_HIDE;
        name = "Wood Hide";
        description = "Hide the color of wood";
        component = Equipment.Component.CHESTPLATE;
        sprite = new Sprite("./src/res/items/takeable/armor/chest/sneak-template-chestplate.png");
        monetaryValue = WOODCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, WOODDEF)
        );
        requiredLv = WOODLV;
        itemWeight = WOODWEIGHT;
        range = RANGE;
    }
}
