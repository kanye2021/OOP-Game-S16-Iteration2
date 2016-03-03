package models.items.takeable.equippable.greaves;

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
public class GoldGreaves extends EquippableItem{
    public GoldGreaves(){
        ID = ItemDictionary.GOLD_GREAVES;
        name = "Gold Greaves";
        description = "Greaves made of gold";
        component = Equipment.Component.GREAVES;
        sprite = new Sprite("./src/res/items/takeable/armor/legs/smasher-template-greaves.png");
        monetaryValue = GOLDCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, 15)
        );
        requiredLv = 20;
    }
}
