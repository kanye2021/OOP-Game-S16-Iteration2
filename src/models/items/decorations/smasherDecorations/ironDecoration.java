package models.items.decorations.smasherDecorations;

import models.items.decorations.equippableItemDecoration;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by aseber on 3/7/16.
 */
public class ironDecoration extends equippableItemDecoration {

    public ironDecoration() {

        IDModifier = 1;

        nameModifier = "Iron";
        descriptionModifier = "iron";

        colorMap = new HashMap<>();
        colorMap.put(new Color(204, 204, 204), new Color(40, 40, 40));
        colorMap.put(new Color(255, 255, 255), new Color(40, 40, 40));
        colorMap.put(new Color(187,187,187),new Color(90,90,90));
        colorMap.put(new Color(85, 85, 85), new Color(67, 67, 67));
        //colorMap.put(new Color(0,0,0), new Color(130,92,92));
        monetaryModifier = 50;
        defenseModifier = 10;
        attackModifier = 20;
        levelRequiredModifier = 5;
        weightModifier = 4;

    }

}
