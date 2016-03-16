package models.items.takeable.equippable.gloves;

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
 * Created by ben on 2/27/16.
 */
public class gloves extends EquippableItem {
    public gloves(equippableItemDecoration decoration) {
        ID = Item.ItemDictionary.getDictionaryItemFromID(1400 + decoration.IDModifier);
        name = "{NAME} Gloves".replace("{NAME}", decoration.nameModifier);
        description = "Gloves made of {NAME}".replace("{NAME}", decoration.descriptionModifier);
        component = Equipment.Component.GLOVES;
        sprite = new DecoratedSprite("./src/res/items/takeable/armor/gloves/templateGloves.png", decoration.colorMap);
        monetaryValue = decoration.monetaryModifier;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.ARMOR_MODIFIER, decoration.defenseModifier)
        );
        equipConditions = new ConditionList(
                new StatCondition(null, decoration.levelRequiredModifier, Stats.Type.LEVEL, Condition.Comparison.AT_LEAST)
        );
        itemWeight = decoration.weightModifier;

    }

}
