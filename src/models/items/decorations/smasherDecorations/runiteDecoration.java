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
        colorMap.put(new Color(204, 204, 204), new Color(106, 130, 140));
        colorMap.put(new Color(255, 255, 255), new Color(106, 130, 140));
        colorMap.put(new Color(187, 187, 187), new Color(40, 53, 60));
        colorMap.put(new Color(85, 85, 85), new Color(85, 113, 125));
        //colorMap.put(new Color(0,0,0), new Color(130,92,92));
        monetaryModifier = 800;
        defenseModifier = 60;
        attackModifier = 80;
        levelRequiredModifier = 40;
        weightModifier = 12;

    }

}
