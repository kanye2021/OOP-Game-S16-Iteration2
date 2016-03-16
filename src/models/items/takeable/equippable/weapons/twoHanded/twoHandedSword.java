package models.items.takeable.equippable.weapons.twoHanded;

import models.Equipment;
import models.conditions.Condition;
import models.conditions.ConditionList;
import models.conditions.OccupationCondition;
import models.conditions.StatCondition;
import models.items.decorations.equippableItemDecoration;
import models.items.takeable.equippable.weapons.WeaponItem;
import models.stats.StatModification;
import models.stats.StatModificationList;
import models.stats.Stats;
import views.sprites.DecoratedSprite;

/**
 * Created by aseber on 3/2/16.
 */
public class twoHandedSword extends WeaponItem {
    public twoHandedSword(equippableItemDecoration decoration) {
        ID = ItemDictionary.getDictionaryItemFromID(1700 + decoration.IDModifier);
        name = "{NAME} 2h Sword".replace("{NAME}", decoration.nameModifier);
        description = "2h sword made of {NAME}".replace("{NAME}", decoration.descriptionModifier);
        component = Equipment.Component.TWO_HANDED_WEAPON;
        sprite = new DecoratedSprite("./src/res/items/takeable/weapons/twoHanded/smasher-template-2h.png", decoration.colorMap);
        monetaryValue = decoration.monetaryModifier;
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.WEAPON_MODIFIER, decoration.attackModifier * 2)
        );
        equipConditions = new ConditionList(
                new OccupationCondition(null, "Smasher", Condition.Comparison.EXACTLY),
                new StatCondition(null, decoration.levelRequiredModifier, Stats.Type.LEVEL, Condition.Comparison.AT_LEAST)
        );
        itemWeight = decoration.weightModifier;
        range = SMASHERRANGE;
    }
}
