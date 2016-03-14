package models.items.decorations.summonerDecorations;

import models.items.decorations.equippableItemDecoration;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by aseber on 3/7/16.
 */
public class dragonFurDecoration extends equippableItemDecoration {
    public dragonFurDecoration(){
        //ID
        IDModifier = 25;
        nameModifier = "Dragon Fur";
        descriptionModifier = "dragon fur";

        colorMap = new HashMap<>();
        colorMap.put(new Color(204, 204, 204), new Color(200, 0, 0));
        colorMap.put(new Color(255, 255, 255), new Color(255, 255, 255));
        colorMap.put(new Color(211,211,211),new Color(100,0,0));
        colorMap.put(new Color(187,187,187),new Color(160,0,0));
        colorMap.put(new Color(85, 85, 85), new Color(120, 0, 0));

        //Stats
        monetaryModifier = 800;
        defenseModifier = 5;
        levelRequiredModifier = 10;
        weightModifier = 2;
    }
}
