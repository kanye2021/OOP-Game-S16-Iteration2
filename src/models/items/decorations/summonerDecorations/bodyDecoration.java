package models.items.decorations.summonerDecorations;

import models.items.decorations.equippableItemDecoration;

/**
 * Created by ben on 3/13/16.
 */
public class bodyDecoration extends equippableItemDecoration {
    public bodyDecoration(){
        //ID
        IDModifier = 23;
        nameModifier = "Body Runes";
        descriptionModifier = "body runes";

        //Color Stuff

        //Stat Stuff
        monetaryModifier = 10;
        defenseModifier = 5;
        levelRequiredModifier = 1;
        weightModifier = 2;
    }
}
