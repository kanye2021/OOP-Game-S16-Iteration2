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
public class GoldBoots extends EquippableItem{
    public GoldBoots(){
        ID = Item.ItemDictionary.GOLD_BOOTS;
        name = "Gold Boots";
        description = "Boots made of gold";
        component = Equipment.Component.BOOTS;
        sprite = new Sprite("./src/res/items/takeable/armor/boots/templateBoots.png");
        monetaryValue = GOLDCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, 20)
        );
        requiredLv = 20;
    }
}
