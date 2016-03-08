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
        colorMap.put(new Color(255, 255, 255), new Color(0, 0, 255));

        monetaryModifier = 50;
        defenseModifier = 10;
        attackModifier = 20;
        levelRequiredModifier = 5;
        weightModifier = 4;

    }

}
