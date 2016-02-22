package models.items;

import java.util.HashMap;

/**
 * Created by aseber on 2/21/16.
 */
public class ItemDictionary {

    public static HashMap<Integer, Item> items = new HashMap<>();

    static {

        items.put(1000, new WoodHelmet());
        items.put(1001, new IronHelmet());
        /*items.put(1002, new SteelHelmet());
        items.put(1003, new MithrilHelmet());
        items.put(1004, new GoldHelmet());
        items.put(1005, new RuniteHelmet());

        items.put(1100, new WoodChestplate());
        items.put(1101, new IronChestplate());
        items.put(1102, new SteelChestplate());
        items.put(1103, new MithrilChestplate());
        items.put(1104, new GoldChestplate());
        items.put(1105, new RuniteChestplate());

        items.put(1200, new WoodGreaves());
        items.put(1201, new IronGreaves());
        items.put(1202, new SteelGreaves());
        items.put(1203, new MithrilGreaves());
        items.put(1204, new GoldGreaves());
        items.put(1205, new RuniteGreaves());

        items.put(1300, new WoodBoots());
        items.put(1301, new IronBoots());
        items.put(1302, new SteelBoots());
        items.put(1303, new MithrilBoots());
        items.put(1304, new GoldBoots());
        items.put(1305, new RuniteBoots());

        items.put(1400, new WoodGloves());
        items.put(1401, new IronGloves());
        items.put(1402, new SteelGloves());
        items.put(1403, new MithrilGloves());
        items.put(1404, new GoldGloves());
        items.put(1405, new RuniteGloves());

        items.put(1500, new WoodShield());
        items.put(1501, new IronShield());
        items.put(1502, new SteelShield());
        items.put(1503, new MithrilShield());
        items.put(1504, new GoldShield());
        items.put(1505, new RuniteShield());

        */

    }

}
