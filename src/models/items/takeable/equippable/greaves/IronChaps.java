package models.items.takeable.equippable.greaves;

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
public class IronChaps extends EquippableItem{
    public IronChaps(){
        ID = Item.ItemDictionary.IRON_CHAPS;
        name = "Iron Chaps";
        description = "Chaps made of iron";
        component = Equipment.Component.GREAVES;
        sprite = new Sprite("./src/res/items/takeable/armor/legs/sneak-template-greaves.png");
        monetaryValue = IRONCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, IRONDEF)
        );
        requiredLv = IRONLV;
        itemWeight = IRONWEIGHT;
        range = RANGE;
    }
}
