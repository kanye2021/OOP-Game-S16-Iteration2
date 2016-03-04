package models.items.takeable.equippable.weapons.twoHanded;

import models.Equipment;
import models.items.Item;
import models.items.takeable.equippable.EquippableItem;
import models.stats.StatModification;
import models.stats.StatModificationList;
import models.stats.Stats;
import views.sprites.Sprite;

/**
 * Created by ben on 3/2/16.
 */
public class WoodBow extends EquippableItem{
    public WoodBow(){
        ID = Item.ItemDictionary.WOOD_BOW;
        name = "Wood bow";
        description = "A bow made of wood";
        component = Equipment.Component.TWO_HANDED_WEAPON;
        sprite = new Sprite("pathname");
        monetaryValue = WOODCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.WEAPON_MODIFIER, WOODATK)
        );
        requiredLv = WOODLV;
        itemWeight = WOODWEIGHT;
        range = SNEAKRANGE;
    }
}