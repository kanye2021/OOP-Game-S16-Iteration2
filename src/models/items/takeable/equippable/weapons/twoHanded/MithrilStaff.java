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
public class MithrilStaff extends EquippableItem{
    public MithrilStaff(){
        ID = Item.ItemDictionary.MITHRIL_STAFF;
        name = "Mithril staff";
        description = "A staff made of mithril";
        component = Equipment.Component.TWO_HANDED_WEAPON;
        sprite = new Sprite("./src/res/items/takeable/weapons/twoHanded/StaffOfAir.png");
        monetaryValue = MITHRILCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.WEAPON_MODIFIER, MITHRILATK)
        );
        requiredLv = MITHRILLV;
        itemWeight = MITHRILWEIGHT;
        range = SUMMONERRANGE;
    }
}
