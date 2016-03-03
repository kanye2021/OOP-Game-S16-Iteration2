package models.items.takeable.equippable.gloves;

import models.Equipment;
import models.items.Item;
import models.items.takeable.equippable.EquippableItem;
import models.stats.StatModification;
import models.stats.StatModificationList;
import models.stats.Stats;
import views.sprites.Sprite;

/**
 * Created by ben on 2/27/16.
 */
public class IronGloves extends EquippableItem{
    public IronGloves(){
        ID = Item.ItemDictionary.IRON_GLOVES;
        name = "Iron Gloves";
        description = "Gloves made of iron";
        component = Equipment.Component.GLOVES;
        sprite = new Sprite("./src/res/items/takeable/armor/gloves/templateGloves.png");
        monetaryValue = IRONCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, 10)
        );
        requiredLv = 5;
    }
}
