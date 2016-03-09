package models.items.decorations.smasherDecorations;

import models.items.decorations.equippableItemDecoration;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by aseber on 3/7/16.
 */
public class runiteDecoration extends equippableItemDecoration {

    public runiteDecoration() {

        IDModifier = 5;

        nameModifier = "Runite";
        descriptionModifier = "runite";

        colorMap = new HashMap<>();
        colorMap.put(new Color(255, 255, 255), new Color(0, 0, 255));

        monetaryModifier = 800;
        defenseModifier = 60;
        attackModifier = 80;
        levelRequiredModifier = 40;
        weightModifier = 12;

    }

}
