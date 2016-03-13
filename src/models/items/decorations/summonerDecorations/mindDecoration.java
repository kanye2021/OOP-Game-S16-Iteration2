package models.items.decorations.summonerDecorations;

import models.items.decorations.equippableItemDecoration;

/**
 * Created by ben on 3/13/16.
 */
public class mindDecoration extends equippableItemDecoration {
    public mindDecoration(){
        //ID
        IDModifier = 26;
        nameModifier = "Mind Runes";
        descriptionModifier = "mine runes";

        //Stats
        monetaryModifier = 10;
        defenseModifier = 5;
        levelRequiredModifier = 1;
        weightModifier = 2;
    }
}
