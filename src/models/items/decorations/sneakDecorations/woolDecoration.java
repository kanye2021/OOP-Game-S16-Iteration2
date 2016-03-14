package models.items.decorations.sneakDecorations;

import models.items.decorations.equippableItemDecoration;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by aseber on 3/7/16.
 */
public class woolDecoration extends equippableItemDecoration {
    public woolDecoration() {
        //ID
        IDModifier = 22;
        nameModifier = "wool";
        descriptionModifier = "wool";

        colorMap = new HashMap<>();
        colorMap.put(new Color(255, 255, 255), new Color(255, 255, 255));
        colorMap.put(new Color(204, 204, 204), new Color(255, 255, 255));
        colorMap.put(new Color(187, 187, 187), new Color(255, 255, 255));
        colorMap.put(new Color(85, 85, 85), new Color(255, 255, 255));
        //Color Stuff
        monetaryModifier = 10;
        defenseModifier = 5;
        levelRequiredModifier = 1;
        weightModifier = 2;
    }
}
