package models.items.takeable.equippable.helmets;

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
public class MithrilCoif extends EquippableItem{
    public MithrilCoif() {

        ID = Item.ItemDictionary.MITHRIL_COIF;
        name = "Mithril Coif";
        description = "A coif the color of Mithril";
        component = Equipment.Component.HELMET;
        sprite = new Sprite("./src/res/items/takeable/armor/head/sneak-template-helm.png");
        monetaryValue = MITHRILCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, MITHRILDEF)
        );
        requiredLv = MITHRILLV;
        itemWeight = MITHRILWEIGHT;
        range = RANGE;
    }
}
