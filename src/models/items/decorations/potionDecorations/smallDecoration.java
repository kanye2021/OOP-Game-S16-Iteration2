package models.items.decorations.potionDecorations;

import models.items.decorations.consumableItemDecoration;

/**
 * Created by David on 3/12/2016.
 */
public class smallDecoration extends consumableItemDecoration {
    public smallDecoration() {

        IDModifier = 1;

        nameModifier = "Small";
        descriptionModifier = "small";

        onUseModifier = 20;
        monetaryModifier = 20;
        weightModifier = 1;
    }
}
