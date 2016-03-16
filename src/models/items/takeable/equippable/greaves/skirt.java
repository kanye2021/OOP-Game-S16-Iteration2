package models.items.takeable.equippable.greaves;

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
 * Created by austin on 3/5/16.
 */
public class skirt extends EquippableItem {
    public skirt(equippableItemDecoration decoration) {
        ID = Item.ItemDictionary.getDictionaryItemFromID(1212 + decoration.IDModifier);
        name = "{NAME} Skirt".replace("{NAME}", decoration.nameModifier);
        description = "A wizardly skirt made of {NAME}".replace("{NAME}", decoration.descriptionModifier);
        component = Equipment.Component.GREAVES;
        sprite = new DecoratedSprite("./src/res/items/takeable/armor/legs/summoner-template-greaves.png", decoration.colorMap);
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
