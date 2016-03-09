package models.items.takeable.equippable.gloves;

import models.Equipment;
import models.conditions.*;
import models.items.Item;
import models.items.decorations.equippableItemDecoration;
import models.items.takeable.equippable.EquippableItem;
import models.stats.*;
import views.sprites.DecoratedSprite;
import views.sprites.Sprite;

/**
 * Created by ben on 2/27/16.
 */
public class gloveTemplate extends EquippableItem{
    public gloveTemplate(equippableItemDecoration decoration) {
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
        range = RANGE;

    }

}
