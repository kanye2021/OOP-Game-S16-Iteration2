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
        colorMap.put(new Color(204, 204, 204), new Color(218, 165, 32));

        //Stats
        monetaryModifier = 800;
        defenseModifier = 5;
        levelRequiredModifier = 10;
        weightModifier = 2;
    }
}
