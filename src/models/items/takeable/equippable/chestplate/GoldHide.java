package models.items.takeable.equippable.chestplate;

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
public class GoldHide extends EquippableItem{
    public GoldHide(){
        ID = Item.ItemDictionary.GOLD_HIDE;
        name = "Gold Hide";
        description = "Hide the color of gold";
        component = Equipment.Component.CHESTPLATE;
        sprite = new Sprite("./src/res/items/takeable/armor/chest/sneak-template-chestplate.png");
        monetaryValue = GOLDCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, GOLDDEF)
        );
        requiredLv = GOLDLV;
        itemWeight = GOLDWEIGHT;
        range = RANGE;
    }
}
