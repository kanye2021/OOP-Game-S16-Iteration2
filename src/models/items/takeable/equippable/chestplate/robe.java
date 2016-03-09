package models.items.takeable.equippable.chestplate;

import models.Equipment;
import models.conditions.Condition;
import models.conditions.ConditionList;
import models.conditions.StatCondition;
import models.items.Item;
import models.items.decorations.equippableItemDecoration;
import models.items.takeable.equippable.EquippableItem;
import models.stats.StatModification;
import models.stats.StatModificationList;
import models.stats.Stats;
import views.sprites.DecoratedSprite;

/**
 * Created by aseber on 3/9/16.
 */
public class robe extends EquippableItem {

    public robe(equippableItemDecoration decoration) {

        ID = Item.ItemDictionary.getDictionaryItemFromID(1113 + decoration.IDModifier);
        name = "{NAME} Robe".replace("{NAME}", decoration.nameModifier);
        description = "A robe made of {NAME}".replace("{NAME}", decoration.descriptionModifier);
        component = Equipment.Component.CHESTPLATE;
        sprite = new DecoratedSprite("./src/res/items/takeable/armor/chest/summoner-template-chestplate.png", decoration.colorMap);
        monetaryValue = decoration.monetaryModifier;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, decoration.defenseModifier)
        );
        equipConditions = new ConditionList(
                new StatCondition(null, decoration.levelRequiredModifier, Stats.Type.LEVEL, Condition.Comparison.AT_LEAST)
        );
        itemWeight = decoration.weightModifier;
        range = RANGE;

    }

}
