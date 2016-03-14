package models.items.decorations.smasherDecorations;

import models.items.decorations.equippableItemDecoration;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by aseber on 3/7/16.
 */
public class woodDecoration extends equippableItemDecoration {

    public woodDecoration() {

        IDModifier = 0;

        nameModifier = "Wood";
        descriptionModifier = "wood";

        colorMap = new HashMap<>();
        colorMap.put(new Color(204, 204, 204), new Color(83, 60, 60));
        colorMap.put(new Color(255, 255, 255), new Color(83, 60, 60));
        colorMap.put(new Color(211,211,211),new Color(255,0,0));
        colorMap.put(new Color(187,187,187),new Color(130,92,92));
        colorMap.put(new Color(85, 85, 85), new Color(102, 51, 0));
        //colorMap.put(new Color(0,0,0), new Color(130,92,92));

        monetaryModifier = 10;
        defenseModifier = 5;
        attackModifier = 10;
        levelRequiredModifier = 1;
        weightModifier = 2;

    }

}
