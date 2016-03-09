package models.items.takeable.equippable.weapons.oneHanded;

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
 * Created by aseber on 3/2/16.
 */
public class swordTemplate extends EquippableItem{
    public swordTemplate(equippableItemDecoration decoration){
        ID = Item.ItemDictionary.getDictionaryItemFromID(1600 + decoration.IDModifier);
        name = "{NAME} Sword".replace("{NAME}", decoration.nameModifier);
        description = "Sword made of {NAME}".replace("{NAME}", decoration.descriptionModifier);
        component = Equipment.Component.ONE_HANDED_WEAPON;
        sprite = new DecoratedSprite("./src/res/items/takeable/weapons/oneHanded/smasher-template-sword.png", decoration.colorMap);
        monetaryValue = decoration.monetaryModifier;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.WEAPON_MODIFIER, decoration.attackModifier)
        );
        equipConditions = new ConditionList(
                new StatCondition(null, decoration.levelRequiredModifier, Stats.Type.LEVEL, Condition.Comparison.AT_LEAST)
        );
        itemWeight = decoration.weightModifier;
        range = SMASHERRANGE;
    }
}
