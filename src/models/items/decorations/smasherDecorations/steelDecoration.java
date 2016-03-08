package models.items.decorations.smasherDecorations;

import models.items.decorations.equippableItemDecoration;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by aseber on 3/7/16.
 */
public class steelDecoration extends equippableItemDecoration {

    public steelDecoration() {

        IDModifier = 2;

        nameModifier = "Steel";
        descriptionModifier = "steel";

        colorMap = new HashMap<>();
        colorMap.put(new Color(255, 255, 255), new Color(0, 0, 255));

        monetaryModifier = 100;
        defenseModifier = 20;
        attackModifier = 30;
        levelRequiredModifier = 10;
        weightModifier = 6;

    }

}
