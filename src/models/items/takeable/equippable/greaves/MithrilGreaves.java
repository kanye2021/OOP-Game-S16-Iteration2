package models.items.takeable.equippable.greaves;

import models.Equipment;
import models.items.takeable.equippable.EquippableItem;
import models.stats.StatModification;
import models.stats.StatModificationList;
import models.stats.Stats;
import views.sprites.Sprite;

/**
 * Created by ben on 2/27/16.
 */
public class MithrilGreaves extends EquippableItem {
    public MithrilGreaves(){
        ID = ItemDictionary.MITHRIL_GREAVES;
        name = "Mithril Greaves";
        description = "Greaves made of mithril";
        component = Equipment.Component.GREAVES;
        sprite = new Sprite("pathname");
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, 20)
        );
    }
}
