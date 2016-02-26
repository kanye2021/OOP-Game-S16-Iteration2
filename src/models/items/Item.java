package models.items;

import models.entities.Entity;
import models.items.takeable.equippable.helmets.*;
import views.sprites.Sprite;

/**
 * Created by aseber on 2/21/16.
 */
public abstract class Item {

    public enum ItemDictionary {

        WOOD_HELMET(1000) {public Item createInstance() {return new WoodHelmet();}},
        IRON_HELMET(1001) {public Item createInstance() {return new IronHelmet();}},
        STEEL_HELMET(1002) {public Item createInstance() {return new SteelHelmet();}},
        MITHRIL_HELMET(1003) {public Item createInstance() {return new MithrilHelmet();}},
        GOLD_HELMET(1004) {public Item createInstance() {return new GoldHelmet();}},
        RUNITE_HELMET(1005) {public Item createInstance() {return new RuniteHelmet();}},

        /*WOOD_CHESTPLATE(1100) {public Item createInstance() {return new WoodChestplate();}},
        IRON_CHESTPLATE(1101) {public Item createInstance() {return new IronChestplate();}},
        STEEL_CHESTPLATE(1102) {public Item createInstance() {return new SteelChestplate();}},
        MITHRIL_CHESTPLATE(1103) {public Item createInstance() {return new MithrilChestplate();}},
        GOLD_CHESTPLATE(1104) {public Item createInstance() {return new GoldChestplate();}},
        RUNITE_CHESTPLATE(1105) {public Item createInstance() {return new RuniteChestplate();}},

        WOOD_GREAVES(1200) {public Item createInstance() {return new WoodGreaves();}},
        IRON_GREAVES(1201) {public Item createInstance() {return new IronGreaves();}},
        STEEL_GREAVES(1202) {public Item createInstance() {return new SteelGreaves();}},
        MITHRIL_GREAVES(1203) {public Item createInstance() {return new MithrilGreaves();}},
        GOLD_GREAVES(1204) {public Item createInstance() {return new GoldGreaves();}},
        RUNITE_GREAVES(1205) {public Item createInstance() {return new RuniteGreaves();}},

        WOOD_BOOTS(1300) {public Item createInstance() {return new WoodBoots();}},
        IRON_BOOTS(1301) {public Item createInstance() {return new IronBoots();}},
        STEEL_BOOTS(1302) {public Item createInstance() {return new SteelBoots();}},
        MITHRIL_BOOTS(1303) {public Item createInstance() {return new MithrilBoots();}},
        GOLD_BOOTS(1304) {public Item createInstance() {return new GoldBoots();}},
        RUNITE_BOOTS(1305) {public Item createInstance() {return new RuniteBoots();}},

        WOOD_GLOVES(1400) {public Item createInstance() {return new WoodGloves();}},
        IRON_GLOVES(1401) {public Item createInstance() {return new IronGloves();}},
        STEEL_GLOVES(1402) {public Item createInstance() {return new SteelGloves();}},
        MITHRIL_GLOVES(1403) {public Item createInstance() {return new MithrilGloves();}},
        GOLD_GLOVES(1404) {public Item createInstance() {return new GoldGloves();}},
        RUNITE_GLOVES(1405) {public Item createInstance() {return new RuniteGloves();}},

        WOOD_SHIELD(1500) {public Item createInstance() {return new WoodShield();}},
        IRON_SHIELD(1501) {public Item createInstance() {return new IronShield();}},
        STEEL_SHIELD(1502) {public Item createInstance() {return new SteelShield();}},
        MITHRIL_SHIELD(1503) {public Item createInstance() {return new MithrilShield();}},
        GOLD_SHIELD(1504) {public Item createInstance() {return new GoldShield();}},
        RUNITE_SHIELD(1505) {public Item createInstance() {return new RuniteShield();}};*/

        GRAVE(2000),
        OCTOPUS(2001),
        STATUE(2002);

        private int ID;

        ItemDictionary(int ID) {

            this.ID = ID;

        }

        public int getID() {

            return ID;

        }

    }

    protected ItemDictionary ID;
    protected Sprite sprite;

    public abstract boolean onTouch(Entity entity); // Method to describe what happens when an item is touched

    public ItemDictionary getID() {

        return ID;

    }

    public final Sprite getSprite() {

        return sprite;

    }

    public final boolean equals(Object o) {

        if (o instanceof Item) {

            Item otherItem = (Item) o;

            return this.getID() == otherItem.getID();

        }

        return false;

    }

    public abstract String getType();

}
