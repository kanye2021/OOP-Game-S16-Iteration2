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
public class MithrilGloves extends EquippableItem{
    public MithrilGloves(){
        ID = Item.ItemDictionary.MITHRIL_GLOVES;
        name = "Mithril Gloves";
        description = "Gloves made of mithril";
        component = Equipment.Component.GLOVES;
        sprite = new Sprite("pathname");
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, 25)
        );
        requiredLv = 30;
    }
}
