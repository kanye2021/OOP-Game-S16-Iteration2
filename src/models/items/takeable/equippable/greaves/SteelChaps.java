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
public class SteelChaps extends EquippableItem{
    public SteelChaps(){
        ID = Item.ItemDictionary.STEEL_CHAPS;
        name = "Steel Chaps";
        description = "Chaps made of steel";
        component = Equipment.Component.GREAVES;
        sprite = new Sprite("./src/res/items/takeable/armor/legs/sneak-template-greaves.png");
        monetaryValue = STEELCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, STEELDEF)
        );
        requiredLv = STEELLV;
        itemWeight = STEELWEIGHT;
        range = RANGE;
    }
}
