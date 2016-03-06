package models.items.takeable.equippable.weapons.oneHanded;

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
public class Special_EnergySword extends EquippableItem{
    public Special_EnergySword(){
        ID = Item.ItemDictionary.ENERGY_SWORD;
        name = "Energy Sword";
        description = "If you someone with this sword... run";
        component = Equipment.Component.ONE_HANDED_WEAPON;
        sprite = new Sprite("./src/res/items/takeable/weapons/oneHanded/EnergySword.png");
        monetaryValue = CHUCKNORRISCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.WEAPON_MODIFIER, CHUCKNORRISATK)
        );
        requiredLv = CHUCKNORRISLV;
        itemWeight = CHUCKNORRISWEIGHT;
        range = SMASHERRANGE + 1;
    }
}
