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
public class MithrilChaps extends EquippableItem{
    public MithrilChaps(){
        ID = Item.ItemDictionary.MITHRIL_CHAPS;
        name = "Mithril Chaps";
        description = "Chaps made of Mithril";
        component = Equipment.Component.GREAVES;
        sprite = new Sprite("./src/res/items/takeable/armor/legs/sneak-template-greaves.png");
        monetaryValue = MITHRILCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, MITHRILDEF)
        );
        requiredLv = MITHRILLV;
        itemWeight = MITHRILWEIGHT;
        range = RANGE;
    }
}
