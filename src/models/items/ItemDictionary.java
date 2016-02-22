package models.items;

import java.util.HashMap;

/**
 * Created by aseber on 2/21/16.
 */
public class ItemDictionary {

    public static HashMap<Integer, Item> items = new HashMap<>();

    static {

        items.put(1, new WoodHelmet());

    }

}
