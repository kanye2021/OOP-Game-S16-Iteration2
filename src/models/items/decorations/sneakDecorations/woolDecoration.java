package models.items.decorations.sneakDecorations;

import models.items.decorations.equippableItemDecoration;

/**
 * Created by aseber on 3/7/16.
 */
public class woolDecoration extends equippableItemDecoration {
    public woolDecoration(){
        //ID
        IDModifier = 22;
        nameModifier = "wool";
        descriptionModifier = "wool";

        //Color Stuff
        monetaryModifier = 10;
        defenseModifier = 5;
        levelRequiredModifier = 1;
        weightModifier = 2;
    }
}
