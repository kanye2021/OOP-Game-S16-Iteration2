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
public class GoldBow extends EquippableItem{
    public GoldBow(){
        ID = Item.ItemDictionary.GOLD_BOW;
        name = "Gold bow";
        description = "A bow made of gold";
        component = Equipment.Component.TWO_HANDED_WEAPON;
        sprite = new Sprite("./src/res/items/takeable/weapons/twoHanded/sneak-template-bow.png");
        monetaryValue = GOLDCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.WEAPON_MODIFIER, GOLDATK)
        );
        requiredLv = 30;
    }
}
