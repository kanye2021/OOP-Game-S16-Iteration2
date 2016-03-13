package models.items.decorations.summonerDecorations;

import models.items.decorations.equippableItemDecoration;

/**
 * Created by ben on 3/13/16.
 */
public class cosmicDecoration extends equippableItemDecoration {
    public cosmicDecoration(){
        //ID
        IDModifier = 24;
        nameModifier = "Cosmic Runes";
        descriptionModifier = "cosmic runes";

        //Stats
        monetaryModifier = 10;
        defenseModifier = 5;
        levelRequiredModifier = 1;
        weightModifier = 2;
    }
}
