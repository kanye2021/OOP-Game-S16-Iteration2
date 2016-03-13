package models.items.decorations.sneakDecorations;

import models.items.decorations.equippableItemDecoration;

/**
 * Created by ben on 3/13/16.
 */
public class leatherDecoration extends equippableItemDecoration {
    public leatherDecoration(){
        //ID
        IDModifier = 21;
        nameModifier = "Leather";
        descriptionModifier = "leather";

        //Color Stuff

        //Stat Stuff
        monetaryModifier = 10;
        defenseModifier = 5;
        levelRequiredModifier = 1;
        weightModifier = 2;
    }
}
