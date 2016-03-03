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
public class SteelGloves extends EquippableItem{
    public SteelGloves(){
        ID = Item.ItemDictionary.STEEL_GLOVES;
        name = "Steel Gloves";
        description = "Gloves made of steel";
        component = Equipment.Component.GLOVES;
        sprite = new Sprite("./src/res/items/takeable/armor/gloves/templateGloves.png");
        monetaryValue = STEELCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, 15)
        );
    }
}
