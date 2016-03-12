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
        colorMap.put(new Color(255, 255, 255), new Color(255, 223, 0));

        monetaryModifier = 400;
        defenseModifier = 40;
        attackModifier = 60;
        levelRequiredModifier = 30;
        weightModifier = 10;

    }

}
