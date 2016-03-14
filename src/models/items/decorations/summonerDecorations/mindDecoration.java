package models.items.decorations.summonerDecorations;

import models.items.decorations.equippableItemDecoration;

import java.awt.*;
import java.util.HashMap;

public class mindDecoration extends equippableItemDecoration {
    public mindDecoration(){
        //ID
        IDModifier = 26;
        nameModifier = "Mind Runes";
        descriptionModifier = "mine runes";

        //Color Map
        colorMap = new HashMap<>();
        colorMap.put(new Color(204, 204, 204), new Color(255, 128, 114));
        colorMap.put(new Color(255, 255, 255), new Color(255, 255, 255));
        colorMap.put(new Color(211,211,211),new Color(255,165,0));
        colorMap.put(new Color(187,187,187),new Color(255,140,0));
        colorMap.put(new Color(85, 85, 85), new Color(255, 99, 71));

        //Stats
        monetaryModifier = 10;
        defenseModifier = 5;
        levelRequiredModifier = 1;
        weightModifier = 2;
    }
}
