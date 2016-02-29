package models.items.takeable.equippable.shield;

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
public class MithrilShield extends EquippableItem{
    public MithrilShield(){
        ID = Item.ItemDictionary.MITHRIL_SHIELD;
        name = "Mithril Shield";
        description = "Shield made of mithril";
        component = Equipment.Component.SHIELD;
        sprite = new Sprite("pathname");
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, 50)
        );
        requiredLv = 30;
    }
}
