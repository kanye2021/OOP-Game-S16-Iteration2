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
public class MithrilHat extends EquippableItem{
    public MithrilHat() {

        ID = Item.ItemDictionary.MITHRIL_HAT;
        name = "Mithril Hat";
        description = "A pointy hat that is the color of mithril";
        component = Equipment.Component.HELMET;
        sprite = new Sprite("./src/res/items/takeable/armor/head/summoner-template-helm.png");
        monetaryValue = MITHRILCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, MITHRILDEF)
        );
        requiredLv = MITHRILLV;
        itemWeight = MITHRILWEIGHT;
        range = RANGE;
    }
}
