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
public class RuniteSword extends EquippableItem{
    public RuniteSword(){
        ID = Item.ItemDictionary.RUNITE_SWORD;
        name = "Runite Sword";
        description = "Sword made of runite";
        component = Equipment.Component.ONE_HANDED_WEAPON;
        sprite = new Sprite("./src/res/items/takeable/weapons/oneHanded/smasher-runite-sword.png");
        monetaryValue = RUNITECOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.WEAPON_MODIFIER, RUNITEATK)
        );
    }
}
