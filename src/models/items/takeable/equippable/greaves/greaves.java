package models.items.takeable.equippable.greaves;

import models.Equipment;
import models.conditions.Condition;
import models.conditions.ConditionList;
import models.conditions.StatCondition;
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
public class greaves extends EquippableItem {

    public greaves(equippableItemDecoration decoration) {

        ID = ItemDictionary.getDictionaryItemFromID(1200 + decoration.IDModifier);
        name = "{NAME} Greaves".replace("{NAME}", decoration.nameModifier);
        description = "Greaves made of {NAME}".replace("{NAME}", decoration.descriptionModifier);
        component = Equipment.Component.GREAVES;
        sprite = new DecoratedSprite("./src/res/items/takeable/armor/legs/smasher-template-greaves.png", decoration.colorMap);
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
