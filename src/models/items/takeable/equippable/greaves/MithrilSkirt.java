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
public class MithrilSkirt extends EquippableItem {
    public MithrilSkirt(){
        ID = Item.ItemDictionary.MITHRIL_SKIRT;
        name = "Mithril Skirt";
        description = "A wizardly skirt made of Mithril";
        component = Equipment.Component.GREAVES;
        sprite = new Sprite("./src/res/items/takeable/armor/legs/summoner-template-greaves.png");
        monetaryValue = MITHRILCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, MITHRILDEF)
        );
        requiredLv = MITHRILLV;
        itemWeight = MITHRILWEIGHT;
        range = RANGE;
    }
}
