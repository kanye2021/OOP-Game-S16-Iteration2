package models.items.decorations.sneakDecorations;

import models.items.decorations.equippableItemDecoration;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by ben on 3/13/16.
 */
public class leatherDecoration extends equippableItemDecoration {
    public leatherDecoration(){
        //ID
        IDModifier = 21;
        nameModifier = "Leather";
        descriptionModifier = "leather";

        //Color Stuff
        colorMap = new HashMap<>();
        colorMap.put(new Color(204, 204, 204), new Color(218, 165, 32));

        //Stat Stuff
        monetaryModifier = 10;
        defenseModifier = 5;
        levelRequiredModifier = 1;
        weightModifier = 2;
    }
}
