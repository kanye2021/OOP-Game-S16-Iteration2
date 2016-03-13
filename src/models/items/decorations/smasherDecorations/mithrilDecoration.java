package models.items.decorations.smasherDecorations;

import models.items.decorations.equippableItemDecoration;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by aseber on 3/7/16.
 */
public class mithrilDecoration extends equippableItemDecoration {

    public mithrilDecoration() {

        IDModifier = 3;

        nameModifier = "Mithril";
        descriptionModifier = "mithril";

        colorMap = new HashMap<>();
        colorMap.put(new Color(204, 204, 204), new Color(65, 105, 225));
        colorMap.put(new Color(255, 255, 255), new Color(65, 105, 225));
        colorMap.put(new Color(187,187,187),new Color(30,144,255));
        colorMap.put(new Color(85, 85, 85), new Color(70, 119, 207));
        //colorMap.put(new Color(0,0,0), new Color(130,92,92));
        monetaryModifier = 200;
        defenseModifier = 30;
        attackModifier = 40;
        levelRequiredModifier = 20;
        weightModifier = 8;

    }

}
