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
public class GoldGloves extends EquippableItem{
    public GoldGloves(){
        ID = Item.ItemDictionary.GOLD_GLOVES;
        name = "Gold Gloves";
        description = "Gloves made of gold";
        component = Equipment.Component.GLOVES;
        sprite = new Sprite("pathname");
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, 20)
        );
        requiredLv = 20;
    }

}
