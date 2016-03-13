package models.items.decorations.sneakDecorations;

import models.items.decorations.equippableItemDecoration;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by ben on 3/13/16.
 */
public class dragonHideDecoration extends equippableItemDecoration {
    public dragonHideDecoration(){
        //ID
        IDModifier = 20;
        nameModifier = "Dragon Hide";
        descriptionModifier = "dragon hide";

        //Color Stuff
        colorMap = new HashMap<>();
        colorMap.put(new Color(204, 204, 204), new Color(218, 165, 32));

        //Stat stuff
        monetaryModifier = 1000;
        defenseModifier = 40;
        levelRequiredModifier = 10;
        weightModifier = 4;
    }
}
