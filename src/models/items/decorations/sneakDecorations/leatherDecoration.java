package models.items.decorations.sneakDecorations;

import models.items.decorations.equippableItemDecoration;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by ben on 3/13/16.
 */
public class leatherDecoration extends equippableItemDecoration {
    public leatherDecoration() {
        //ID
        IDModifier = 21;
        nameModifier = "Leather";
        descriptionModifier = "leather";

        //Color Stuff
        colorMap = new HashMap<>();
        colorMap.put(new Color(255, 255, 255), new Color(140, 70, 10));
        colorMap.put(new Color(204, 204, 204), new Color(139, 69, 19));
        colorMap.put(new Color(187, 187, 187), new Color(160, 82, 45));
        colorMap.put(new Color(85, 85, 85), new Color(160, 80, 30));

        //Stat Stuff
        monetaryModifier = 10;
        defenseModifier = 5;
        levelRequiredModifier = 1;
        weightModifier = 2;
    }
}
