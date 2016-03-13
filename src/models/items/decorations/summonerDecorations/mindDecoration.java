package models.items.decorations.summonerDecorations;

import models.items.decorations.equippableItemDecoration;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by ben on 3/13/16.
 */
public class mindDecoration extends equippableItemDecoration {
    public mindDecoration(){
        //ID
        IDModifier = 26;
        nameModifier = "Mind Runes";
        descriptionModifier = "mine runes";

        //Color Map
        colorMap = new HashMap<>();
        colorMap.put(new Color(204, 204, 204), new Color(218, 165, 32));

        //Stats
        monetaryModifier = 10;
        defenseModifier = 5;
        levelRequiredModifier = 1;
        weightModifier = 2;
    }
}
