package models.items.decorations.summonerDecorations;

import models.items.decorations.equippableItemDecoration;

import java.awt.*;
import java.util.HashMap;

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
        colorMap = new HashMap<>();
        colorMap.put(new Color(204, 204, 204), new Color(218, 165, 32));

        //Stat Stuff
        monetaryModifier = 10;
        defenseModifier = 5;
        levelRequiredModifier = 1;
        weightModifier = 2;
    }
}
