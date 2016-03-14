package models.items.takeable.equippable.helmets;

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

/**
 * Created by aseber on 2/22/16.
 */
public class helmet extends EquippableItem {

    public helmet(equippableItemDecoration decoration) {

        ID = ItemDictionary.getDictionaryItemFromID(1000 + decoration.IDModifier);
        name = "{NAME} Helmet".replace("{NAME}", decoration.nameModifier);
        description = "A helmet made of {NAME}".replace("{NAME}", decoration.descriptionModifier);
        component = Equipment.Component.HELMET;
        sprite = new DecoratedSprite("./src/res/items/takeable/armor/head/smasher-template-helm.png", decoration.colorMap);
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
