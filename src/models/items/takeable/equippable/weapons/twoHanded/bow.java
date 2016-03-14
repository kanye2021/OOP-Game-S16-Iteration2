package models.items.takeable.equippable.weapons.twoHanded;


import models.Equipment;
import models.conditions.Condition;
import models.conditions.ConditionList;
import models.conditions.OccupationCondition;
import models.conditions.StatCondition;
import models.items.Item;
import models.items.decorations.equippableItemDecoration;

import models.items.takeable.equippable.EquippableItem;
import models.stats.StatModification;
import models.stats.StatModificationList;
import models.stats.Stats;
import views.sprites.DecoratedSprite;



public class bow extends EquippableItem {
    public bow(equippableItemDecoration decoration){
        ID = Item.ItemDictionary.getDictionaryItemFromID(1900 + decoration.IDModifier);
        name = "{NAME} Bow".replace("{NAME}", decoration.nameModifier);
        description = "Bow made of {NAME}".replace("{NAME}", decoration.descriptionModifier);
        component = Equipment.Component.TWO_HANDED_WEAPON;
        sprite = new DecoratedSprite("./src/res/items/takeable/weapons/twoHanded/sneak-template-bow.png", decoration.colorMap);
        monetaryValue = decoration.monetaryModifier;
        onEquipModifications = new StatModificationList(

                new StatModification(Stats.Type.WEAPON_MODIFIER, decoration.attackModifier)
        );
        equipConditions = new ConditionList(
                new OccupationCondition(null, "Sneak", Condition.Comparison.EXACTLY),
                new StatCondition(null, decoration.levelRequiredModifier, Stats.Type.LEVEL, Condition.Comparison.AT_LEAST)
        );
        itemWeight = decoration.weightModifier;
        range = SNEAKRANGE;
    }

}
