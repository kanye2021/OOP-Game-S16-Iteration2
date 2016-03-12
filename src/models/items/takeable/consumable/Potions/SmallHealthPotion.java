package models.items.takeable.consumable.Potions;

import models.items.Item;
import models.items.decorations.consumableItemDecoration;
import models.items.takeable.consumable.ConsumableItem;
import models.stats.StatModification;
import models.stats.StatModificationList;
import models.stats.Stats;
import views.sprites.Sprite;

/**
 * Created by David on 3/12/2016.
 */
public class SmallHealthPotion extends ConsumableItem {
    public SmallHealthPotion(consumableItemDecoration decoration){
        ID = Item.ItemDictionary.getDictionaryItemFromID(7000 + decoration.IDModifier);
        name = "{NAME} Health Potion".replace("{NAME}" ,decoration.nameModifier);
        description = "A cool {NAME} health potion". replace("{NAME})", decoration.descriptionModifier);
        sprite = new Sprite("./src/res/items/takeable/recovery_items/HealthPotion.png");
        monetaryValue = decoration.monetaryModifier;
        onConsumeModifications = new StatModificationList(
                new StatModification(Stats.Type.HEALTH, decoration.onUseModifier)
        );
        itemWeight = decoration.weightModifier;
    }
}
