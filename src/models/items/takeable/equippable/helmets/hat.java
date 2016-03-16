package models.items.takeable.equippable.helmets;

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
public class hat extends EquippableItem {

    public hat(equippableItemDecoration decoration) {

        ID = Item.ItemDictionary.getDictionaryItemFromID(1012 + decoration.IDModifier);
        name = "{NAME} Hat".replace("{NAME}", decoration.nameModifier);
        description = "A hat from the {NAME} realm".replace("{NAME}", decoration.descriptionModifier);
        component = Equipment.Component.HELMET;
        sprite = new DecoratedSprite("./src/res/items/takeable/armor/head/summoner-template-helm.png", decoration.colorMap);
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
