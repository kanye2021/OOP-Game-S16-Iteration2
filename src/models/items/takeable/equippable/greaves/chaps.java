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
public class chaps extends EquippableItem {

    public chaps(equippableItemDecoration decoration) {

        ID = Item.ItemDictionary.getDictionaryItemFromID(1206 + decoration.IDModifier);
        name = "{NAME} Chaps".replace("{NAME}", decoration.nameModifier);
        description = "chaps made of {NAME}".replace("{NAME}", decoration.descriptionModifier);
        component = Equipment.Component.GREAVES;
        sprite = new DecoratedSprite("./src/res/items/takeable/armor/legs/sneak-template-greaves.png", decoration.colorMap);
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
