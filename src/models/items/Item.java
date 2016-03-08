package models.items;

import models.entities.Entity;
import models.items.interactive.*;
import models.items.obstacle.*;
import models.items.takeable.equippable.chestplate.*;
import models.items.takeable.equippable.helmets.*;
import models.items.takeable.equippable.greaves.*;
import models.items.takeable.equippable.boots.*;
import models.items.takeable.equippable.gloves.*;
import models.items.takeable.equippable.shield.*;
import models.items.takeable.equippable.weapons.oneHanded.*;
import models.items.takeable.equippable.weapons.twoHanded.*;

import models.items.takeable.quest.KeyOfKanye;
import views.sprites.Sprite;

import java.awt.*;

/**
 * Created by aseber on 2/21/16.
 */
public abstract class Item {

    public enum ItemDictionary {

        // Helmets
        WOOD_HELMET(1000) {public Item createInstance() {return new WoodHelmet();}},
        IRON_HELMET(1001) {public Item createInstance() {return new IronHelmet();}},
        STEEL_HELMET(1002) {public Item createInstance() {return new SteelHelmet();}},
        MITHRIL_HELMET(1003) {public Item createInstance() {return new MithrilHelmet();}},
        GOLD_HELMET(1004) {public Item createInstance() {return new GoldHelmet();}},
        RUNITE_HELMET(1005) {public Item createInstance() {return new RuniteHelmet();}},

        WOOD_COIF(1006) {public Item createInstance() {return new WoodCoif();}},
        IRON_COIF(1007) {public Item createInstance() {return new IronCoif();}},
        STEEL_COIF(1008) {public Item createInstance() {return new SteelCoif();}},
        MITHRIL_COIF(1009) {public Item createInstance() {return new MithrilCoif();}},
        GOLD_COIF(1010) {public Item createInstance() {return new GoldCoif();}},
        RUNITE_COIF(1011) {public Item createInstance() {return new RuniteCoif();}},

        WOOD_HAT(1012) {public Item createInstance() {return new WoodHat();}},
        IRON_HAT(1013) {public Item createInstance() {return new IronHat();}},
        STEEL_HAT(1014) {public Item createInstance() {return new SteelHat();}},
        MITHRIL_HAT(1015) {public Item createInstance() {return new MithrilHat();}},
        GOLD_HAT(1016) {public Item createInstance() {return new GoldHat();}},
        RUNITE_HAT(1017) {public Item createInstance() {return new RuniteHat();}},

        WOOD_CHESTPLATE(1100) {public Item createInstance() {return new WoodChestplate();}},
        IRON_CHESTPLATE(1101) {public Item createInstance() {return new IronChestplate();}},
        STEEL_CHESTPLATE(1102) {public Item createInstance() {return new SteelChestplate();}},
        MITHRIL_CHESTPLATE(1103) {public Item createInstance() {return new MithrilChestplate();}},
        GOLD_CHESTPLATE(1104) {public Item createInstance() {return new GoldChestplate();}},
        RUNITE_CHESTPLATE(1105) {public Item createInstance() {return new RuniteChestplate();}},

        WOOD_HIDE(1106) {public Item createInstance() {return new WoodHide();}},
        IRON_HIDE(1107) {public Item createInstance() {return new IronHide();}},
        STEEL_HIDE(1108) {public Item createInstance() {return new SteelHide();}},
        MITHRIL_HIDE(1109) {public Item createInstance() {return new MithrilHide();}},
        GOLD_HIDE(1110) {public Item createInstance() {return new GoldHide();}},
        RUNITE_HIDE(1111) {public Item createInstance() {return new RuniteHide();}},

        WOOD_ROBE(1112) {public Item createInstance() {return new WoodRobe();}},
        IRON_ROBE(1113) {public Item createInstance() {return new IronRobe();}},
        STEEL_ROBE(1114) {public Item createInstance() {return new SteelRobe();}},
        MITHRIL_ROBE(1115) {public Item createInstance() {return new MithrilRobe();}},
        GOLD_ROBE(1116) {public Item createInstance() {return new GoldRobe();}},
        RUNITE_ROBE(1117) {public Item createInstance() {return new RuniteRobe();}},

        WOOD_GREAVES(1200) {public Item createInstance() {return new WoodGreaves();}},
        IRON_GREAVES(1201) {public Item createInstance() {return new IronGreaves();}},
        STEEL_GREAVES(1202) {public Item createInstance() {return new SteelGreaves();}},
        MITHRIL_GREAVES(1203) {public Item createInstance() {return new MithrilGreaves();}},
        GOLD_GREAVES(1204) {public Item createInstance() {return new GoldGreaves();}},
        RUNITE_GREAVES(1205) {public Item createInstance() {return new RuniteGreaves();}},

        WOOD_CHAPS(1206) {public Item createInstance() {return new WoodChaps();}},
        IRON_CHAPS(1207) {public Item createInstance() {return new IronChaps();}},
        STEEL_CHAPS(1208) {public Item createInstance() {return new SteelChaps();}},
        MITHRIL_CHAPS(1209) {public Item createInstance() {return new MithrilChaps();}},
        GOLD_CHAPS(1210) {public Item createInstance() {return new GoldChaps();}},
        RUNITE_CHAPS(1211) {public Item createInstance() {return new RuniteChaps();}},

        WOOD_SKIRT(1212) {public Item createInstance() {return new WoodSkirt();}},
        IRON_SKIRT(1213) {public Item createInstance() {return new IronSkirt();}},
        STEEL_SKIRT(1214) {public Item createInstance() {return new SteelSkirt();}},
        MITHRIL_SKIRT(1215) {public Item createInstance() {return new MithrilSkirt();}},
        GOLD_SKIRT(1216) {public Item createInstance() {return new GoldSkirt();}},
        RUNITE_SKIRT(1217) {public Item createInstance() {return new RuniteSkirt();}},

