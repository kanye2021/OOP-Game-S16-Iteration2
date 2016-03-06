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
public class GoldSkirt extends EquippableItem{
    public GoldSkirt(){
        ID = Item.ItemDictionary.GOLD_SKIRT;
        name = "Gold Skirt";
        description = "A wizardly skirt made of gold";
        component = Equipment.Component.GREAVES;
        sprite = new Sprite("./src/res/items/takeable/armor/legs/summoner-template-greaves.png");
        monetaryValue = GOLDCOST;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, GOLDDEF)
        );
        requiredLv = GOLDLV;
        itemWeight = GOLDWEIGHT;
        range = RANGE;
    }
}
