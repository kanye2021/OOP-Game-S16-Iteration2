package models.items.decorations.summonerDecorations;

import models.items.decorations.equippableItemDecoration;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by aseber on 3/9/16.
 */
public class chaosDecoration extends equippableItemDecoration {

    public chaosDecoration() {

        IDModifier = 3;

        nameModifier = "Chaos";
        descriptionModifier = "chaos";

        colorMap = new HashMap<>();
        colorMap.put(new Color(255, 255, 255), new Color(0, 0, 255));

        monetaryModifier = 10;
        defenseModifier = 5;
        attackModifier = 10;
        levelRequiredModifier = 1;
        weightModifier = 2;

    }

}
