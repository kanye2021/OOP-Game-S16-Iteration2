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
public class WoodGloves extends EquippableItem{
    public WoodGloves(){
        ID = Item.ItemDictionary.WOOD_GLOVES;
        name = "Wood Gloves";
        description = "Gloves made of wood";
        component = Equipment.Component.GLOVES;
        sprite = new Sprite("pathname");
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, 5)
        );
        requiredLv = 1;
    }
}
