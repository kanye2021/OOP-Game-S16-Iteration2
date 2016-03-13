package models.items.decorations.summonerDecorations;

import models.items.decorations.equippableItemDecoration;

/**
 * Created by aseber on 3/7/16.
 */
public class dragonFurDecoration extends equippableItemDecoration {
    public dragonFurDecoration(){
        //ID
        IDModifier = 25;
        nameModifier = "Dragon Fur";
        descriptionModifier = "dragon fur";

        //Stats
        monetaryModifier = 800;
        defenseModifier = 5;
        levelRequiredModifier = 10;
        weightModifier = 2;
    }
}
