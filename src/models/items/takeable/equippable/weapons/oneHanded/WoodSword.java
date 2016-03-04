package models.items.takeable.equippable.weapons.oneHanded;

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
public class WoodSword extends EquippableItem{
    public WoodSword(){
        ID = Item.ItemDictionary.WOOD_SWORD;
        name = "Wood Sword";
        description = "Sword made of Wood";
        component = Equipment.Component.ONE_HANDED_WEAPON;
        sprite = new Sprite("./src/res/items/takeable/weapons/oneHanded/smasher-template-sword.png");
        monetaryValue = 10;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.WEAPON_MODIFIER, WOODATK)
        );
        requiredLv = WOODLV;

        range = SMASHERRANGE;
    }
}
