package models.items.takeable.equippable.boots;

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
public class MithrilBoots extends EquippableItem{
    public MithrilBoots(){
        ID = ItemDictionary.MITHRIL_BOOTS;
        name = "Mithril Boots";
        description = "Boots made of mithril";
        component = Equipment.Component.BOOTS;
        sprite = new Sprite("pathname");
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, 25)
        );
        requiredLv = 30;
    }
}
