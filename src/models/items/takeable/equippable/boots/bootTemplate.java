package models.items.takeable.equippable.boots;

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
import views.sprites.Sprite;

/**
 * Created by aseber on 3/7/16.
 */
public class bootTemplate extends EquippableItem {

    public bootTemplate(equippableItemDecoration decoration){
        ID = Item.ItemDictionary.getDictionaryItemFromID(1300 + decoration.IDModifier);
        name = "{NAME} Boots".replace("{NAME}", decoration.nameModifier);
        description = "Boots made of {NAME}".replace("{NAME}", decoration.descriptionModifier);
        component = Equipment.Component.BOOTS;
        sprite = new DecoratedSprite("./src/res/items/takeable/armor/boots/templateBoots.png", decoration.colorMap);
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