        WOOD_BOOTS(1300) {public Item createInstance() {return new WoodBoots();}},
        IRON_BOOTS(1301) {public Item createInstance() {return new IronBoots();}},
        STEEL_BOOTS(1302) {public Item createInstance() {return new SteelBoots();}},
        MITHRIL_BOOTS(1303) {public Item createInstance() {return new MithrilBoots();}},
        GOLD_BOOTS(1304) {public Item createInstance() {return new GoldBoots();}},
        RUNITE_BOOTS(1305) {public Item createInstance() {return new RuniteBoots();}},

        // Gloves
        WOOD_GLOVES(1400) {public Item createInstance() {return new WoodGloves();}},
        IRON_GLOVES(1401) {public Item createInstance() {return new IronGloves();}},
        STEEL_GLOVES(1402) {public Item createInstance() {return new SteelGloves();}},
        MITHRIL_GLOVES(1403) {public Item createInstance() {return new MithrilGloves();}},
        GOLD_GLOVES(1404) {public Item createInstance() {return new GoldGloves();}},
        RUNITE_GLOVES(1405) {public Item createInstance() {return new RuniteGloves();}},

        // Shields
        WOOD_SHIELD(1500) {public Item createInstance() {return new WoodShield();}},
        IRON_SHIELD(1501) {public Item createInstance() {return new IronShield();}},
        STEEL_SHIELD(1502) {public Item createInstance() {return new SteelShield();}},
        MITHRIL_SHIELD(1503) {public Item createInstance() {return new MithrilShield();}},
        GOLD_SHIELD(1504) {public Item createInstance() {return new GoldShield();}},
        RUNITE_SHIELD(1505) {public Item createInstance() {return new RuniteShield();}},

        // One handed weapons
        WOOD_SWORD(1600) {public Item createInstance() {return new WoodSword();}},
        IRON_SWORD(1601) {public Item createInstance() {return new IronSword();}},
        STEEL_SWORD(1602) {public Item createInstance() {return new SteelSword();}},
        MITHRIL_SWORD(1603) {public Item createInstance() {return new MithrilSword();}},
        GOLD_SWORD(1604) {public Item createInstance() {return new GoldSword();}},
        RUNITE_SWORD(1605) {public Item createInstance() {return new RuniteSword();}},

        // Two handed weapons
        WOOD_TWO_HAND_SWORD(1700) {public Item createInstance() {return new WoodTwoHandSword();}},
        IRON_TWO_HAND_SWORD(1701) {public Item createInstance() {return new IronTwoHandSword();}},
        STEEL_TWO_HAND_SWORD(1702) {public Item createInstance() {return new SteelTwoHandSword();}},
        MITHRIL_TWO_HAND_SWORD(1703) {public Item createInstance() {return new MithrilTwoHandSword();}},
        GOLD_TWO_HAND_SWORD(1704) {public Item createInstance() {return new GoldTwoHandSword();}},
        RUNITE_TWO_HAND_SWORD(1705) {public Item createInstance() {return new RuniteTwoHandSword();}},

        // Bows
        WOOD_BOW(1706) {public Item createInstance() {return new WoodBow();}},
        IRON_BOW(1707) {public Item createInstance() {return new IronBow();}},
        STEEL_BOW(1708) {public Item createInstance() {return new SteelBow();}},
        MITHRIL_BOW(1709) {public Item createInstance() {return new MithrilBow();}},
        GOLD_BOW(1710) {public Item createInstance() {return new GoldBow();}},
        RUNITE_BOW(1711) {public Item createInstance() {return new RuniteBow();}},

        WOOD_STAFF(1712) {public Item createInstance() {return new WoodStaff();}},
        IRON_STAFF(1713) {public Item createInstance() {return new IronStaff();}},
        STEEL_STAFF(1714) {public Item createInstance() {return new SteelStaff();}},
        MITHRIL_STAFF(1715) {public Item createInstance() {return new MithrilStaff();}},
        GOLD_STAFF(1716) {public Item createInstance() {return new GoldStaff();}},
        RUNITE_STAFF(1717) {public Item createInstance() {return new RuniteStaff();}},

        SWAMPHACKS_SHIRT(1800){public Item createInstance(){return new Special_SwampHacksShirt();}},
        YEEZY(1801){public Item createInstance(){return new Special_Yeezy();}},
        ENERGY_SWORD(1802){public Item createInstance(){return new Special_EnergySword();}},


        // Obstacles
        GRAVE(2000) {public Item createInstance() {return new Grave();}},
        OCTOPUS(2001) {public Item createInstance() {return new Octopus();}},
        STATUE(2002) {public Item createInstance() {return new Statue();}},

        // Interactive items
        GATE_OF_KANYE(5000) {public Item createInstance() {return new GateOfKanye();}},

        // Quest items
        KEY_OF_KANYE(6000) {public Item createInstance() {return new KeyOfKanye();}};

        private int ID;
        public abstract Item createInstance();

        ItemDictionary(int ID) {

            this.ID = ID;

        }

        // Not sure if this is the best way to get an instance of an item from an id
        // Will be used when putting items on the map and loading + instantiating them.
        public static Item itemFromID(int id) {
            for (ItemDictionary entry: values()) {
                if (entry.getID() == id) {
                    return entry.createInstance();
                }
            }
            // Item with provided id not found..
            return null;
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

    public final void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public final boolean equals(Object o) {

        if (o instanceof Item) {

            Item otherItem = (Item) o;

            return this.getID() == otherItem.getID();

        }

        return false;

    }

    public Image getImage(){
        return sprite.getImage();
    }

    public abstract String getType();



}
