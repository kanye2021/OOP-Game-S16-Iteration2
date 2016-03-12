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
        colorMap.put(new Color(255, 255, 255), new Color(185, 187, 219));

        monetaryModifier = 200;
        defenseModifier = 30;
        attackModifier = 40;
        levelRequiredModifier = 20;
        weightModifier = 8;

    }

}
