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
public class Special_SwampHacksShirt extends EquippableItem{
    public Special_SwampHacksShirt(){
        ID = ItemDictionary.SWAMPHACKS_SHIRT;
        name = "SwampHacks Shirt";
        description = "A shirt that provides an unexpected amount of defense";
        component = Equipment.Component.CHESTPLATE;
        sprite = new Sprite("./src/res/items/takeable/armor/chest/swamphacksshirt.png");
        monetaryValue = RUNITECOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, CHUCKNORRISDEF)
        );
        requiredLv = CHUCKNORRISLV;
        itemWeight = CHUCKNORRISWEIGHT;
        range = RANGE;
    }

}
