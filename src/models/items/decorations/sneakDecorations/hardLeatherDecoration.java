package models.items.decorations.sneakDecorations;

import models.items.decorations.equippableItemDecoration;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by aseber on 3/9/16.
 */
public class hardLeatherDecoration extends equippableItemDecoration {

    public hardLeatherDecoration() {

        IDModifier = 1;

        nameModifier = "Hard leather";
        descriptionModifier = "hard leather";

        colorMap = new HashMap<>();
        colorMap.put(new Color(255, 255, 255), new Color(0, 0, 255));

        monetaryModifier = 10;
        defenseModifier = 5;
        attackModifier = 10;
        levelRequiredModifier = 1;
        weightModifier = 2;

    }

}
