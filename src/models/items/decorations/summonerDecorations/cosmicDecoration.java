package models.items.decorations.summonerDecorations;

import models.items.decorations.equippableItemDecoration;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by ben on 3/13/16.
 */
public class cosmicDecoration extends equippableItemDecoration {
    public cosmicDecoration(){
        //ID
        IDModifier = 24;
        nameModifier = "Cosmic Runes";
        descriptionModifier = "cosmic runes";

        colorMap = new HashMap<>();
        colorMap.put(new Color(204, 204, 204), new Color(200, 200, 0));
        colorMap.put(new Color(255, 255, 255), new Color(255, 255, 255));
        colorMap.put(new Color(211,211,211),new Color(100,100,0));
        colorMap.put(new Color(187,187,187),new Color(160,160,0));
        colorMap.put(new Color(85, 85, 85), new Color(230, 210, 120));

        //Stats
        monetaryModifier = 10;
        defenseModifier = 5;
        levelRequiredModifier = 1;
        weightModifier = 2;
    }
}
