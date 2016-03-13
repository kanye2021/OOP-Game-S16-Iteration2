package models.items.decorations.smasherDecorations;

import models.items.decorations.equippableItemDecoration;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by aseber on 3/7/16.
 */
public class goldDecoration extends equippableItemDecoration {

    public goldDecoration() {

        IDModifier = 4;

        nameModifier = "Gold";
        descriptionModifier = "gold";

        colorMap = new HashMap<>();
        colorMap.put(new Color(255, 255, 255), new Color(218, 165, 32));
        colorMap.put(new Color(187,187,187),new Color(255,223,92));
        colorMap.put(new Color(85, 85, 85), new Color(255, 215, 0));
        //colorMap.put(new Color(0,0,0), new Color(130,92,92));
        monetaryModifier = 400;
        defenseModifier = 40;
        attackModifier = 60;
        levelRequiredModifier = 30;
        weightModifier = 10;

    }

}
