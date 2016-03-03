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
public class SteelSword extends EquippableItem{
    public SteelSword(){
        ID = Item.ItemDictionary.STEEL_SWORD;
        name = "Steel Sword";
        description = "Sword made of steel";
        component = Equipment.Component.ONE_HANDED_WEAPON;
        sprite = new Sprite("./src/res/items/takeable/weapons/oneHanded/smasher-template-sword.png");
        monetaryValue = 100;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.WEAPON_MODIFIER, 15)
        );
        requiredLv = 10;
    }
}
