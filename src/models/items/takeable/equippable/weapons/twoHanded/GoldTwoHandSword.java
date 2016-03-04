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
public class GoldTwoHandSword extends EquippableItem{
    public GoldTwoHandSword(){
        ID = ItemDictionary.GOLD_TWO_HAND_SWORD;
        name = "Gold 2h Sword";
        description = "2h sword made of gold";
        component = Equipment.Component.TWO_HANDED_WEAPON;
        sprite = new Sprite("./src/res/items/takeable/weapons/twoHanded/smasher.template-2h.png");
        monetaryValue = GOLDCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.WEAPON_MODIFIER, GOLDATK*2)
        );
        requiredLv = GOLDLV;
        itemWeight = GOLDWEIGHT;
        range = SMASHERRANGE;
    }
}
