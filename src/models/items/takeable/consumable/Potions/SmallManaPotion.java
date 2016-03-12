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
public class SmallManaPotion extends ConsumableItem {
    public SmallManaPotion(consumableItemDecoration decoration){
        ID = Item.ItemDictionary.getDictionaryItemFromID(7000 + decoration.IDModifier);
        name = "{NAME} Mana Potion".replace("{NAME}",decoration.nameModifier);
        description = "A rad {NAME} mana potion". replace("{NAME}", decoration.descriptionModifier);
        sprite = new Sprite("./src/res/items/takeable/recovery_items/ManaPotion.png");
        monetaryValue = decoration.monetaryModifier;
        onConsumeModifications = new StatModificationList(
                new StatModification(Stats.Type.MANA, decoration.onUseModifier)
        );
        itemWeight = decoration.weightModifier;
    }
}
