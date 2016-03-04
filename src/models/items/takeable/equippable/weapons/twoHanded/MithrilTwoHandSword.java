package models.items.takeable.equippable.weapons.twoHanded;

import models.Equipment;
import models.items.Item;
import models.items.takeable.equippable.EquippableItem;
import models.stats.StatModification;
import models.stats.StatModificationList;
import models.stats.Stats;
import views.sprites.Sprite;

/**
 * Created by ben on 3/2/16.
 */
public class MithrilTwoHandSword extends EquippableItem{
    public MithrilTwoHandSword(){
        ID = Item.ItemDictionary.MITHRIL_TWO_HAND_SWORD;
        name = "MITHRIL 2h Sword";
        description = "2h sword made of mithril";
        component = Equipment.Component.TWO_HANDED_WEAPON;
        sprite = new Sprite("./src/res/items/takeable/weapons/twoHanded/smasher.template-2h.png");
        monetaryValue = MITHRILCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.WEAPON_MODIFIER, MITHRILATK*2)
        );
        requiredLv = MITHRILLV;
        itemWeight = MITHRILWEIGHT;
        range = SMASHERRANGE;
    }
}
